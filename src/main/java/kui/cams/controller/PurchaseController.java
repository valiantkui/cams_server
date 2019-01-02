package kui.cams.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kui.cams.dao.GoodsDao;
import kui.cams.dao.PurchaseDao;
import kui.cams.dao.StudentDao;
import kui.cams.entity.Goods;
import kui.cams.entity.Purchase;
import kui.cams.entity.extro.GoodsInfo;
import kui.cams.entity.extro.StudentInfo;
import kui.cams.tools.Global;

@CrossOrigin
@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Resource(name="purchaseDao")
	private PurchaseDao purchaseDao;
	
	@Resource(name="goodsDao")
	private GoodsDao goodsDao;
	
	@Resource(name="studentDao")
	private StudentDao studentDao;
	
	@RequestMapping("/findAllPurchase")
	@ResponseBody
	public List<Purchase> findAllPurchase(HttpSession session){
		//从session中获取c_no
		String c_no = (String) session.getAttribute("c_no");
		String s_id = (String) session.getAttribute("s_id");
		if(c_no == null && s_id != null) {
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
		}
		
		return purchaseDao.findPurchaseByC_no(c_no);
	}
	
	@RequestMapping("/findAllInPurchase")
	@ResponseBody
	public List<Purchase> findAllInPurchase(HttpSession session){
		//从session中获取c_no
		String c_no = (String) session.getAttribute("c_no");
		String s_id = (String) session.getAttribute("s_id");
		if(c_no == null && s_id != null) {
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
		}
		
		return purchaseDao.findInPurchaseByC_no(c_no);
	}
	
	/**
	 * 根据p_no获取一个团购信息
	 * @param pNoStr
	 * @return
	 */
	@RequestMapping("/findPurchaseByP_no")
	@ResponseBody
	public Purchase findPurchaseByP_no(@RequestParam("p_no") String pNoStr) {
		int p_no = Integer.parseInt(pNoStr);
		return purchaseDao.findPurchaseByP_no(p_no);
	}
	
	/**
	 * 获取指定团购编号的所有团购商品信息的集合
	 * @param pNoStr	团购编号
	 * @return
	 */
	@RequestMapping("/findGoodsInfoByP_no")
	@ResponseBody
	public List<GoodsInfo> findGoodsInfoByP_no(@RequestParam("p_no") String pNoStr){
		int p_no = Integer.parseInt(pNoStr);
		List<GoodsInfo> goodsInfoList = new ArrayList<GoodsInfo>();
		List<Goods> goodsList = goodsDao.findGoodsByP_no(p_no);
		try {
			for(Goods g: goodsList) {
				BufferedReader br = new BufferedReader(new FileReader(Global.goodsPath+g.getParticipator_path()));
				
				String line = null;
				List<StudentInfo> studentInfoList = new ArrayList<>();
				while((line=br.readLine())!=null) {
					StudentInfo studentInfo = new StudentInfo();
					String[] infoArr = line.split(" ");
					String s_id = infoArr[0];
					int num = Integer.parseInt(infoArr[1]);
					studentInfo.setStudent(studentDao.findStudentByS_id(s_id));
					studentInfo.setNumber(num);
					studentInfoList.add(studentInfo);
				}
				GoodsInfo goodsInfo = new GoodsInfo();
				goodsInfo.setGoods(g);
				goodsInfo.setStudentInfoList(studentInfoList);
				goodsInfoList.add(goodsInfo);
				
			}
			
			return goodsInfoList;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goodsInfoList;
		
	}
	
	
	
	
	
	/**
	 * 获取当前学生的所有
	 * @param session
	 * @return
	 */
	@RequestMapping("/findPurchaseByS_id")
	@ResponseBody
	public List<Purchase> findPurchaseByS_id(HttpSession session){
		//从session中获取c_no
		String s_id = (String) session.getAttribute("s_id");
		
		return purchaseDao.findPurchaseByS_id(s_id);
	}
	
	/**
	 * 删除一个团购，包含purchase表和goods表的内容
	 * @param pNoStr
	 * @return
	 */
	@RequestMapping("/deletePurchaseByP_no")
	@ResponseBody
	public String deletePurchaseByP_no(@RequestParam("p_no") String pNoStr) {
		
		int p_no = Integer.parseInt(pNoStr);
		
		if(purchaseDao.deletePurchaseByP_no(p_no)>0 && goodsDao.deleteGoodsByP_no(p_no)>0) return "true";
		
		return "false";
	}
	
	@RequestMapping("/publishPurchase")
	@ResponseBody
	public String publishPurchase(String title,String content,String type,String end_date,String[] goods,String [] price, HttpSession session) {
		
		System.out.println("publishPurchase()");
		String s_id = (String) session.getAttribute("s_id");
		String c_no = studentDao.findStudentByS_id(s_id).getC_no();
		System.out.println("s_id:"+s_id+",,,c_no:"+c_no);
		//向数据库表purchase添加团购
		Purchase purchase = new Purchase();
		purchase.setTitle(title);
		purchase.setContent(content);
		purchase.setType(type);
		purchase.setPublish_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		purchase.setC_no(c_no);
		purchase.setS_id(s_id);
		purchase.setEnd_date(end_date);
		try {
			purchaseDao.publishPurchase(purchase);
			
			int p_no = purchase.getP_no();
			System.out.println("添加的P_no:"+p_no);
			
			
			//向goods表添加记录
			List<Goods> goodList= new ArrayList<>();
			
			for(int i = 0;i<goods.length;i++) {
				Goods g = new Goods();
				g.setName(goods[i]);
				g.setPrice(Double.parseDouble(price[i]));
				g.setP_no(p_no);
				goodList.add(g);
			}
			
			System.out.println("添加之前商品列表："+goodList);
			goodsDao.addGoods(goodList);
			System.out.println("添加之后商品列表："+goodList);
			for(Goods g: goodList) {
				g.setParticipator_path(g.getG_no()+".goods");
			}
			System.out.println("修改之后商品列表："+goodList);
			//创建每一个参与者信息的文件
			File destDir = new File(Global.goodsPath);
			if(!destDir.exists()) destDir.mkdirs();
			
			for(Goods g:goodList) {
				new File(destDir,g.getParticipator_path()).createNewFile();
			}
			
			
			goodsDao.updatePath(goodList);
		}catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("/joinPurchaseGoods")
	@ResponseBody
	public String joinPurchaseGoods(@RequestParam("g_no") String gNoStr,@RequestParam("number") String numStr,@RequestParam("p_no") String pNoStr,HttpSession session) {
		int g_no = Integer.parseInt(gNoStr);
		int number = Integer.parseInt(numStr);
		int p_no = Integer.parseInt(pNoStr);//暂时用不到
		
		String path = goodsDao.findGoodsByG_no(g_no).getParticipator_path();
		String s_id = (String) session.getAttribute("s_id");
		
		String content = s_id+" "+number+"\r\n";
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(Global.goodsPath+path),true);
			fw.write(content);
			fw.flush();
			
		} catch (IOException e) {
			return "false";
			
		} finally {
			try {
				if(fw != null) fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		
		
		
		return "true";
	}
	
	@RequestMapping("/unjoinPurchaseGoods")
	@ResponseBody
	public String unjoinPurchaseGoods(@RequestParam("g_no") String gNoStr,HttpSession session) {
		int g_no = Integer.parseInt(gNoStr);
		String s_id = (String) session.getAttribute("s_id");
		
		String path = goodsDao.findGoodsByG_no(g_no).getParticipator_path();
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(Global.goodsPath+path));
			List<String> list = new ArrayList<>();
			String line = null;
			while((line = br.readLine()) != null) {
				if(line.startsWith(s_id)) continue;
				list.add(line);
			}
			
			bw = new BufferedWriter(new FileWriter(Global.goodsPath+path));
			for(String s: list) {
				bw.write(s+"\r\n");
			}
			bw.flush();
		} catch (FileNotFoundException e) {
		
			return "false";
		} catch (IOException e) {
			
			return "false";
		} finally {
			try {
				if(br!=null) br.close();
				if(bw!=null) bw.close();
			}catch (Exception e) {
				
			}
		}
		
		
		return "true";
	}
}

package kui.cams.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kui.cams.dao.ActivityDao;
import kui.cams.dao.StudentDao;
import kui.cams.entity.Activity;
import kui.cams.entity.Student;
import kui.cams.tools.Global;

@CrossOrigin
@RestController
@RequestMapping("activity")
public class ActivityController {

	@Resource(name="activityDao")
	private ActivityDao activityDao;
	
	@Resource(name="studentDao")
	private StudentDao studentDao;
	
	@RequestMapping("/cancelJoin")
	public String cancelJoin(@RequestParam("a_no") String aNoStr,HttpSession session) {
		int a_no = Integer.parseInt(aNoStr);
		//从session中获取当前登陆用户
		String s_id = (String)session.getAttribute("s_id");
		List<String> s_idList = new ArrayList<>();
		String filePath = Global.activityPath+a_no+".activity";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String s = null;
			while((s=br.readLine()) != null) {
				if(s_id.equals(s)) continue;
				
				s_idList.add(s);
			}
			br.close();
			FileWriter fw = new FileWriter(filePath,false);
			for(String s2: s_idList) {
				fw.write(s2+"\r\n");
			}
			fw.flush();
			
			fw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "false";
		} catch (IOException e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("/joinActivity")
	public String joinActivity(@RequestParam("a_no") String aNoStr,HttpSession session) {
		int a_no = Integer.parseInt(aNoStr);
		//从session中获取当前登陆用户
		String s_id = (String)session.getAttribute("s_id");
		String filePath = Global.activityPath+a_no+".activity";
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(filePath),true);
			fw.write(s_id+"\r\n");
		} catch (IOException e) {
			return "false";
		} finally {
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return "true";
	}
	
	/**
	 * 
	 * @param aNoStr
	 * @return 活动信息
	 */
	@RequestMapping("/findActivityByA_no")
	public Activity findActivityByA_no(@RequestParam("a_no") String aNoStr) {
		int a_no = Integer.parseInt(aNoStr);
		return activityDao.findActivityByA_no(a_no);
	}
	
	@RequestMapping("/findParticipatorInfoByA_no")
	public List<Student> findParticipatorInfoByA_no(@RequestParam("a_no") String aNoStr){
		int a_no = Integer.parseInt(aNoStr);
		Activity activity = activityDao.findActivityByA_no(a_no);
		String participator_path = activity.getParticipator_path();
		List<Student> studentList = null;
		List<String> s_idList = new ArrayList<>(); 
		
		//如果s_idList为空，不能传到数据库，会报错
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(Global.activityPath+participator_path));
			String s_id = null;
			while((s_id=br.readLine()) != null) {
				s_idList.add(s_id);
			}
			
			System.out.println(s_idList);
			if(s_idList==null || s_idList.size()==0) return null;
			studentList = studentDao.findStudentByS_idList(s_idList);
			return studentList;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return studentList;
		
	}
	
	@RequestMapping("/findCurrentMonthActivity")
	public List<Activity> findCurrentMonthActivity(HttpSession session){
		//从session中获取当前登陆账号
		String c_no = (String) session.getAttribute("c_no");
		String s_id = (String) session.getAttribute("s_id");
		
		String month = new SimpleDateFormat("yyyy-MM").format(new Date());
		if(c_no == null && s_id != null) {
			//如果是学生用户
			return activityDao.findActivityByMonthS_id(s_id, month);
		}
		
		
		return activityDao.findActivityByMonthC_no(c_no, month);
	}
	@RequestMapping("/findActivityByS_id")
	public List<Activity> findActivityByS_id(HttpSession session){
		String s_id = (String) session.getAttribute("s_id");
		List<Activity> activityList = activityDao.findActivityByS_id(s_id);
		if(activityList.size()==0) return null;
		
		return activityList;
		
	}
	
	@RequestMapping("/findActivityByDate")
	public List<Activity> findActivityByDate(String date,HttpSession session){
		//从session中获取当前登陆账号
		String c_no = (String) session.getAttribute("c_no");
		String s_id = (String) session.getAttribute("s_id");
		
		if(c_no == null && s_id != null) {
			//如果是学生用户
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
			
		}
				
		
		
		return activityDao.findActivityByMonthC_no(c_no, date);
	}
	
	@RequestMapping("/findActivityWithStudentByDate")
	public Object[] findActivityWithStudentByDate(String date,HttpSession session){
		List<Activity> activityList= findActivityByDate(date, session);
		List<String> s_idList= new ArrayList<>();
		for(Activity a: activityList) {
			s_idList.add(a.getS_id());
		}
		
		List<Student> studentList = studentDao.findStudentByS_idList(s_idList);
		Map<String,List<Object>> map = new HashMap<>();
		
		
		/*
		 * map.put("activityList", activityList); map.put("studentList", studentList);
		 */
		return new Object[]{activityList,studentList};
		
	}
	
	@RequestMapping("/findCurrentDayActivity")
	public List<Activity> findCurrentDayActivity(HttpSession session){
		//从session中获取当前登陆账号
		String c_no = (String) session.getAttribute("c_no");
		String s_id = (String) session.getAttribute("s_id");
		
		String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(c_no == null && s_id != null) {
			//如果是学生用户
			return activityDao.findActivityByDayS_id(s_id, day);
		}
		
		
		return activityDao.findActivityByDayC_no(c_no, day);
	}
	
	/**
	 * 已测试
	 * @param title
	 * @param content
	 * @param start_date
	 * @param end_date
	 * @param session
	 * @return
	 */
	@RequestMapping("/publishActivity")
	public String publishActivity(String title,String content,String start_date,String end_date,HttpSession session) {
		//从session中获取当前登陆账号
		String c_no = (String) session.getAttribute("c_no");
		String s_id = (String) session.getAttribute("s_id");
		
		if(c_no == null && s_id != null) {
			//表示是学生用户
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
			
		}
		
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		Activity activity = new Activity();
		activity.setC_no(c_no);
		activity.setS_id(s_id);
		activity.setTitle(title);
		activity.setContent(content);
		activity.setStart_date(start_date);
		activity.setEnd_date(end_date);
		activity.setPublish_date(now);
		activity.setUpdate_date(now);
		try {
			activityDao.addActivity(activity);
			int a_no = activity.getA_no();
			String fileName = a_no+".activity";
			File destDir = new File(Global.activityPath);
			if(!destDir.exists()) destDir.mkdirs();
			File filePath = new File(destDir,fileName);
			filePath.createNewFile();
			activityDao.updatePathByA_no(a_no, fileName);
		}catch (Exception e) {
			return "false";
		}
		
		return "true";
	}
	
	
	
	@RequestMapping("/updateActivity")
	public String updateActivity(@RequestParam("a_no") String aNoStr,String title,String content,String start_date,String end_date,HttpSession session) {
		//从session中获取当前登陆账号
		String c_no = (String) session.getAttribute("c_no");
		String s_id = (String) session.getAttribute("s_id");
		int a_no = Integer.parseInt(aNoStr);
		if(c_no == null && s_id != null) {
			//表示是学生用户
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
			
		}
		
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		Activity activity = new Activity();
		activity.setA_no(a_no);
		activity.setC_no(c_no);
		activity.setS_id(s_id);
		activity.setTitle(title);
		activity.setContent(content);
		activity.setStart_date(start_date);
		activity.setEnd_date(end_date);
	
		activity.setUpdate_date(now);
		try {
			activityDao.updateActivity(activity);
			
		}catch (Exception e) {
			return "false";
		}
		
		return "true";
	}
	
	@RequestMapping("/deleteActivityByA_no")
	public String deleteActivityByA_no(@RequestParam("a_no") String aNoStr) {
		int a_no = Integer.parseInt(aNoStr);
		
		
		
		//删除数据库的信息
		
		try {
			//先删除活动的参与者信息文件
			String path = activityDao.findActivityByA_no(a_no).getParticipator_path();
			File file = new File(Global.activityPath + path);
			if (file.exists())
				file.delete();
			if (activityDao.deleteActivityByA_no(a_no) <= 0)
				return "false";
		} catch (Exception e) {
			return "false";
		}
		
		return "true";
		
	}
	
	@RequestMapping("/searchActivityByTitle")
	public List<Activity> searchActivityByTitle(String title,HttpSession session){
		String s_id = (String)session.getAttribute("s_id");
		String c_no = (String)session.getAttribute("c_no");
		if(c_no==null && s_id != null) {
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
		}
		
		return activityDao.searchActivityByTitle(title, c_no);
	}
	
	
	@RequestMapping("/searchStudentActivityByTitle")
	public List<Activity> searchStudentActivityByTitle(String title,HttpSession session){
		String s_id = (String)session.getAttribute("s_id");
	
		
		return activityDao.searchStudentActivityByTitle(title, s_id);
	}
	@RequestMapping("/searchActivityByDate")
	public List<Activity> searchActivityByDate(String date,HttpSession session){
		String s_id = (String)session.getAttribute("s_id");
		String c_no = (String)session.getAttribute("c_no");
		if(c_no==null && s_id != null) {
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
		}
		
		return activityDao.searchActivityByDate(date, c_no);
	}
	
}

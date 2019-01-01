package kui.cams.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kui.cams.dao.R_c_sDao;
import kui.cams.dao.ResourceDao;
import kui.cams.dao.StudentDao;
import kui.cams.entity.R_c_s;

@CrossOrigin
@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Resource(name="resourceDao")
	private ResourceDao resourceDao;
	
	@Resource(name="rcsDao")
	private R_c_sDao rcsDao;
	
	@Resource(name="studentDao")
	private StudentDao studentDao;
	
	/**
	 * 检查给定md5是否存在，如果存在表明此文件已经在本系统了
	 * @param md5 md5码的16进制字符串
	 * @return
	 */
	@RequestMapping("/checkMd5")
	@ResponseBody
	public String checkMd5(String md5) {
		kui.cams.entity.Resource resource = resourceDao.findResourceByMd5(md5);
		if(resource == null) return "fasle";
		return "true";
	}
	
	/**
	 * 上传课件，有两种可能：
	 * 1. 由班级用户所上传的课件
	 * 2. 由学生用户所上传的课件
	 * 问题： 如果两者请求同一个url请求，那么在controller层如何判断上传课件的用户是班级还是学生呢？
	 * 解决1： 从session 中获取c_no或s_id（班级编号和学生编号），会有三种可能
	 * 1. c_no存在，s_id不存在
	 * 2. c_no不存在，s_id存在
	 * 3. c_no 存在，s_id存在
	 * 解决方案中所需面对的是第三种可能，如果c_no和s_i都存在，则没有办法获取当前用户是班级还是学生。
	 * 最终解决：在LoginController层，添加逻辑：当学生用户登陆后，清除班级用户；当班级用户登陆后，清除学生用户信息；
	 * @param type
	 * @param subject
	 * @param md5
	 * @param fileName
	 * @param session
	 * @return
	 */
	@RequestMapping("/uploadResource")
	public String uploadResource(String type,String subject,String md5,String r_name,HttpSession session) {
		//从session中获取当前学生信息，，
		String c_no =(String) session.getAttribute("c_no");
		String s_id =(String) session.getAttribute("s_id");
		if(c_no == null && s_id != null){
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
		}
		//根据md5获取资源的r_no
		int r_no = resourceDao.findResourceByMd5(md5).getR_no();
		
		
		R_c_s rcs = new R_c_s();
		rcs.setR_no(r_no);
		rcs.setC_no(c_no);
		rcs.setR_name(r_name);
		String upload_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		rcs.setUpload_date(upload_date);
		rcs.setS_id(s_id);
		try {
			rcsDao.addR_c_s(rcs);
			//将resource表中指定r_no的课件计数加1
			resourceDao.updateCount(r_no);
		}catch (Exception e) {
			return "false";
		}
		
		return "true";
	}
	
	/**
	 * 
	 * @param type
	 * @param subject
	 * @param md5
	 * @param fileName
	 * @param file
	 * @param session
	 * @return
	 */
	@RequestMapping("/uploadResourceWithFile")
	public String uploadResourceWithFile(String type,String subject,String md5,String r_name,MultipartFile file,HttpSession session) {
		//从session中获取当前学生信息，，
		String c_no =(String) session.getAttribute("c_no");
		String s_id =(String) session.getAttribute("s_id");
		if(c_no == null && s_id != null){
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
		}
		//根据md5获取资源的r_no
		
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String path = md5+suffix;
		kui.cams.entity.Resource resource = new kui.cams.entity.Resource();
		resource.setMd5(md5);
		resource.setCount(1);
		resource.setSubject(subject);
		resource.setType(type);
		resource.setPath(path);
		
		resourceDao.addResource(resource);
		int r_no = resource.getR_no();
		
		R_c_s rcs = new R_c_s();
		rcs.setR_no(r_no);
		
		
		rcs.setC_no(c_no);
		rcs.setR_name(r_name);
		rcs.setUpload_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		rcs.setS_id(s_id);
		return "true";
	}
}

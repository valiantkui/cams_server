package kui.cams.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kui.cams.dao.ClassDao;
import kui.cams.entity.Class;
import kui.cams.tools.Global;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {

	@Resource(name="classDao")
	private ClassDao classDao;
	
	@RequestMapping("/checkC_no")
	public String checkC_no(@RequestParam("c_no") String c_no) {
		
		
		Class c = classDao.findClassByC_no(c_no);
		if(c!=null) {
			return "false";
		}
		return "true";//表示可以用
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/classRegister")
	public String classRegister(HttpSession session,String c_no,String password,String name,String school,String profession,String enrol_date,String tell,MultipartFile file) {
		
		String sufix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		File destFile = new File(Global.classImagePath);
		if(!destFile.exists()) destFile.mkdirs();
		String image_path = c_no + sufix;
		try {	
			file.transferTo(new File(destFile,image_path));
		} catch (IllegalStateException e1) {
			return "false";
		} catch (IOException e1) {
			return "img_false";
		}
		
		kui.cams.entity.Class c = new kui.cams.entity.Class();
		c.setC_no(c_no);
		c.setPassword(password);
		c.setName(name);
		c.setSchool(school);
		c.setProfession(profession);
		c.setImage_path(image_path);
		c.setEnrol_date(enrol_date);
		c.setTell(tell);
		try {
			
			classDao.classRegister(c);
		}catch(Exception e) {
			return "db_false";//数据库更新失败
		}
		return "true";
		
	}
}

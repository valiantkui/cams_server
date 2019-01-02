package kui.cams.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kui.cams.dao.StudentDao;
import kui.cams.entity.Student;
import kui.cams.tools.Global;

@Controller
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
	
	@Resource(name="studentDao")
	private StudentDao studentDao;

	@RequestMapping("/findStudentByS_idList")
	@ResponseBody
	public List<Student> findStudentByS_idList(String s_idJson){
		System.out.println(s_idJson);
		
		
		
		List<String> s_idList = new Gson().fromJson(s_idJson, new TypeToken<List<String>>() {}.getType());
		System.out.println("s_idList:"+s_idList);
		if(s_idList==null || s_idList.size()==0) return null;
		return studentDao.findStudentByS_idList(s_idList);
	}
	
	@RequestMapping("/updateStudentByStudent")
	@ResponseBody
	public String updateStudentByStudent(String name,MultipartFile file,HttpSession session) {
		String s_id = (String) session.getAttribute("s_id");
		
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String image_path = s_id + suffix;
		File destDir = new File(Global.studentImagePath);
		if(!destDir.exists()) destDir.mkdirs();
		try {
			file.transferTo(new File(destDir,image_path));
			Student student = new Student();
			student.setS_id(s_id);
			student.setName(name);
			student.setImage_path(image_path);
			studentDao.updateStudentByStudent(student);
			
		} catch (IllegalStateException e) {
			return "false";
		} catch (IOException e) {
			return "false";
		}
		
		
		
		
		return "true";
	}
	
	@RequestMapping("/updateStudentPassword")
	@ResponseBody
	public String updateStudentPassword(String password,HttpSession session) {
		String s_id = (String) session.getAttribute("s_id");
		
		try {
			
		studentDao.updateStudentPassword(s_id, password);
		}catch (Exception e) {
			return "false";
		}
		
		
		return "true";
	}
}

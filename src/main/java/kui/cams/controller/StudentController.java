package kui.cams.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kui.cams.dao.StudentDao;
import kui.cams.entity.Student;

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
}

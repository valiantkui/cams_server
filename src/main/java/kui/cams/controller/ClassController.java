package kui.cams.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kui.cams.dao.ClassDao;
import kui.cams.dao.StudentDao;
import kui.cams.dao.SyllabusDao;
import kui.cams.dao.TaskDao;
import kui.cams.entity.Student;
import kui.cams.entity.Syllabus;
import kui.cams.entity.Task;
import kui.cams.tools.Global;
import kui.cams.tools.TermDateTool;

@CrossOrigin
@Controller
@RequestMapping("/class")
public class ClassController {

	@Resource(name="classDao")
	private ClassDao classDao;
	
	@Resource(name="studentDao")
	private StudentDao studentDao;
	
	@Resource(name="syllabusDao")
	private SyllabusDao syllabusDao;
	
	@Resource(name="taskDao")
	private TaskDao taskDao;
	
	@RequestMapping("/findStudentByC_no")
	@ResponseBody
	public List<Student> findStudentByC_no(@RequestParam("c_no") String c_no) {
		List<Student> studentList = studentDao.findStudentByC_no(c_no);
		
		return studentList;
		
	}
	@RequestMapping("/findCurrentClassStudent")
	@ResponseBody
	public List<Student> findCurrentClassStudent(HttpSession session){
		String c_no = (String) session.getAttribute("c_no");
		return studentDao.findStudentByC_no(c_no);
	}
	
	@RequestMapping("/addStudent")
	@ResponseBody
	public String addStudent(HttpServletRequest request) {
		String s_id = request.getParameter("s_id");
		String name = request.getParameter("name");
		String post = request.getParameter("post");
		Student student = new Student();
		
		HttpSession session = request.getSession();
		student.setS_id(s_id);
		student.setPassword("123456");
		student.setName(name);
		student.setPost(post);
		student.setC_no((String)session.getAttribute("c_no"));
		try {
			studentDao.addStudent(student);
		}catch(Exception e) {
			return "false";
		}
		return "true";
	}
	
	/**
	 * 检测当前班级中是否已经包含该学号了，如果包含返回false,否则返回true;
	 * @param s_id
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkS_id")
	@ResponseBody
	public String checkS_id(String s_id,HttpSession session) {
		
		Student student = studentDao.findStudentByS_id(s_id);
		if(student == null) return "true";
		else return "false";
	}
	/**
	 * 修改学生信息
	 */
	@RequestMapping("/updateStudentByClass")
	@ResponseBody
	public String updateStudentByClass(@RequestParam("stuJson") String stuJson) {
		Student student = new Gson().fromJson(stuJson, new TypeToken<Student>(){}.getType());
		try {
			if(studentDao.updateStudentByClass(student)<=0) return "false";
		}catch(Exception e) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("/deleteStudentByS_id")
	@ResponseBody
	public String deleteStudentByS_id(@RequestParam("s_id") String s_id) {
		try {
			String image_path = studentDao.findStudentByS_id(s_id).getImage_path();
			int row = studentDao.deleteStudentByS_id(s_id) ;
			File file = new File(Global.studentImagePath+image_path);
			if(file.exists()) file.delete();
			if(row < 0) return "s_id_false";
		} catch(Exception e) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("/findSyllabusByTermC_no")
	@ResponseBody
	public Syllabus findSyllabusByTermC_no(@RequestParam("term") String term, @RequestParam("c_no") String c_no) {
		//System.out.println(term+",,,"+c_no);
		
		Syllabus syllabus = new Syllabus();
		
		syllabus.setTerm(Integer.parseInt(term));
		syllabus.setC_no(c_no);
		syllabus = syllabusDao.findSyllabusByTermC_no(syllabus);
		//System.out.println(syllabus);
		return syllabus;
	}
	
	@RequestMapping("/addSyllabus")
	@ResponseBody
	public String addSyllabus(HttpServletRequest request,String name, @RequestParam("term") String termStr,@RequestParam("subject") String[] subject,@RequestParam("file") MultipartFile file) {
		System.out.println("addSyllabus()");
		int term = Integer.parseInt(termStr);
		System.out.println("name:"+name);
		String all_subject = "";
		for(String s: subject) {
			all_subject += (s +"#");
		}
		all_subject = all_subject.substring(0,all_subject.length()-1);
		String image_path = null;
		String c_no = (String)(request.getSession().getAttribute("c_no"));
		String s_id = (String)(request.getSession().getAttribute("s_id"));
		if(c_no == null && s_id != null) {
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
		}
		
		System.out.println("c_no:"+c_no);
		String sufix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		image_path = c_no+"_"+term+sufix;
		System.out.println("image_path:"+image_path);
		System.out.println(Global.syllabusPath);
		File destFile = new File(Global.syllabusPath);
		if(!destFile.exists()) destFile.mkdirs();
		try {
			file.transferTo(new File(destFile,image_path));
			
			
			System.out.println("上传成功");
		} catch (IllegalStateException e) {
			return "file_false";
		} catch (IOException e) {
			return "file_false";
		}
		Syllabus syllabus = new Syllabus();
		syllabus.setName(name);
		syllabus.setTerm(term);
		syllabus.setAll_subject(all_subject);
		syllabus.setImage_path(image_path);
		syllabus.setC_no(c_no);
		try {
			syllabusDao.addSyllabus(syllabus);
		} catch (Exception e) {
			return "db_false";
		}
		return "true";
	}
	
	@RequestMapping("/updateSyllabus")
	@ResponseBody
	public String updateSyllabus(@RequestParam("s_no") String sNoStr,String name,String[] subject,@RequestParam("term") String termStr,MultipartFile file) {
		int s_no = Integer.parseInt(sNoStr);
		int term = Integer.parseInt(termStr);
		String all_subject = "";
		for(String s: subject) {
			all_subject += (s +"#");
		}
		
		String c_no = syllabusDao.findSyllabusByS_no(s_no).getC_no();
		all_subject = all_subject.substring(0,all_subject.length()-1);
		String sufix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				
		String image_path = c_no +"_" +term +sufix;
		File destFile = new File(Global.syllabusPath);
		if(!destFile.exists()) destFile.mkdirs();
		try {
			file.transferTo(new File(destFile,image_path));
			System.out.println("上传成功");
		} catch (IllegalStateException e) {
			return "file_false";
		} catch (IOException e) {
			return "file_false";
		}
		Syllabus syllabus = new Syllabus();
		syllabus.setS_no(s_no);
		syllabus.setName(name);
		syllabus.setTerm(term);
		syllabus.setAll_subject(all_subject);
		syllabus.setImage_path(image_path);
		try {
			if(syllabusDao.updateSyllabus(syllabus)<=0) {
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "db_false";
		}
		return "true";
	}
	
	@RequestMapping("/findSyllabusByTerm")
	@ResponseBody
	public Syllabus findSyllabusByTerm(@RequestParam("term") String termStr,HttpSession session) {
		String c_no =(String) session.getAttribute("c_no");
		String s_id = (String) session.getAttribute("s_id");
		if(c_no==null && s_id != null) {
			c_no = studentDao.findStudentByS_id(s_id).getC_no();
		}
		int term = Integer.parseInt(termStr);
		Syllabus syllabus = new Syllabus();
		syllabus.setC_no(c_no);
		syllabus.setTerm(term);
		
		return syllabusDao.findSyllabusByTermC_no(syllabus);
		
		
	}
	
	@RequestMapping("downloadSyllabusImageByS_no")
	public void downloadSyllabusImageByS_no(@RequestParam("s_no") String sNoStr,HttpServletResponse response) {
		int s_no = Integer.parseInt(sNoStr);
		
		Syllabus syllabus = syllabusDao.findSyllabusByS_no(s_no);
		String path = syllabus.getImage_path();
		response.setContentType("image/png");
		try {
			FileInputStream fis = new FileInputStream(Global.syllabusPath+path);
			OutputStream os = response.getOutputStream();
			byte[] bytes = new byte[1024*10];
			int i = -1;
			while((i=fis.read(bytes))!=-1) {
				os.write(bytes, 0, i);
			}
			
			os.flush();
			fis.close();
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping("/deleteSyllabusByS_no")
	@ResponseBody
	public String deleteSyllabusByS_no(@RequestParam("s_no") String sNoStr) {
		int s_no = Integer.parseInt(sNoStr);
		String path = syllabusDao.findSyllabusByS_no(s_no).getImage_path();
		try {
			
		//1.先将课程表图片文件删除
			File file = new File(Global.syllabusPath+path);
			if(file.exists()) file.delete();
		//2.删除数据库中课程表记录
			syllabusDao.deleteSyllabusByS_no(s_no);
		}catch (Exception e) {
			return "false";
		}
		
		return "true";
	}
	
	
	
}

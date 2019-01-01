package kui.cams.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kui.cams.dao.ClassDao;
import kui.cams.dao.StudentDao;
import kui.cams.entity.Student;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

	@Resource(name="classDao")
	private ClassDao classDao;
	
	@Resource(name="studentDao")
	private StudentDao studentDao;
	
	@RequestMapping("/classLogin")
	public String classLogin(HttpServletRequest request) {
		System.out.println(request.getRequestedSessionId());
		System.out.println(request.getSession().getId());
		String c_no = request.getParameter("c_no");
		String password = request.getParameter("password");
		
		System.out.println("账号："+c_no+",密码："+password);
		kui.cams.entity.Class c = classDao.findClassByC_no(c_no);
		
		if(c==null) {
			return "c_no_false";
		}
		if(c.getPassword()!=null && !c.getPassword().equals(password)) {
			return "password_false";
		}
		HttpSession session = request.getSession();
		
		session.removeAttribute("s_id");
		session.removeAttribute("studentName");
		session.removeAttribute("password");
		session.setAttribute("c_no", c_no);
		session.setAttribute("className", c.getName());
		session.setAttribute("password", password);
		
		return "true";//账号密码正确
	}
	
	
	@RequestMapping("/studentLogin")
	public String studentLogin(HttpServletRequest request) {
		String s_id = request.getParameter("s_id");
		String password = request.getParameter("password");
		
		System.out.println("账号："+s_id+",密码："+password);
		Student student= studentDao.findStudentByS_id(s_id);
		
		if(student==null) {
			return "s_id_false";
		}
		if(student.getPassword()!=null && !student.getPassword().equals(password)) {
			return "password_false";
		}
		HttpSession session = request.getSession();
		session.removeAttribute("c_no");
		session.removeAttribute("className");
		session.removeAttribute("password");
		
		session.setAttribute("s_id", s_id);
		session.setAttribute("studentName", student.getName());
		session.setAttribute("password", password);
		
		return "true";//账号密码正确
	}
	
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		String s = (String)session.getAttribute("c_no");
		s+="密码："+ (String) session.getAttribute("password");
		
		return s;
	}
	
	/**
	 * 检查session是否已经包含班级用户登陆信息，如果包含，则返回当前班级对象
	 * 否则返回空
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkClassSession")
	public kui.cams.entity.Class checkClassSession(HttpSession session) {
		System.out.println(session.getId());
		String c_no =(String) session.getAttribute("c_no");
		kui.cams.entity.Class c = classDao.findClassByC_no(c_no);
		System.out.println(c);
		return c;
	}
	
	/**
	 * 检查session是否已经包含学生用户登陆信息，如果包含，则返回当前学生对象
	 * 否则返回空
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkStudentSession")
	public Student checkStudentSession(HttpSession session) {
		System.out.println(session.getId());
		String s_id =(String) session.getAttribute("s_id");
		Student student = studentDao.findStudentByS_id(s_id);
		//System.out.println(c);
		return student;
	}
	
	@RequestMapping("/classLogout")
	public String classLogout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		try {
			
			session.removeAttribute("c_no");
			session.removeAttribute("className");
			session.removeAttribute("password");
		}catch(Exception e) {
			return "false";
		}
		return "true";
	}
	
	@RequestMapping("/studentLogout")
	public String studentLogout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		try {
			
			session.removeAttribute("s_id");
			session.removeAttribute("studentName");
			session.removeAttribute("password");
		}catch(Exception e) {
			return "false";
		}
		return "true";
	}
}

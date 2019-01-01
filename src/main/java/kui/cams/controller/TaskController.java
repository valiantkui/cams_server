package kui.cams.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kui.cams.dao.ClassDao;
import kui.cams.dao.StudentDao;
import kui.cams.dao.TaskDao;
import kui.cams.entity.Task;
import kui.cams.tools.TermDateTool;

@CrossOrigin
@Controller
@RequestMapping("/task")
public class TaskController {

	@Resource(name="classDao")
	private ClassDao classDao;
	
	@Resource(name="taskDao")
	private TaskDao taskDao;
	
	@Resource(name="studentDao")
	private StudentDao studentDao;
	
	
	@RequestMapping("/findCurrentTermTask")
	@ResponseBody
	public List<Task> findCurrentTermTask(@RequestParam("c_no") String c_no){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String c_no = (String)session.getAttribute("c_no");
		//c_no="1";
		String enrolStr = classDao.findClassByC_no(c_no).getEnrol_date();
		List<Task> taskList = null;
		try {
			Date enrolDate = sdf.parse(enrolStr);
			String [] strArr = TermDateTool.getUpDownDate(new Date());
			String start_date = strArr[0];
			String end_date = strArr[1];
			taskList = taskDao.findCurrentTermTask(c_no, start_date, end_date);
			
			return taskList;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return taskList;
	}
	/**
	 * 根据学期和班级来获取所有作业信息
	 * @param termStr 学期号
	 * @param c_no班级编号
	 * @return 作业信息的集合
	 */
	@RequestMapping("/findTaskByTermC_no")
	@ResponseBody
	public List<Task> findTaskByTermC_no(@RequestParam("term") String termStr,String c_no){
		
		if(termStr == null || c_no == null) return null;
		List<Task> taskList = null;
		int term = Integer.parseInt(termStr);
		
		String enrolDate = classDao.findClassByC_no(c_no).getEnrol_date();
		
		try {
			String [] strArr = TermDateTool.getUpDownDate(term, new SimpleDateFormat("yyyy-MM-dd").parse(enrolDate));
			taskList = taskDao.findCurrentTermTask(c_no, strArr[0], strArr[1]);
			return taskList;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskList;
	}
	
	@RequestMapping("/publishTask")
	@ResponseBody
	public String publishTask(String subject,String content,String 	end_date ,HttpSession session) {
		String s_id = (String)session.getAttribute("s_id");
		String c_no = studentDao.findStudentByS_id(s_id).getC_no();
		
		Date now = new Date();
		String publish_date = new SimpleDateFormat("yyyy-MM-dd").format(now);
		Task task = new Task();
		task.setSubject(subject);
		task.setContent(content);
		task.setC_no(c_no);
		task.setPublish_date(publish_date);
		task.setUpdate_date(publish_date);
		task.setEnd_date(end_date);
		task.setS_id(s_id);
		
		try {
			
			if(taskDao.publishTask(task)<=0) return "false";
		}catch (Exception e) {
			return "db_false";
		}
		return "true";
	}
	
	@RequestMapping("/updateTask")
	@ResponseBody
	public String updateTask(@RequestParam("t_no") String tNoStr,String subject,String content,String 	end_date) {
		
		int t_no = Integer.parseInt(tNoStr);
		Date now = new Date();
		String update_date = new SimpleDateFormat("yyyy-MM-dd").format(now);
		Task task = new Task();
		task.setT_no(t_no);
		task.setSubject(subject);
		task.setContent(content);
		task.setUpdate_date(update_date);
		task.setEnd_date(end_date);
		try {
			if(taskDao.updateTask(task)<=0) return "false";
		}catch (Exception e) {
			return "db_false";
		}
		return "true";
	}
}

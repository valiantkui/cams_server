package kui.cams.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kui.cams.entity.Task;

@Repository("taskDao")
public interface TaskDao {

	/**
	 * 获取本班级当前学期的所有作业
	 * @param c_no 班级编号
	 * @param start_date 作业发布开始时间（判断条件的下限）
	 * @param end_date	作业发布结束时间（判断条件的上限）
	 * @return 作业的集合
	 */
	public List<Task> findCurrentTermTask(@Param("c_no") String c_no,@Param("start_date") String start_date,@Param("end_date") String end_date);
	
	public int publishTask(Task task);
	
	public int updateTask(Task task);
}

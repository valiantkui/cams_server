package kui.cams.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kui.cams.entity.Activity;

public interface ActivityDao {
	
	public int deleteActivityByA_no(int a_no);
	
	/**
	 * 获取本班级的指定标题的活动
	 * @param title
	 * @param c_no
	 * @return
	 */
	public List<Activity> searchActivityByTitle(@Param("title") String title,@Param("c_no") String c_no);
	public List<Activity> searchStudentActivityByTitle(@Param("title") String title,@Param("s_id") String s_id);
	public List<Activity> searchActivityByDate(@Param("date") String date,@Param("c_no") String c_no);
	public Activity findActivityByA_no(int a_no) ;
	
	/**
	 * 向数据库添加一个活动记录
	 * @param activity
	 */
	public void addActivity(Activity activity);
	
	public void updateActivity(Activity activity);

	public void updatePathByA_no(@Param("a_no") int a_no,@Param("participator_path") String participator_path);
	
	public List<Activity> findActivityByS_id(String s_id);
	/**
	 * 获取指定班级的指定月份的所有活动信息
	 * @param c_no
	 * @param month
	 * @return
	 */
	public List<Activity> findActivityByMonthC_no(@Param("c_no") String c_no,@Param("month") String month);
	
	
	
	/**
	 * 获取某班级指定日期的所有活动
	 * @param c_no
	 * @param month
	 * @return
	 */
	public List<Activity> findActivityByDayC_no(@Param("c_no") String c_no, @Param("day") String day);
	
	/**
	 * 获取某位学生发布的指定月份的所有活动信息
	 * @param s_id
	 * @param month
	 * @return
	 */
	public List<Activity> findActivityByMonthS_id(@Param("s_id") String s_id,@Param("month") String month);
	
	/**
	 * 获取某为学生指定日期的所有活动
	 * @param s_id
	 * @param day
	 * @return
	 */
	public List<Activity> findActivityByDayS_id(@Param("s_id") String s_id,@Param("day") String day);

	
}

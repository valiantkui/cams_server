package kui.cams.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kui.cams.entity.Student;

@Repository("studentDao")
public interface StudentDao {
	public List<Student> findStudentByS_idList(List<String> list);
	
	public List<Student> findStudentByC_no(String c_no);
	
	public Student findStudentByS_id(String s_id);
	
	//public Student findStudentByC_noS_id(String c_no,String s_id);
	public void addStudent(Student student);
	
	public void updateStudentByStudent(Student student);
	
	public void updateStudentPassword(@Param("s_id") String s_id,@Param("password") String password);
	
	public int updateStudentByClass(Student student);
	
	public int deleteStudentByS_id(String s_id);
}

package kui.cams.entity.extro;

import kui.cams.entity.Student;

public class StudentInfo{
	/**
	 * 学生
	 */
	private Student student;
	/**
	 * 团购数量
	 */
	private int number;
	
	
	public StudentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
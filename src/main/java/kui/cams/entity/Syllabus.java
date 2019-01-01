package kui.cams.entity;

public class Syllabus {

	private int s_no;
	private String name;
	private int term;
	private String all_subject;
	private String image_path;
	private String c_no;
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public String getAll_subject() {
		return all_subject;
	}
	public void setAll_subject(String all_subject) {
		this.all_subject = all_subject;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	@Override
	public String toString() {
		return "Syllabus [s_no=" + s_no + ", name=" + name + ", term=" + term + ", all_subject=" + all_subject
				+ ", image_path=" + image_path + ", c_no=" + c_no + "]";
	}
	
}

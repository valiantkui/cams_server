package kui.cams.entity;

public class Class {
	private String c_no;
	private String password;
	private String name;
	private String school;
	private String image_path;
	private String profession;
	private String enrol_date;
	private String tell;
	public String getEnrol_date() {
		return enrol_date;
	}
	public void setEnrol_date(String enrol_date) {
		this.enrol_date = enrol_date;
	}
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	@Override
	public String toString() {
		return "Class [c_no=" + c_no + ", password=" + password + ", name=" + name + ", school=" + school
				+ ", image_path=" + image_path + ", profession=" + profession + ", enrol_date=" + enrol_date + ", tell="
				+ tell + "]";
	}
	
	
}

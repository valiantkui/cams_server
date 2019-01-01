package kui.cams.entity;

public class Student {

	private String s_id;
	private String password;
	private String name;
	private String post;
	private String image_path;
	private String c_no;
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
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
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
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
		return "Student [s_id=" + s_id + ", password=" + password + ", name=" + name + ", post=" + post
				+ ", image_path=" + image_path + ", c_no=" + c_no + "]";
	}
	
}

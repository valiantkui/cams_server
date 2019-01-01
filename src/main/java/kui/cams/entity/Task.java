package kui.cams.entity;

public class Task {

	private int t_no;
	private String subject;
	private String content;
	private String publish_date;
	private String update_date;
	private String end_date;
	private String c_no;
	private String s_id;
	public int getT_no() {
		return t_no;
	}
	public void setT_no(int t_no) {
		this.t_no = t_no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	@Override
	public String toString() {
		return "Task [t_no=" + t_no + ", subject=" + subject + ", content=" + content + ", publish_date=" + publish_date
				+ ", update_date=" + update_date + ", end_date=" + end_date + ", c_no=" + c_no + ", s_id=" + s_id + "]";
	}
	
	
}

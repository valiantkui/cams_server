package kui.cams.entity;

public class Activity {
	private int a_no;
	private String title;
	private String content;
	private String publish_date;
	private String update_date;
	private String start_date;
	private String end_date;
	private String participator_path;
	private String c_no;
	private String s_id;
	public int getA_no() {
		return a_no;
	}
	public void setA_no(int a_no) {
		this.a_no = a_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getParticipator_path() {
		return participator_path;
	}
	public void setParticipator_path(String participator_path) {
		this.participator_path = participator_path;
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
		return "Activity [a_no=" + a_no + ", title=" + title + ", content=" + content + ", publish_date=" + publish_date
				+ ", update_date=" + update_date + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", participator_path=" + participator_path + ", c_no=" + c_no + ", s_id=" + s_id + "]";
	}
	
}

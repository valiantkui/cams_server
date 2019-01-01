package kui.cams.entity;

public class R_c_s {
	private int r_no;
	private String c_no;
	private String r_name;
	private String upload_date;
	private String s_id;
	
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	@Override
	public String toString() {
		return "R_c_s [r_no=" + r_no + ", c_no=" + c_no + ", r_name=" + r_name + ", upload_date=" + upload_date
				+ ", s_id=" + s_id + "]";
	}
	
}

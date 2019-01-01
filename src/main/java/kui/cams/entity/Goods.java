package kui.cams.entity;

public class Goods {

	private int g_no;
	private String name;
	private double price;
	private String participator_path;
	private int p_no;
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getParticipator_path() {
		return participator_path;
	}
	public void setParticipator_path(String participator_path) {
		this.participator_path = participator_path;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	@Override
	public String toString() {
		return "Goods [g_no=" + g_no + ", name=" + name + ", price=" + price + ", participator_path="
				+ participator_path + ", p_no=" + p_no + "]";
	}
	
	
}

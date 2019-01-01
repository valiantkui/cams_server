package kui.cams.entity.extro;

import java.util.List;

import kui.cams.entity.Goods;
import kui.cams.entity.Student;

/**
 * 团购详情，包含团购物品，以及每个物品的参与者信息
 * @author kui
 *
 */
public class GoodsInfo {

	/**
	 * 当前商品
	 */
	private Goods goods;
	/**
	 * 当前商品的参与学生信息
	 */
	private List<StudentInfo> studentInfoList;
	
	
	
	
	public Goods getGoods() {
		return goods;
	}




	public void setGoods(Goods goods) {
		this.goods = goods;
	}




	public List<StudentInfo> getStudentInfoList() {
		return studentInfoList;
	}




	public void setStudentInfoList(List<StudentInfo> studentInfoList) {
		this.studentInfoList = studentInfoList;
	}




	
}


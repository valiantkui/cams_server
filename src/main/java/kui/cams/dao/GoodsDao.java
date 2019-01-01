package kui.cams.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kui.cams.entity.Goods;

@Repository("goodsDao")
public interface GoodsDao {
	public List<Goods> findGoodsByP_no(int p_no);
	
	public int deleteGoodsByP_no(int p_no);
	
	public int addGoods(List<Goods> list);
	
	public int updatePath(List<Goods> list);

}

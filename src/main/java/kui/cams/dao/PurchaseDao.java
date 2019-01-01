package kui.cams.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kui.cams.entity.Purchase;

@Repository("purchaseDao")
public interface PurchaseDao {
	public List<Purchase> findPurchaseByC_no(String c_no);
	
	public List<Purchase> findInPurchaseByC_no(String c_no);
	
	public Purchase findPurchaseByP_no(int p_no);
	
	public List<Purchase> findPurchaseByS_id(String s_id);
	
	public int deletePurchaseByP_no(int p_no);
	
	public void publishPurchase(Purchase purchase);
		
}

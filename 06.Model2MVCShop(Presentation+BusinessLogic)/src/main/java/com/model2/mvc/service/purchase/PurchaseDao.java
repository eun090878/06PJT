package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	
	public void addPurchase(Purchase purchase) throws Exception;
	
	public Purchase findPurchase(int tranNo) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search searchVO, String buyerId)throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase2(int prodNo) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	

}

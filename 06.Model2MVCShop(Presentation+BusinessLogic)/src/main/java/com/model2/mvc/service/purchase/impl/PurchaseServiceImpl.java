package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	
	//Field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDaoImpl purchaseDao;
	
	//Constructor
	public PurchaseServiceImpl() {
		this.purchaseDao = new PurchaseDaoImpl();
	}
	
	//구매
	public void addPurchase(Purchase purchase) throws Exception{
		purchaseDao.addPurchase(purchase);
	}
	
	//구매정보 상세조회
	public Purchase getPurchase(int tranNo) throws Exception{
		return purchaseDao.findPurchase(tranNo);		
	}


	public Purchase getPurchase2(int prodNo) throws Exception{
		return purchaseDao.getPurchase2(prodNo);
	}

	//구매목록
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception{
		return purchaseDao.getPurchaseList(search, buyerId);
	}
/*	
	//판매목록
	public Map<String, Object> getSaleList(Search searchVO) throws Exception{
		return purchaseDao.getSaleList(searchVO);
	}
	*/
	//구매정보 수정
	public void updatePurchase(Purchase purchase) throws Exception{
		purchaseDao.updatePurchase(purchase);
	}
	
	//구매상태코드 수정
	public void updateTranCode(Purchase purchase) throws Exception{
		purchaseDao.updateTranCode(purchase);
	}
}

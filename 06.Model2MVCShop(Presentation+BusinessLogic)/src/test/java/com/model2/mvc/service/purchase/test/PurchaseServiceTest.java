package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)

// ==> Meta-Data 를 다양하게 Wiring 하자...
// @ContextConfiguration :: ApplicationContext의 참조가 테스트 인스턴스에 제공
@ContextConfiguration(locations = { "classpath:config/context-common.xml", "classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml", "classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		System.out.println("testAddProduct() 시작 :: ");
		Purchase purchase = new Purchase();
		
			String userId = "user01";
			User user = new User();
			UserService uservice = new UserServiceImpl();
			user = uservice.getUser(userId);
		
//		User user = new UserServiceImpl().getUser("user01");
		System.out.println("addPurchase : user 정보 ::"+ uservice.getUser("user01"));
		
/*		Product product = new Product();
		ProductService  pservice = new ProductServiceImpl();
		product = pservice.getProduct(Integer.parseInt("10021"));*/
		
		Product product = new ProductServiceImpl().getProduct(Integer.parseInt("10021"));
		System.out.println("addPurchase : product 정보 :: " + product);

		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("나는야태국이");
		purchase.setReceiverPhone("010-123-2345");
		purchase.setDlvyAddr("김포시");
		purchase.setDlvyRequest("태국까지배달되나요");
		purchase.setDlvyDate("2017-03-30");

		purchaseService.addPurchase(purchase);
		System.out.println("testAddProduct ::" + purchase);

		Assert.assertEquals("1", purchase.getPaymentOption());
		Assert.assertEquals("나는야태국이", purchase.getReceiverName());
		Assert.assertEquals("010-123-2345", purchase.getReceiverPhone());
		Assert.assertEquals("김포시", purchase.getDlvyAddr());
		Assert.assertEquals("태국까지배달되나요", purchase.getDlvyRequest());
		Assert.assertEquals("2017-03-30", purchase.getDlvyDate());

	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(Integer.parseInt("10088"));
		System.out.println("testGetPurchase :: purchase : "+ purchase);
		
		Assert.assertEquals(Integer.parseInt("10088"), purchase.getTranNo());
		Assert.assertNotNull(purchaseService.getPurchase(10088));
		
	}

	@Test
	public void testGetPurchaseListAll() throws Exception{
				 
		String userId = "user01";
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	Map<String,Object> map = purchaseService.getPurchaseList(search, "user01");
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(3, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setCurrentPage(2);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("");
		 	map = purchaseService.getPurchaseList(search,"user01");
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(3, list.size());
		 	
		 	//==> console 확인
		 	System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
	
		 	
	 }
	/*	 
		@Test
		 public void testGetProductListByProdNo() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("10140");
		 	Map<String,Object> map = productService.getProductList(search);
		 	System.out.println("ProductListByProdNo :: " + map);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
			System.out.println("productNo :: list " + map);
		 	
		 }
			 */

}

package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.product.ProductService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)

// ==> Meta-Data 를 다양하게 Wiring 하자...
// @ContextConfiguration :: ApplicationContext의 참조가 테스트 인스턴스에 제공
@ContextConfiguration(locations = { "classpath:config/context-common.xml", "classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml", "classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

//	@Test
	public void testAddProdut() throws Exception {
		
		System.out.println("testAddProduct() 시작 :: ");
		Product product = new Product();

		product.setProdName("사탕");
		product.setProdDetail("선물하기좋아요");
		product.setManuDate("2017-03-01");
		product.setPrice(Integer.parseInt("15000"));
		product.setFileName(null);

		productService.addProduct(product);
		System.out.println("testAddProduct ::" + product);

		// product = productService.getProduct("킨더초콜릿");

		// Assert.assertEquals(20000, product.getProdNo());
		Assert.assertEquals("사탕", product.getProdName());
		Assert.assertEquals("선물하기좋아요", product.getProdDetail());
		Assert.assertEquals("20170301", product.getManuDate());
		Assert.assertEquals(Integer.parseInt("15000"), product.getPrice());
		Assert.assertEquals(null, product.getFileName());

	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		
		product = productService.getProduct(Integer.parseInt("10140"));
		System.out.println("testGetProduct :: product : "+ product);
		
		Assert.assertEquals(Integer.parseInt("10140"), product.getProdNo());
		Assert.assertEquals("사탕", product.getProdName());
				
//		Assert.assertNotNull(productService.getProduct(10040));
//		Assert.assertNotNull(productService.getProduct(10041));
		
	}
	
		@Test
		 public void testGetProductListAll() throws Exception{
				 
			 	Search search = new Search();
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	Map<String,Object> map = productService.getProductList(search);
			 	
			 	List<Object> list = (List<Object>)map.get("list");
			 	Assert.assertEquals(3, list.size());
			 	
				//==> console 확인
			 	//System.out.println(list);
			 	
			 	Integer totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 	
			 	System.out.println("=======================================");
			 	
			 	search.setCurrentPage(1);
			 	search.setPageSize(3);
			 	search.setSearchCondition("0");
			 	search.setSearchKeyword("");
			 	map = productService.getProductList(search);
			 	
			 	list = (List<Object>)map.get("list");
			 	Assert.assertEquals(3, list.size());
			 	
			 	//==> console 확인
			 	//System.out.println(list);
			 	
			 	totalCount = (Integer)map.get("totalCount");
			 	System.out.println(totalCount);
			 }
		 
//		@Test
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
			 

}

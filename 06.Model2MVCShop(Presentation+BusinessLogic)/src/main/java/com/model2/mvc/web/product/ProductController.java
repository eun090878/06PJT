package com.model2.mvc.web.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {
	
	//Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(":: 여기는 "+this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addProductView.do")
	public String addProductView() throws Exception {
		
		System.out.println("/addProductView.do");
		
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct( @ModelAttribute("product") Product product ) throws Exception {
		
		System.out.println("/addProduct.do");
		
		productService.addProduct(product);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct( @RequestParam("prodNo") int prodNo,  @RequestParam("menu") String menu, Model model) throws Exception {
		
		System.out.println("/getProduct.do");
		
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		
		if( menu.equals("manage")) {
			return "forward:/product/updateProductView.jsp";
		} else {
			return "forward:/product/readProduct.jsp";
		}
		
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct( @ModelAttribute("productOld") Product product, Model model) throws Exception {
		
		System.out.println("상품정보 수정 후 수정된 정보 보여주기");
		System.out.println("/updateProduct.do");
		
		productService.updateProduct(product);
		product = productService.getProduct(product.getProdNo());
		
		//**@ModelAttribute("productOld") Product product로 가져온 정보(regDate이 없는 정보)로 수정을 한 후, 수정된 정보를 가져온다!
		//**그리고 그 수정된 정보를 Scope을 통해서 담아주고 readProduct.jsp로 전달
		model.addAttribute("product", product);
		
		System.out.println("수정된 product 정보 : " + product);
		
		return "/product/readProduct.jsp";
	}
	
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("prodNo") int prodNo, Model  model) throws Exception {
		
		System.out.println("상품정보를 수정하기위한 View");
		System.out.println("/updateProductView.do");
		
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping("listProduct.do")
	public String listProduct( @ModelAttribute("search") Search search, @RequestParam("menu") String menu, Model model ) throws Exception{
		
		System.out.println("/listProduct.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
		
}

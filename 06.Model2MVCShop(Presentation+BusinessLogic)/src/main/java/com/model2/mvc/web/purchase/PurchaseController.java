package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
public class PurchaseController {
	
	//Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	///////////////////////////////////////////
	//��û���� �߿���! 
	//Autowired�� �����ʰ� �����ڷ� ������ ���, serviceImpl������ ������ �����ϰ�,
	//DaoImpl���� ���� 
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//////////////////////////////////////////
	
	public PurchaseController() {
		System.out.println(":: ����� "+this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml ���� �Ұ�
	//==> �Ʒ��� �ΰ��� �ּ��� Ǯ�� �ǹ̸� Ȯ�� �Ұ�
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView( @RequestParam("prodNo") int prodNo, Model model) throws Exception {
		
		System.out.println("/addPurchaseView.do");
		
		Product product =productService.getProduct(prodNo);
		
		System.out.println("addPurchaseView.do : product���� :: " + product);
		model.addAttribute("product", product);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase( @RequestParam("prodNo") int prodNo, @RequestParam("buyerId") String buyerId, @ModelAttribute("purchase") Purchase purchase) throws Exception {
		
		System.out.println("/addPurchase.do");
		
		User user = userService.getUser(buyerId);
		Product product = productService.getProduct(prodNo);
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchaseService.addPurchase(purchase);
				
		return "forward:/purchase/addPurchase.jsp";
	}
	
	@RequestMapping("/getPurchase.do")
	public String getPurchase( @RequestParam("tranNo") int tranNo,  Model model) throws Exception {
		
		System.out.println("/getProduct.do");
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		System.out.println("purchase ���� :: " + purchase);
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/getPurchase.jsp";
		
	}
	
	@RequestMapping("/updatePurchase.do")
	public String updatePurchase( @RequestParam("tranNo") int tranNo, HttpSession session, @ModelAttribute("purchase") Purchase purchase, Model model) throws Exception {
		
		System.out.println("��ǰ���� ���� �� ������ ���� �����ֱ�");
		System.out.println("/updatePurchase.do");
		
		String userId=((User)session.getAttribute("user")).getUserId();
		User user = userService.getUser(userId);
//		Product product = productService.getProduct(prodNo);
		
		purchase.setBuyer(user);
//		purchase.setPurchaseProd(product);
		
		purchaseService.updatePurchase(purchase);
		purchase = purchaseService.getPurchase(tranNo);
		
		//**@ModelAttribute("productOld") Product product�� ������ ����(regDate�� ���� ����)�� ������ �� ��, ������ ������ �����´�!
		//**�׸��� �� ������ ������ Scope�� ���ؼ� ����ְ� readProduct.jsp�� ����
		model.addAttribute("purchase", purchase);
		
		System.out.println("������ purchase ���� : " + purchase);
		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	
	@RequestMapping("/updatePurchaseView.do")
	public String updatePurchaseView(@RequestParam("tranNo") int tranNo, Model  model) throws Exception {
		
		System.out.println("���������� �����ϱ����� View");
		System.out.println("/updatePurchaseView.do");
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}

	@RequestMapping("listPurchase.do")
	public String listPurchase( @ModelAttribute("search") Search search, HttpSession session, Model model ) throws Exception{
		
		System.out.println("/listPurchase.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		String sessionId=((User)session.getAttribute("user")).getUserId();
		
		// Business logic ����
		Map<String , Object> map=purchaseService.getPurchaseList(search, sessionId);
		System.out.println("listPurchase() : map�� ���� �����̳� :: " + map);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
		
	@RequestMapping("/updateTranCodeByProd.do")
	public String updateTranCodeByProd(@RequestParam("tranCode") String tranCode, @RequestParam("prodNo") int prodNo) throws Exception{
		
		System.out.println("/updateTranCodeByProd.do");
		
		Purchase purchase = purchaseService.getPurchase2(prodNo);
		purchaseService.updateTranCode(purchase);
		
	return  "redirect:/listProduct.do?menu=manage";
	}
	
	@RequestMapping("/updateTranCode.do")
	public String updateTranCode(@RequestParam("tranNo") int tranNo) throws Exception{
		
		System.out.println("/updateTranCode.do");
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		purchaseService.updateTranCode(purchase);
		
		return "redirect:/listPurchase.do";
	}
	
}

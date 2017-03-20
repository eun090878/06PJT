package com.model2.mvc.service.domain;

import java.sql.Date;


public class Product {
	
	private int prodNo;							//상품번호
	private String prodName;			//상품명
	private String fileName;				//상품이미지의 파일이름
	private String prodDetail;			//상품상제정보
	private String manuDate;				//상품 제조일자
	private int price;								//상품가격	
	private Date regDate;						//상품등록일자
	private String proTranCode;		//구매상태코드
	private String menu1;
	private String menu2;
	private String menu3;
	
	public Product(){
	}
	
	public String getProTranCode() {
		return proTranCode;
	}
	public void setProTranCode(String proTranCode) {
		this.proTranCode = proTranCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		if(manuDate != null && manuDate.length() != 0) {
			String[] parsing= manuDate.split("-");
			manuDate="";
			for(int i=0; i<parsing.length; i++)	{
				manuDate += parsing[i];
			}
			this.manuDate = manuDate;

	}
	
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getMenu1() {
		return menu1;
	}
	public String getMenu2() {
		return menu2;
	}
	public String getMenu3() {
		return menu3;
	}
	

	// Override
	public String toString() {
		return "ProductVO : [fileName]" + fileName
				+ "[manuDate]" + manuDate+ "[price]" + price + "[prodDetail]" + prodDetail + "[regDate]" + regDate
				+ "[prodName]" + prodName + "[prodNo]" + prodNo + "[proTranCode]" + proTranCode;
	}	
}
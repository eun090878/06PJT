package com.model2.mvc.service.domain;

import java.sql.Date;

public class Purchase {

	//Field
	private int tranNo;									//구매번호
	private String tranCode;						//구매상태코드
	private String receiverPhone;			//받는사람 전화번호
	private String receiverName;				// 받는사람 이름
	private Product purchaseProd;			// 구매물품 정보
	private String paymentOption;			//지불방식
	private Date orderDate;						//구매일자
	private String dlvyRequest;					//배송시 요구사항
	private String dlvyDate;						//배송희망일자
	private String dlvyAddr;						//배송지주소
	private User buyer;									//구매자정보
	
	
	public Purchase() {
	}

	public int getTranNo() {
		return tranNo;
	}

	public String getTranCode() {
		return tranCode;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public Product getPurchaseProd() {
		return purchaseProd;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public String getDlvyRequest() {
		return dlvyRequest;
	}

	public String getDlvyDate() {
		return dlvyDate;
	}

	public String getDlvyAddr() {
		return dlvyAddr;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setDlvyRequest(String dlvyRequest) {
		this.dlvyRequest = dlvyRequest;
	}

	public void setDlvyDate(String dlvyDate) {
		this.dlvyDate = dlvyDate;
	}

	public void setDlvyAddr(String dlvyAddr) {
		this.dlvyAddr = dlvyAddr;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return "Purchase [tranNo=" + tranNo + ", tranCode=" + tranCode + ", receiverPhone=" + receiverPhone
				+ ", receiverName=" + receiverName + ", purchaseProd=" + purchaseProd + ", paymentOption="
				+ paymentOption + ", orderDate=" + orderDate + ", dlvyRequest=" + dlvyRequest + ", dlvyDate=" + dlvyDate
				+ ", dlvyAddr=" + dlvyAddr + ", buyer=" + buyer + "]";
	}

}

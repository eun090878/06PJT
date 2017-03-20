<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
	function fncGetAllList(currentPage){
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();	
	}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<%-- <form name="detailForm" action="/listProduct.do?menu=<%= request.getParameter("menu") %>" method="post">  --%>
<form name="detailForm" action="/listProduct.do?menu=${param.menu}" method="post">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<c:if test="${ param.menu == 'manage' }" >
					<td width="93%" class="ct_ttl01">상품 관리</td>
				</c:if>
				<c:if test="${ param.menu == 'search' }" >
					<td width="93%" class="ct_ttl01">상품 목록조회</td>
				</c:if>
				
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>
<!-- 검색 부분 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				
				<option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
				<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
				<option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
			</select>
			<input type="text" name="searchKeyword" value="${! empty search.searchKeyword ? search.searchKeyword : ""}"  
			class="ct_input_g" style="width:200px; height:19px" />
		</td>

		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetAllList('${resultPage.currentPage}');">검색</a>
						<!-- 	현재페이지 1부터 시작. -->
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >
			<%-- 전체 <%= resultPage.getTotalCount() %> 건수, 현재 <%= resultPage.getCurrentPage() %> 페이지 --%>
			전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
<%-- 	<% 	
		for(int i=0; i<list.size(); i++) {
			Product vo = list.get(i);
	%>		
	<tr class="ct_list_pop">
		<td align="center"><%=i + 1%></td>
		<td></td>				
			<td align="left">
		<a href=" /getProduct.do?prodNo=<%=vo.getProdNo() %>&menu=manage"><%= vo.getProdName() %></a></td>
				<a href=" /getProduct.do?prodNo=<%=vo.getProdNo() %>&menu=<%= request.getParameter("menu") %>"><%= vo.getProdName() %></a></td>
				<!-- 상품명을 클릭했을때, 상품번호와 상품관리/상품검색으로 보냄. 일단 상품관리로 먼저 보내보자 -->

		<td></td>
		<td align="left"><%= vo.getPrice() %></td>
		<td></td>
		<td align="left"><%= vo.getManuDate() %></td>
		<td></td>
		<td align="left"><%= vo.getProTranCode() %></td>
	</tr> 
	
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	<% } %>--%>
	<%-- /////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// --%>
		<c:set var="i" value="0" />
		<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center">${ i }</td>
			<td></td>
			<td align="left">
				<%-- <a href="/getUser.do?userId=${user.userId}">${user.userId}</a></td> --%>
				<c:if test="${product.proTranCode.trim() =='0'}">
					<a href=" /getProduct.do?prodNo=${product.prodNo}&menu=${param.menu}">	${product.prodName}</a></td>
				</c:if>
				<c:if test="${product.proTranCode.trim() !='0'}">
					${product.prodName}</td>
				</c:if>
				
	<%-- 			${product.prodName}</td> --%>
			<td></td>
			<td align="left">${product.price}</td>
			<td></td>
			<td align="left">${product.regDate}</td>			
			<td></td>
			<td align="left">
				<c:if test="${param.menu=='search'}">
					<c:choose>
						<c:when test="${product.proTranCode.trim()=='0'}">
							판매중
							<br/>
							</c:when>
							<c:otherwise>
								재고없음
								<br/>
							</c:otherwise>
						</c:choose>
					</c:if>
				
						<c:if test="${param.menu=='manage'}">
							<c:choose>
								<c:when test="${product.proTranCode.trim()=='0' }">
									판매중
									<br/>
								</c:when>
								<c:when test="${product.proTranCode.trim()=='1' }">
									구매완료
									<a href="/updateTranCodeByProd.do?prodNo=${product.prodNo}&tranCode=${product.proTranCode}">배송하기</a>
								</c:when>
								<c:when test="${product.proTranCode.trim()=='2' }">
									배송중
									<br/>
									</c:when>
									<c:when test="${product.proTranCode.trim()=='3' }">
										배송완료
										<br/>
									</c:when>
							</c:choose>
						</c:if>
				</td>
			
			<td></td>			
			</td>		
		</tr>
		<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
</table>


<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=" ${resultPage.currentPage}"/>
				
<%-- 			<c:if test="${resultPage.currentPage} <= ${resultPage.pageUnit }" >
						◀ 이전
				</c:if>
				<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
					<a href="javascript:fncGetProductList('${resultPage.currentPage-1}')">◀ 이전</a>
				</c:if>

				<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
					<a href="javascript:fncGetProductList('${i}');">${i}</a>
				</c:forEach>
	
				<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
						이후 ▶ 
				</c:if>
				<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
						<a href="javascript:fncGetProductList('${resultPage.endUnitPage+1}')">이후 ▶</a>
				</c:if> 
--%>
				<jsp:include page="../common/pageNavigator.jsp"/>	
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>
</div>
</body>
</html>

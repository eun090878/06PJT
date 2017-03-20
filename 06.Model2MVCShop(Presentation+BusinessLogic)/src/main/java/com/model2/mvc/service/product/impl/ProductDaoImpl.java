package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

//상품관리 DAO CRUD 구현
@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
	
	//Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public ProductDaoImpl() {
		System.out.println("ProductDaoImpl default Constructor :: " + this.getClass());
	}
	
	public void addProduct(Product product) throws Exception{
		sqlSession.insert("ProductMapper.addProduct", product);
	}
	
	public Product getProduct(int prodNo) throws Exception {
		System.out.println("getProduct Dao :: ");
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	public List<Product> getProductList(Search search) throws Exception {
			return sqlSession.selectList("ProductMapper.getProductList", search);
	}

	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
}

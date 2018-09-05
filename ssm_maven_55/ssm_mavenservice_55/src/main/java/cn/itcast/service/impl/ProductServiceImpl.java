package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.ProductDao;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.util.PageBean;
/*
 * 事务的注解实现
 * @Transactional + 配置文件的 tx:annotation-driven
 * */
@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public PageBean<Product> findAllProduct(Integer pageNum,Integer pageSize) {
		//封装得到返回的PageBean对象
		PageBean<Product> pb  = new PageBean<Product>();
		pb.setPageNum(pageNum);
		pb.setPageSize(pageSize);
		//调用dao得到总记录数
		Integer totalCount = productDao.findTotalCount();
		pb.setTotalCount(totalCount);
		/*
		 * totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		 * */
		Integer totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		pb.setTotalPage(totalPage);
		/* 6--10 2页 每页5条记录
		 * dao得到分页的查询结果集
		 * 分页的sql语句分析 
		 * mySql的分页sql  select * from product limit startIndex pageSize
		 * startIndex = (pageNum-1)*pageSize 
		 * oracle的分页一定是rownum的子查询实现
		 *select * from  (select rownum r,p.* from product p) t where t.r>startIndex and t.r<=endIndex
		 * endIndex = pageNum*pageSize 
		 * */
		Integer startIndex = (pageNum-1)*pageSize ;
		Integer endIndex = pageNum*pageSize ;
		List<Product> list = productDao.findAllProduct(startIndex,endIndex);
		pb.setList(list);
		
		return pb;
	}

	@Override
	public void saveProduct(Product product) {
		
		productDao.saveProduct(product);
		
	}

	@Override
	public Product findProductById(Integer productId) {
		
		return productDao.findProductById(productId);
	}

	@Override
	public void updateProduct(Product product) {
		
		productDao.updateProduct(product);
	}

	@Override
	public void deleteProductById(Integer productId) {
		
		productDao.deleteById(productId);
	}

}

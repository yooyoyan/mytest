package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Product;
import cn.itcast.util.PageBean;

public interface ProductService {
	
	public PageBean<Product> findAllProduct(Integer pageNum, Integer pageNum2);

	public void saveProduct(Product product);

	public Product findProductById(Integer productId);

	public void updateProduct(Product product);

	public void deleteProductById(Integer productId);

}

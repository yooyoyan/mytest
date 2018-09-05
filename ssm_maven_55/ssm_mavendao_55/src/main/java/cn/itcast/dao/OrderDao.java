package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import cn.itcast.domain.Orders;
import cn.itcast.domain.Product;

public interface OrderDao {

	//查询订单列表 和订单所属的产品名称
	@Select("select * from orders")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="productId",property="product",javaType=Product.class,
		one=@One(select="cn.itcast.dao.ProductDao.findProductById")),
	})
	public List<Orders> findAllOrders();
}

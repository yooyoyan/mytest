package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.itcast.domain.Product;
/*
 * 注解实现查询
 * */
public interface ProductDao {

	//查询所有的产品数据
	/*select * from  (select rownum r,p.* from product p) t where t.r>startIndex and t.r<=endIndex
	 * 参数的获取方式
	 * 基本类型 任意参数名都可以获取  只限一个
	 * 对象类型 属性名获取参数
	 * 
	 * */
	@Select("select * from  (select rownum r,p.* from product p where rownum <=#{endIndex}) t where t.r>#{startIndex} ")
	public List<Product> findAllProduct(@Param("startIndex") Integer startIndex, @Param("endIndex") Integer endIndex);

	//保存数据到数据库
	@Insert("insert into product values(common_sequence.nextval,"
			+ "#{productNum},#{productName},#{cityName},#{departureTime},"
			+ "#{productPrice},#{productDesc},#{productStatus})")
	public void saveProduct(Product product);

	@Select("select * from product where id = #{id}")
	public Product findProductById(Integer productId);

	@Update("update product set productName=#{productName},"
			+ " cityName=#{cityName},departureTime=#{departureTime},"
			+ " productPrice=#{productPrice} ,productDesc=#{productDesc},productStatus=#{productStatus}"
			+ " where id = #{id}")
	public void updateProduct(Product product);

	@Delete("delete from product where id = #{id}")
	public void deleteById(Integer productId);
	//统计所有数量用于分页
	@Select("select count(1) from product ")
	public Integer findTotalCount();
}

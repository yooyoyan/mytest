package cn.itcast.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.Orders;

public interface OrderService {

	//查询所有订单
	public PageInfo<Orders> findAllOrders(Integer pageNum, Integer pageSize);
}

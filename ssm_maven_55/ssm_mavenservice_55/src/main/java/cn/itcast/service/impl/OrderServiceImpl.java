package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.dao.OrderDao;
import cn.itcast.domain.Orders;
import cn.itcast.service.OrderService;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public PageInfo<Orders> findAllOrders(Integer pageNum,Integer pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		//list本质是个page对象
		List<Orders> list = orderDao.findAllOrders();
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
		return pageInfo;
	}

}

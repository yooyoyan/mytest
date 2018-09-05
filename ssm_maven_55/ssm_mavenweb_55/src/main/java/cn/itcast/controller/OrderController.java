package cn.itcast.controller;


import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import cn.itcast.domain.Orders;
import cn.itcast.domain.Product;
import cn.itcast.service.OrderService;
import cn.itcast.service.ProductService;
import cn.itcast.util.DateUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	/*
	 * 改造成插件实现分页效果
	 * */
	@RequestMapping("/findAllOrder")
	public String findAllOrder(Model model,
			@RequestParam(defaultValue="1") Integer pageNum,
			@RequestParam(defaultValue="3") Integer pageSize) {
		//调用service得到封装后的分页对象
		PageInfo<Orders> pi = orderService.findAllOrders(pageNum,pageSize);
		//跳转到jsp页面显示
		model.addAttribute("pageInfo", pi);
		return "order/orderList";
	}
	
}

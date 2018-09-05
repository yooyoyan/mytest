package cn.itcast.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.util.DateUtil;
import cn.itcast.util.PageBean;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	/*
	 * 自定义属性编辑器处理日期参数
	 * @InitBinder
	public void initBind(WebDataBinder binder) {
		//requiredType 需要转换的类型
		binder.registerCustomEditor(Date.class, new PropertiesEditor() {

			@Override
			public void setAsText(String dateStr) throws IllegalArgumentException {
				//将dateStr转换成日期 赋值给属性
				Date date = DateUtil.parseStrToDate(dateStr);
				setValue(date);
			}
			
			
		});
	}
	 * */
	
	
	@Autowired
	private ProductService productService;
	
	/*
	 * 接受参数分页获取数据
	 * 
	 * */
	@RequestMapping("/findAllProduct")
	public String findAllProduct(Model model,
		@RequestParam(defaultValue="1") Integer pageNum,
		@RequestParam(defaultValue="3") Integer pageSize) {
		//securityContext对象的获取
		SecurityContext context = SecurityContextHolder.getContext();
		//获取存储的user对象
		User user = (User) context.getAuthentication().getPrincipal();
		System.out.println("当前登录的用户为：====="+user.getUsername());
		
		//调用service得到封装的分页对象
		PageBean<Product> pb = productService.findAllProduct(pageNum,pageSize);
		//传递到jsp展示
		model.addAttribute("pb", pb);
		return "product/productList";
	}
	/*
	 * 接受请求跳转添加页面
	 * */
	@RequestMapping("/addProductUI")
	public String addProductUI() {
		
		return "product/productAdd";
	}
	/*
	 * 接受产品参数
	 * 保存到数据库
	 * */
	@RequestMapping("/saveProduct")
	public String saveProduct(Product product) {
		//调用servicve的保存方法
		productService.saveProduct(product);
		//保存成功跳转到list列表展示所有产品
		/* redirect 重定向  数据丢失     两次请求
		 * forward  转发      数据不丢失 一次请求
		 *   forward可以重复提交表单  （不要使用）
		 * */
		return "redirect:/product/findAllProduct";
	}
	/*接受产品的id
	 * 跳转页面显式被修改的产品数据
	 * 
	 * */
	@RequestMapping("/updateProductUI")
	public String updateProductUI(Integer productId,Model model) {
		//查询产品对象
		Product product = productService.findProductById(productId);
		//存入model对象
		model.addAttribute("product", product);
		//跳转的编辑页面
		return "product/productUpdate";
	}
	
	/*
	 * 接受修改的产品对象
	 * 更新到数据库
	 * */
	@RequestMapping("/updateProduct")
	public String updateProduct(Product product) {
		
		productService.updateProduct(product);
		
		return "redirect:/product/findAllProduct";
	}
	
	/*
	 * 根据id删除产品数据
	 * 
	 * 
	 * */
	@RequestMapping("/deleteProduct")
	public String deleteProduct(Integer productId) {
		
		productService.deleteProductById(productId);
		
		return "redirect:/product/findAllProduct";
	}
	/*
	 * 批量删除多条产品
	 * */
	@RequestMapping("/deleteAll")
	public String deleteAll(Integer [] ids) {
		//循环数组 循环一次调用一次删除
		if(null!=ids&&ids.length>0) {
			for(Integer id:ids) {
				productService.deleteProductById(id);
			}
		}
		//循环数组拼接参数 1 ,2 ,3 dao sql delete form product where id  in 
		return "redirect:/product/findAllProduct";
	}
	
}

package cn.itcast.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.domain.SysRole;
import cn.itcast.domain.SysUser;
import cn.itcast.service.SysRoleService;
import cn.itcast.service.SysUserService;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysRoleService roleService;
	/*
	 * 改造成插件实现分页效果
	 * */
	@RequestMapping("/findAllUser")
	public String findAllUser(Model model) {
		
		
		model.addAttribute("userList", userService.findAllUser());
		//执行异常 提示信息详细 访问的方法 传递的参数 调用的方法 出现什么异常
		
		return "user/userList";
	}
	/*
	 * 接受请求跳转添加用户的页面
	 * 
	 * */
	@RequestMapping("/userAddUI")
	public String userAddUI() {
		
		return "user/userAdd";
		
	}
	/*
	 * 接受用户信息
	 * 存储到数据库
	 * */
	@RequestMapping("/userAdd")
	public String userAdd(SysUser user) {
		
		userService.saveUser(user);
		
		return "redirect:/sysUser/findAllUser";
		
	}
	/*接受用户id
	 * 查询数据
	 * 用户的角色信息
	 * 所有的角色信息
	 * 
	 * */
	@RequestMapping("/managerUserRole")
	public String managerUserRole(Integer userId,Model model) {
		//查询用信息
		SysUser user = userService.findUserByUserId(userId);
		//查询用户的所有角色
		List<SysRole> userRoles = user.getRoles();
		//将所有的用户角色拼接成一个字符串 
		//ROLE_ADMIN,ROLE_USER,ROLE_PRODUCT,
		if(null!=userRoles&&userRoles.size()>0) {
			StringBuilder sb = new StringBuilder();
			for(SysRole role:userRoles) {
				sb.append(role.getRoleName()+",");
			}
			model.addAttribute("roleStr", sb.toString());
		}
		//数据库所有的角色信息
		List<SysRole> roles = roleService.findAllRole();
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		
		return "user/managerUserRole";
		
	}
	/*
	 * 接受页面的参数传递
	 * 传递多个角色地 以数组形式传递过来
	 * 用户的id
	 * */
	@RequestMapping("/managerRoleToUser")
	public String managerRoleToUser(Integer userId,Integer [] ids) {
		roleService.managerUserRole(userId,ids);
		
		return "redirect:/sysUser/findAllUser";
	}
	/*
	 * 传递用户的id查询用户的详情
	 * */
	@RequestMapping("/userDetail")
	public String userDetail(Integer userId,Model model) {
		SysUser user = userService.findUserByUserId(userId);
		model.addAttribute("user", user);
		return "user/userDetail";
	}
}

package cn.itcast.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.domain.Permission;
import cn.itcast.domain.SysRole;
import cn.itcast.domain.SysUser;
import cn.itcast.service.PermissionService;
import cn.itcast.service.SysRoleService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	/*
	 * 
	 * */
	@RequestMapping("/findAllPermission")
	public String findAllPermission(Model model) {
		
		model.addAttribute("pList", permissionService.findAllPermission());
		
		return "permission/pList";
	}
	/*
	 * 跳转添加权限的页面
	 * */
	@RequestMapping("/addPermissionUI")
	public String addPermissionUI() {
		
		return "permission/addPermission";
	}
	/*
	 * 接受权限保存到数据库
	 * */
	@RequestMapping("/addPermission")
	public String addPermission(Permission permission) {
		
		permissionService.savePermission(permission);
		//查询列表的展示
		return "redirect:/permission/findAllPermission";
	}
	
}

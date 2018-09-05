package cn.itcast.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.domain.Permission;
import cn.itcast.domain.SysRole;
import cn.itcast.service.PermissionService;
import cn.itcast.service.SysRoleService;

@Controller
@RequestMapping("/sysRole")
/*
 * jsr250的注解拦截权限 @RolesAllowed("ROLE_ADMIN")
 * securiy注解表达式 @Secured("ROLE_ADMIN")
 * 表达式的注解实现 @PreAuthorize("hasRole('ROLE_ADMIN')")
 * */
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SysRoleController {
	
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private PermissionService pService;
	/*
	 * 
	 * */
	@RequestMapping("/findAllRole")
	public String findAllRole(Model model) {
		
		model.addAttribute("roleList", roleService.findAllRole());
		
		return "role/roleList";
	}
	
	/*
	 * 接受请求跳转添加页面
	 * */
	@RequestMapping("/addRoleUI")
	public String addRoleUI() {
		
		return "role/addRole";
	}
	/*
	 * 接受角色信息存储到数据库
	 * */
	@RequestMapping("/addRole")
	public String addRole(SysRole role) {
		roleService.saveRole(role);
		return "redirect:/sysRole/findAllRole";
	}
	/*
	 * 接受角色的id
	 * 跳转管理角色权限页面
	 * */
	@RequestMapping("/managerRolePermissionUI")
	public String managerRolePermissionUI(Integer roleId,Model model) {
		
		SysRole role = roleService.findRoleByRoleId(roleId);
		model.addAttribute("role", role);
		//得到角色的权限
		List<Permission> pList =role.getPermissions();
		//循环遍历所有的权限拼装成一个字符串用于页面的包含判断
		if(pList!=null&&pList.size()>0) {
			StringBuilder sb = new StringBuilder();
			for(Permission p:pList) {
				sb.append(p.getPermissionName()+",");
			}
			model.addAttribute("pStr", sb.toString());
		}
		//所有的权限
		List<Permission> allPList = pService.findAllPermission();
		model.addAttribute("allPList", allPList);
		return "role/managerRolePermission";
	}
	/*
	 * 接受角色的id
	 * 数组形式的权限id
	 * 管理角色的权限
	 * */
	@RequestMapping("/managerRolePermission")
	public String managerRolePermission(Integer roleId,Integer [] ids) {
		
		roleService.managerRolePermission(roleId,ids);
		
		return "redirect:/sysRole/findAllRole";
	}
	
}

package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Permission;

public interface PermissionService {
	
	//查询所有的权限
	public List<Permission> findAllPermission();

	public void savePermission(Permission permission);
}

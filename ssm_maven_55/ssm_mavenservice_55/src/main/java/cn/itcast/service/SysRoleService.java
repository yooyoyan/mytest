package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.SysRole;

public interface SysRoleService {

	public List<SysRole> findAllRole();

	public void saveRole(SysRole role);

	public void managerUserRole(Integer userId, Integer[] ids);

	public SysRole findRoleByRoleId(Integer roleId);

	public void managerRolePermission(Integer roleId, Integer[] ids);
}

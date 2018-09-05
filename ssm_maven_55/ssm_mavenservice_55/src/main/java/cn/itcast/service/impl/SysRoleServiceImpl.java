package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.SysRoleDao;
import cn.itcast.domain.SysRole;
import cn.itcast.service.SysRoleService;
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao roleDao;
	
	@Override
	public List<SysRole> findAllRole() {
		// TODO Auto-generated method stub
		return roleDao.findAllRole();
	}

	@Override
	public void saveRole(SysRole role) {
		
		roleDao.saveRole(role);
		
	}

	@Override
	public void managerUserRole(Integer userId, Integer[] ids) {
		//先删除原始的角色信息
		roleDao.deleteRolesFromUser(userId);
		//再添加用户真正的角色
		if(null!=ids&&ids.length>0) {
			for(Integer roleId:ids) {
				roleDao.saveUserRole(userId,roleId);
			}
		}
		
	}

	@Override
	public SysRole findRoleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.findRoleByRoleId(roleId);
	}

	@Override
	public void managerRolePermission(Integer roleId, Integer[] ids) {
		//1.移除原始的记录
		roleDao.deletePermissionFromRole(roleId);
		//2.判断数组id循环添加数据
		if(null!=ids&&ids.length>0) {
			for(Integer pId:ids) {
				roleDao.saveRolePermission(roleId,pId);
			}
		}
		
	}

}

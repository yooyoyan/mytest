package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.PermissionDao;
import cn.itcast.domain.Permission;
import cn.itcast.service.PermissionService;
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public List<Permission> findAllPermission() {
		// TODO Auto-generated method stub
		return permissionDao.findAllPermission();
	}

	@Override
	public void savePermission(Permission permission) {
		
		permissionDao.savePermission(permission);
		
	}

}

package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.itcast.domain.Permission;

public interface PermissionDao {
	
	//查询所有的权限
	@Select("select * from sys_permission")
	public List<Permission> findAllPermission();

	@Insert("insert into sys_permission values(common_sequence.nextval,#{permissionName},#{url})")
	public void savePermission(Permission permission);
	
	@Select("select * from sys_permission where id in " + 
			"  (select permissionid from sys_role_permission where roleid =#{roleId})")
	public List<Permission> findPermissionsByRoleId(Integer roleId);
}

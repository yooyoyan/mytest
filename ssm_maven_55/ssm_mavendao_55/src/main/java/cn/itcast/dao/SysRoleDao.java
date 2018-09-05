package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.itcast.domain.SysRole;

public interface SysRoleDao {
	
	//查询所有的角色
	@Select("select * from sys_role")
	public List<SysRole> findAllRole();

	@Insert("insert into sys_role values(common_sequence.nextval,#{roleName},#{roleDesc})")
	public void saveRole(SysRole role);
	
	@Select("select * from sys_role where id in " + 
			"     (select roleid from sys_user_role where userid= #{userId})")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="permissions",javaType=List.class,
		many=@Many(select="cn.itcast.dao.PermissionDao.findPermissionsByRoleId"))
	})
	public List<SysRole> findRolesByUserId(Integer userId);

	@Insert("insert into sys_user_role values(#{userId},#{roleId})")
	public void saveUserRole(@Param("userId") Integer userId, @Param("roleId")Integer roleId);

	@Delete("delete from sys_user_role where userid=#{userId}")
	public void deleteRolesFromUser(Integer userId);

	@Select("select * from sys_role where id=#{roleId}")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="permissions",javaType=List.class,
		many=@Many(select="cn.itcast.dao.PermissionDao.findPermissionsByRoleId"))
	})
	public SysRole findRoleByRoleId(Integer roleId);

	@Delete("delete from sys_role_permission where roleid=#{roleid}")
	public void deletePermissionFromRole(Integer roleId);

	@Insert("insert into sys_role_permission values(#{pId},#{roleId})")
	public void saveRolePermission(@Param("roleId")Integer roleId, @Param("pId")Integer pId);
}

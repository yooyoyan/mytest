package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.itcast.domain.SysUser;

public interface SysUserDao {

	@Select("select * from sys_user where username=#{name}")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="roles",javaType=List.class,
		many=@Many(select="cn.itcast.dao.SysRoleDao.findRolesByUserId"))
	})
	public SysUser findUserByName(String username);

	@Select("select * from sys_user")
	public List<SysUser> findAllUser();

	@Insert("insert into sys_user values(common_sequence.nextval,"
			+ "#{username},#{email},#{password},#{phoneNum},#{status})")
	public void saveUser(SysUser user);

	@Select("select * from sys_user where id = #{userId}")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="id",property="roles",javaType=List.class,
		many=@Many(select="cn.itcast.dao.SysRoleDao.findRolesByUserId"))
	})
	public SysUser findUserByUserId(Integer userId);

}

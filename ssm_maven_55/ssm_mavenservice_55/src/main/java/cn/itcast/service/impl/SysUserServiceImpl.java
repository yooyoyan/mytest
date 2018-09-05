package cn.itcast.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.SysUserDao;
import cn.itcast.domain.SysRole;
import cn.itcast.domain.SysUser;
import cn.itcast.service.SysUserService;
/*
 * 将对象交给容器初始化两种方式
 * @Service @Repository @Controller @Component
 * 配置文件<bean> 标签初始化对象
 * */

@Service("userService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	/*
	 * 11个过滤器 
	 * 第一个过滤器用于存储用户的登录信息
	 * 最后一个过滤器判断用户账号密码是否正确
	 * 拥有的权限是否匹配
	 * */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//先通过用户名得到数据库的sysuser对象
		SysUser sysUser = userDao.findUserByName(username);
		//返回给框架用于验证的User对象
		//用户的账号 用户的密码 用户拥有的权限集合
		
		List<GrantedAuthority> authorities  = new ArrayList<GrantedAuthority>();
		//根据用户信息查询得到真实的权限集合 循环添加
		List<SysRole> userRoles = sysUser.getRoles();
		if(null!=userRoles&&userRoles.size()>0) {
			for(SysRole role:userRoles) {
				
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}
		//获取用户的截止时间 判断是否过期 如果过期 
		User user = new User(sysUser.getUsername(), sysUser.getPassword(), sysUser.getStatus()==1?true:false, true, true, true, authorities);
		return user;
	}

	@Override
	public List<SysUser> findAllUser() {
		
		return userDao.findAllUser();
	}

	@Override
	public void saveUser(SysUser user) {
		//先获取原始明文的密码
		String pwd  = user.getPassword();
		//给明文密码加密 //将加密后的密码赋值给user
		user.setPassword(encoder.encode(pwd));
		userDao.saveUser(user);
	}

	@Override
	public SysUser findUserByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.findUserByUserId(userId);
	}

}

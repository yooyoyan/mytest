package cn.itcast.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import cn.itcast.domain.SysUser;

public interface SysUserService extends UserDetailsService {

	List<SysUser> findAllUser();

	void saveUser(SysUser user);

	SysUser findUserByUserId(Integer userId);

}

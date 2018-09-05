package cn.itcast.dao;

import org.apache.ibatis.annotations.Insert;

import cn.itcast.domain.SysLog;

public interface SysLogDao {

	@Insert("insert into sys_log values(common_sequence.nextval,"
			+ "#{visitTime},#{username},#{ip},#{method},#{executeResult},#{executeMsg},#{executeTime})")
	public void saveSysLog(SysLog log);
}

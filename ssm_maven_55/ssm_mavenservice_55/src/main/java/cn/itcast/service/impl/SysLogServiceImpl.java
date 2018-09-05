package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.SysLogDao;
import cn.itcast.domain.SysLog;
import cn.itcast.service.SysLogService;
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao logDao ;
	@Override
	public void saveSysLog(SysLog log) {
	
		logDao.saveSysLog(log);
	}

}

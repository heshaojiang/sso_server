package com.minstone.sso.userLinkInfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.minstone.common.util.DBManager;
import com.minstone.common.util.Tools;
import com.minstone.sso.userLinkInfo.dao.UserLinkInfoDao;
import com.minstone.sso.userLinkInfo.model.UserLinkInfo;

/**
 * 单点登录用户信息表service
 * @author hesj
 * @version v1.0
 */

@Service
@Transactional(readOnly = true)
public class UserLinkInfoService {
	
	/**
	 * this loger  can be used by service in anywhere . wzz
	 */
	//private Logger log = LoggerFactory.getLogger(UserLinkInfoServiceImpl.class);
	
	@Autowired
	private UserLinkInfoDao userLinkInfoDao;
	
	public boolean saveOrUpdate(UserLinkInfo user){
		boolean flag = false;

		String id = user.getId();
		if (Tools.isEmpty(id)) {
			id = DBManager.getUUID();
			user.setId(id);
			flag = userLinkInfoDao.insert(user)>0;
		}
		else{
			flag = userLinkInfoDao.update(user)>0;
		}
		return flag;
	}
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public UserLinkInfo getById(String id) {
		return userLinkInfoDao.getById(id);
	}
	
	public Map<String,Object> getSysWithUserOne(Map<String,Object> condition){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list = userLinkInfoDao.getSysWithUser(condition);
		if(null == list || list.size()==0){
			result = null;
		}
		else{
			result = list.get(0);
		}
		return result;
	}
	
	public List<Map<String,Object>> getSysWithUser(Map<String,Object> condition){
		return userLinkInfoDao.getSysWithUser(condition);
	}
	
	public List<Map> getSysInfos(){
		return userLinkInfoDao.getSysInfos();
	}
}

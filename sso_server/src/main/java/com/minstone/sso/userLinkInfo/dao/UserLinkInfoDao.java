package com.minstone.sso.userLinkInfo.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.minstone.sso.userLinkInfo.model.UserLinkInfo;

/**
 * 单点登录用户信息表Dao
 * @author hesj
 * @version v1.0
 */

public interface UserLinkInfoDao{
	
	/**
	 * 获取单条数据
	 * @param code
	 * @return
	 */
	public UserLinkInfo getById(String id);
	
	public UserLinkInfo get(UserLinkInfo info);
	
	public List<UserLinkInfo> findList(UserLinkInfo info);
	
	public int insert(UserLinkInfo info);
	public int update(UserLinkInfo info);
	
	/**
	 * @param id
	 * @return
	 */
	public int deleteById(String id);
	
	public int delete(Map<String,Object> condition);
	
	
	public List<Map<String,Object>> getSysWithUser(Map<String,Object> condition);
	
	@Select("SELECT * FROM pms_indi")        
	List<Map> getSysInfos();
}
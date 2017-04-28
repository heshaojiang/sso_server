package com.minstone.test.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TestDao {
	@Select("SELECT * FROM pms_indi WHERE indi_id = #{indiId}")        
	Map getUser(@Param("indiId") String indiId);   
}

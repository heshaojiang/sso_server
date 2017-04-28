package com.minstone.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minstone.test.dao.TestDao;

@Controller
@RequestMapping(value="/test")
public class TestController {
	@Autowired TestDao dao;
	@RequestMapping(value="/hello")
	public String hello() throws Exception{
		return "MyJsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/getData")
	public Map getData(){
		Map map = dao.getUser("806cd1e4aa744c6ea79b9eb49796422a");
		map.remove("CREATE_TIME");
		map.remove("UPDATE_TIME");
		return map;
	}
}

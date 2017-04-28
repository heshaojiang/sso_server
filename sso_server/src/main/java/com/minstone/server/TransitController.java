package com.minstone.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minstone.common.util.DBManager;
import com.minstone.common.util.Tools;
import com.minstone.sso.userLinkInfo.model.UserLinkInfo;
import com.minstone.sso.userLinkInfo.service.UserLinkInfoService;

/**
 * 单点登录controller
 * @author namehsj
 *
 */
@Controller
@RequestMapping(value="/transit")
public class TransitController {
	@Autowired
	private UserLinkInfoService userLinkInfoService;
	
	/**
	 * 进入单点登录的系统入口
	 * 先进行登录，再执行跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String service = request.getParameter("service");
        if(isLogin(request)){
        	String fromSysCode = request.getParameter("fromSysCode");
    		String toSysCode = request.getParameter("toSysCode");
    		String username = request.getParameter("username");
    		Map<String,Object> condition = new HashMap<String, Object>();
    		condition.put("sysCode", fromSysCode);
    		condition.put("username", username);
    		Map<String,Object> userInfo = userLinkInfoService.getSysWithUserOne(condition);
    		if(null != userInfo){
    			String parentId = (String) userInfo.get("PARENT_ID");
    			if(Tools.isNotEmpty(parentId)){
    				condition.clear();
    				condition.put("sysCode", toSysCode);
    				condition.put("parentId", parentId);
    				Map<String,Object> tempUser = userLinkInfoService.getSysWithUserOne(condition);
    				if(null != tempUser){
    					String loginUrl = (String) tempUser.get("LOGIN_URL");
    					String ucode_nm = (String) tempUser.get("UCODE_NM");
    					String pwd_nm = (String) tempUser.get("PWD_NM");
    					
    					StringBuilder url = new StringBuilder();
    			        url.append(loginUrl);
    			        if (0 <= loginUrl.indexOf("?")) {
    			            url.append("&");
    			        } else {
    			            url.append("?");
    			        }
    			        url.append(ucode_nm+"=").append(tempUser.get("USERNAME"));
    			        url.append("&"+pwd_nm+"=").append(tempUser.get("PASSWORD"));
    			        request.setAttribute("url", url.toString());
    				}
    			}
    		}
        }
		

        request.setAttribute("service", service);
        return "transit/index";
	}
	
	/**
	 * 进入系统外系统账号和密码的页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/setOtherSysUser")
	public String setOtherSysUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if(isLogin(request)){
			String fromSysCode = request.getParameter("fromSysCode");
			String toSysCode = request.getParameter("toSysCode");
			String username = request.getParameter("username");
			Map<String,Object> condition = new HashMap<String, Object>();
			condition.put("sysCode", fromSysCode);
			condition.put("username", username);
			Map<String,Object> userInfo = userLinkInfoService.getSysWithUserOne(condition);
			String parentId = "";
			if(Tools.isEmpty(userInfo.get("USERNAME"))){
				//创建本系统的账号
				UserLinkInfo user = new UserLinkInfo();
				user.setParentId(DBManager.getUUID());
				user.setSysId(userInfo.get("S_INFO_ID")+"");
				user.setUsername(username);
				user.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				userLinkInfoService.saveOrUpdate(user);
				parentId = user.getParentId();
				
			}
			else{
				parentId = userInfo.get("PARENT_ID")+"";
			}
			condition.clear();
			condition.put("sysCodes", toSysCode.split(","));
			condition.put("parentId", parentId);
			List<Map<String, Object>> users = userLinkInfoService.getSysWithUser(condition);
			request.setAttribute("users", users);
			request.setAttribute("parentId", parentId);
		}
		return "transit/userForm";
		
		
	}
	
	@RequestMapping(value="/setOtherSysUserIndex")
	public String setOtherSysUserIndex(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String fromSysCode = request.getParameter("fromSysCode");
		String toSysCode = request.getParameter("toSysCode");
		String username = request.getParameter("username");
		String service = "setOtherSysUser?"+request.getQueryString();
		request.setAttribute("service", service);
        return "transit/index";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public boolean save(HttpServletRequest request,HttpServletResponse response){
		String parent_id = request.getParameter("parent_id");
		String[] ids = request.getParameterValues("id");
		String[] sys_ids = request.getParameterValues("sys_id");
		String[] usernames = request.getParameterValues("username");
		String[] passwords = request.getParameterValues("password");
		for(int i=0; i<sys_ids.length; i++){
			UserLinkInfo user = new UserLinkInfo();
			user.setId(ids[i]);
			user.setParentId(parent_id);
			user.setSysId(sys_ids[i]);
			user.setUsername(usernames[i]);
			user.setPassword(passwords[i]);
			if(Tools.isEmpty(ids[i])){
				user.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
			userLinkInfoService.saveOrUpdate(user);
		}
		return true;
	}
	
	/**
	 * 判断用户是否登录
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request){
		boolean result = false;
		Cookie[] cookies = request.getCookies();
        String username = "";
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if ("sso".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        String username1 = request.getParameter("username");
        if(Tools.isEmpty(username) || !username.equals(username1)){
        	
        }
        else{
        	result = true;
        }
		return result;
		
	}
	public static void main(String[] args) {
		try {
			TestPost();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void TestPost() throws IOException {  
        
        URL url = new URL("http://219.159.248.217:8080/pms/j_security_check");  
        URLConnection connection = url.openConnection();  
        connection.setDoOutput(true);  
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");  
        out.write("j_username=jxb&j_password=11"); // 向页面传递数据。post的关键所在！  
        out.flush();  
        out.close();  
        // 一旦发送成功，用以下方法就可以得到服务器的回应：  
        String sCurrentLine;  
        String sTotalString;  
        sCurrentLine = "";  
        sTotalString = "";  
        InputStream l_urlStream;  
        l_urlStream = connection.getInputStream();  
        // 传说中的三层包装阿！  
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(  
                l_urlStream));  
        while ((sCurrentLine = l_reader.readLine()) != null) {  
            sTotalString += sCurrentLine + "\r\n";  
  
        }  
        System.out.println(sTotalString);  
          
    }  
}

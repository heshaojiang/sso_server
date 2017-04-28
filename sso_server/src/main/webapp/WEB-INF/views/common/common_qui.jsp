<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>

<!-- 标签 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!-- QUI-->
<c:set var="qui" value="${pageContext.request.contextPath}/static/qui"/>
<c:set var="common" value="${pageContext.request.contextPath}/static/common"/>

<!-- 系统变量-->
<c:set var="ctxPort" value="${pageContext.request.serverPort}" />
<c:set var="ctxIp" value="${pageContext.request.serverName}" />
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<!-- css,js资源 -->
<%@ include file="/WEB-INF/views/common/css/common_qui_css.jsp"%>
<%@ include file="/WEB-INF/views/common/js/common_qui_js.jsp"%>


<script type="text/javascript">


var projectPath = "${ctx}";
var ctxStatic = "${ctxStatic}";

/*全局方法,刷新菜单对应的整个右侧业务页面*/
function refreshRightPage(){
	top.frmright.location.reload();
}

/*判断对象是否为空*/
function isNotEmpty(obj){

	   var flag = true;
	   
       if( obj=="" || obj==null || obj=="undefined" ){
           flag = false;
       }
       
       if(typeof(obj)=="boolean"){
    	   flag = true;
       }

       return flag;
}



</script>
</head>



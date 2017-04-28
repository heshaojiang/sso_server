<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common_qui.jsp"%>
<body>
	<div class="box1" >
		<form action="save" method="post" id="userForm">
			<input type="hidden" name="parent_id" value="${parentId }">
			<table class="tableStyle">
				<c:forEach var="user" items="${users }">
					<tr>
						<td>${user.SYS_NAME }账号</td>
						<td>
							<input type="hidden" name="id" value="${user.ID }">
							<input type="hidden" name="sys_id" value="${user.S_INFO_ID }">
							<input type="text" name="username" value="${user.USERNAME }">
						</td>
					</tr>
					<tr>
						<td>${user.SYS_NAME }密码</td>
						<td>
							<input type="text" name="password" value="${user.PASSWORD }">
						</td>
					</tr>
					
				</c:forEach>
				
			</table>
		</form>
		<div align="center" style="height: 40px;padding-top: 20px;">
    		<button type="button" onclick="javascript:formSubmit();"><span class="icon_save">保存</span></button>
		</div>
	</div>
	<script type="text/javascript">
		function formSubmit(){
			$.ajax({
			    type:"post",
			    url:"save",
			 	dataType:'json',
			 	data:$("#userForm").serialize(),  
			    success:function(data) {  
			        if(data){  
			            alert("修改成功！"); 
			            window.close(); 
			        }else{  
			        }  
			     },  
			     error : function() {  
			          alert("网络异常！");  
			     }
			});
		}
	</script>
</body>
</html>
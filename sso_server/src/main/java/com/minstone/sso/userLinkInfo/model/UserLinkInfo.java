package com.minstone.sso.userLinkInfo.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 单点登录用户信息表Entity
 * @author hesj
 * @version v1.0
 */

public class UserLinkInfo{
	
	private static final long serialVersionUID = 1L;
	
	private String id;

    /**
     * 系统关联ID       db_column: SYS_ID 
     */
	private java.lang.String sysId;
    /**
     * 父ID       db_column: PARENT_ID 
     */
	private java.lang.String parentId;
    /**
     * 用户名       db_column: USERNAME 
     */
	private java.lang.String username;
    /**
     * 密码       db_column: PASSWORD 
     */
	private java.lang.String password;
    /**
     * 创建时间       db_column: CRT_TIME 
     */
	private java.lang.String crtTime;
	//columns END
	
	
	
	

	
	public String getId() {
		return id;
	}

	public UserLinkInfo() {
		super();
	}


	public void setId(String id) {
		this.id = id;
	}


	public java.lang.String getSysId() {
		return this.sysId;
	}
	
	public void setSysId(java.lang.String sysId) {
		this.sysId = sysId;
	}
	
	public java.lang.String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	public java.lang.String getCrtTime() {
		return this.crtTime;
	}
	
	public void setCrtTime(java.lang.String crtTime) {
		this.crtTime = crtTime;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SysId",getSysId())
			.append("ParentId",getParentId())
			.append("Username",getUsername())
			.append("Password",getPassword())
			.append("CrtTime",getCrtTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserLinkInfo == false) return false;
		if(this == obj) return true;
		UserLinkInfo other = (UserLinkInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


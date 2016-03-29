package com.crawler.schema.web.model;

public enum Role {
	
	ADMIN(1, "admin");

	int roleId;
	String roleName;
	Role(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public static Role getRoleByName(String roleName) {
		for (Role role : values()) {
			if(role.equals(role.roleName)) {
				return role;
			}
		}
		return null;
	}
}

package com.elf.sys.security.service;

import java.util.List;

import com.elf.sys.security.entity.SysSecBusiRole;

public interface BusiRoleService {
	
	SysSecBusiRole getBusiRoleById(String busiRoleId);

	SysSecBusiRole saveBusiRole(SysSecBusiRole busiRole);

	void deleteBusiRoleById(String busiRoleId);

	SysSecBusiRole updateBusiRole(SysSecBusiRole busiRole);

	void saveBusiRoleUser(String busiRoleId, List<String> userIdList);

	void deleteBusiRoleUser(String busiRoleId, String userId);

	List<SysSecBusiRole> getBusiRoles();

	List<SysSecBusiRole> getBusiRolesByUserId(String userId);
	

}

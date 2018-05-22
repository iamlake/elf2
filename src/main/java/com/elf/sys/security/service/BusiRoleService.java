package com.elf.sys.security.service;

import com.elf.sys.security.entity.SysSecBusiRole;

import java.util.List;

public interface BusiRoleService {

	SysSecBusiRole getBusiRoleById(String busiRoleId);

	SysSecBusiRole saveBusiRole(SysSecBusiRole busiRole);

	void deleteBusiRoleById(String busiRoleId);

	SysSecBusiRole updateBusiRole(SysSecBusiRole busiRole);

	void saveBusiRoleUser(String busiRoleId, List<String> userIdList);

	void deleteBusiRoleUser(String busiRoleId, String userId);

	List<SysSecBusiRole> getBusiRoles();

	List<SysSecBusiRole> getBusiRolesByUserId(String userId);

	List<SysSecBusiRole> getAllBusiRoles();
}

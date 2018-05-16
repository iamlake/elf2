package com.elf.sys.security.service;

import com.elf.sys.security.entity.SysSecResourceAuthority;

import java.util.List;

public interface ResourceAuthorizeService {

	List<SysSecResourceAuthority> saveBusiPermissionAuthority(String busiRoleId, List<String> permissionList);

	List<SysSecResourceAuthority> saveBusiPermissionAuthorityForbidden(String busiRoleId, List<String> permissionList);

	List<SysSecResourceAuthority> saveBusiMenuAuthority(String busiRoleId, List<String> menuIdList);

	List<SysSecResourceAuthority> saveBusiMenuAuthorityForbidden(String busiRoleId, List<String> menuIdList);

	List<SysSecResourceAuthority> getBusiMenuAuthority();

	List<SysSecResourceAuthority> getBusiMenuAuthorityForbidden();

	List<SysSecResourceAuthority> getBusiMenuAuthorityByRoleId(String roleId);

	List<SysSecResourceAuthority> getBusiMenuAuthorityForbiddenByRoleId(String roleId);

//	List<SysSecResourceAuthority> updateBusiMenuAuthority(String busiRoleId, List<String> menuIdList);
//
//	List<SysSecResourceAuthority> updateBusiMenuAuthorityForbidden(String busiRoleId, List<String> menuIdList);

}

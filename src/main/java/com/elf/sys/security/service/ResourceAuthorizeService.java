package com.elf.sys.security.service;

import java.util.List;

import com.elf.sys.security.entity.SysSecResourceAuthority;

public interface ResourceAuthorizeService {

	List<SysSecResourceAuthority> saveBusiPermissionAuthority(String busiRoleId, List<String> permissionList);

	List<SysSecResourceAuthority> saveBusiPermissionAuthorityForbidden(String busiRoleId, List<String> permissionList);

	List<SysSecResourceAuthority> saveBusiMenuAuthority(String busiRoleId, List<String> menuIdList);

	List<SysSecResourceAuthority> saveBusiMenuAuthorityForbidden(String busiRoleId, List<String> menuIdList);

	List<SysSecResourceAuthority> getBusiMenuAuthority();

	List<SysSecResourceAuthority> getBusiMenuAuthorityForbidden();

	List<SysSecResourceAuthority> getBusiMenuAuthorityByRoleId(String roleId);

	

}

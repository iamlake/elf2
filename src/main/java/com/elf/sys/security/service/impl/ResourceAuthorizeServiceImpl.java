package com.elf.sys.security.service.impl;

import com.elf.core.context.context.ContextHolder;
import com.elf.sys.org.entity.User;
import com.elf.sys.security.entity.SysSecBusiRole;
import com.elf.sys.security.entity.SysSecResourceAuthority;
import com.elf.sys.security.entity.SysSecResourceAuthorityExample;
import com.elf.sys.security.mapper.SysSecResourceAuthorityMapper;
import com.elf.sys.security.service.BusiRoleService;
import com.elf.sys.security.service.ResourceAuthorizeService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceAuthorizeServiceImpl implements ResourceAuthorizeService {
	
	public static enum RoleTypeEnum {
		adminRole, busiRole, unitRole, stationRole
	};
	
	public static enum ResourceTypeEnum {
		app, menu, permision
	};
	
	public static enum AuthorityTypeEnum {
		available, forbidden
	};
    
	@Autowired
	private BusiRoleService busiRoleService;
	
	@Autowired
    private SysSecResourceAuthorityMapper resourceAuthorityMapper;
	
	@Override
    public List<SysSecResourceAuthority> saveBusiPermissionAuthority(String busiRoleId, List<String> permissionList) {
		String authorityType = AuthorityTypeEnum.available.toString();
    	
    	return this.saveBusiPermissionAuthority(busiRoleId, permissionList, authorityType);
    }
	
	@Override
    public List<SysSecResourceAuthority> saveBusiPermissionAuthorityForbidden(String busiRoleId, List<String> permissionList) {
		String authorityType = AuthorityTypeEnum.forbidden.toString();
    	
    	return this.saveBusiPermissionAuthority(busiRoleId, permissionList, authorityType);
    }
	
    private List<SysSecResourceAuthority> saveBusiPermissionAuthority(String busiRoleId, List<String> permissionList, String authorityType) {
		String roleType = RoleTypeEnum.busiRole.toString();
		String resourceType = ResourceTypeEnum.permision.toString();
    	
    	return this.saveResourceAuthority(busiRoleId, roleType, permissionList, resourceType, authorityType);
    }
    
    @Override
    public List<SysSecResourceAuthority> saveBusiMenuAuthority(String busiRoleId, List<String> menuIdList) {
		String authorityType = AuthorityTypeEnum.available.toString();
    	
    	return this.saveBusiMenuAuthority(busiRoleId, menuIdList, authorityType);
    }
	
	@Override
    public List<SysSecResourceAuthority> saveBusiMenuAuthorityForbidden(String busiRoleId, List<String> menuIdList) {
		String authorityType = AuthorityTypeEnum.forbidden.toString();
    	
    	return this.saveBusiMenuAuthority(busiRoleId, menuIdList, authorityType);
    }
    
    private List<SysSecResourceAuthority> saveBusiMenuAuthority(String busiRoleId, List<String> menuIdList, String authorityType) {
		String roleType = RoleTypeEnum.busiRole.toString();
		String resourceType = ResourceTypeEnum.menu.toString();
    	
    	return this.saveResourceAuthority(busiRoleId, roleType, menuIdList, resourceType, authorityType);
    }
    
    private List<SysSecResourceAuthority> saveResourceAuthority(String roleId, String roleType, List<String> resourceList, String resourceType, String authorityType) {
		  
		User currentUser = ContextHolder.getContext().getCurrentUser();
    	String account = currentUser.getAccount();
		Timestamp time = new Timestamp(new Date().getTime());
		SysSecResourceAuthorityExample example = new SysSecResourceAuthorityExample();
    	example.or()
    	.andRoleIdEqualTo(roleId)
    	.andRoleTypeEqualTo(roleType)
    	.andResourceIdIn(resourceList)
    	.andResourceTypeEqualTo(resourceType);
    	
    	List<SysSecResourceAuthority> resourceAuthorityList = resourceAuthorityMapper.selectByExample(example);
    	for(SysSecResourceAuthority resourceAuthority : resourceAuthorityList) {
    		if(resourceList.contains(resourceAuthority.getResourceId())) {
    			resourceList.remove(resourceAuthority.getResourceId());
    		}
    	}
    	
    	List<SysSecResourceAuthority> returnList = new ArrayList<SysSecResourceAuthority>();
    	SysSecResourceAuthority resourceAuthority = null;
    	String id = null;
    	for(String resource : resourceList) {
    		resourceAuthority = new SysSecResourceAuthority();
    		id = UUID.randomUUID().toString().replaceAll("-", "");
    		resourceAuthority.setId(id);
    		resourceAuthority.setRoleId(roleId);
    		resourceAuthority.setRoleType(roleType);
    		resourceAuthority.setResourceId(resource);
    		resourceAuthority.setResourceType(resourceType);
    		resourceAuthority.setAuthorityType(authorityType);
    		resourceAuthority.setCreatedBy(account);
    		resourceAuthority.setCreationTime(time);
    		resourceAuthority.setModifiedBy(account);
    		resourceAuthority.setModificationTime(time);
    		resourceAuthorityMapper.insertSelective(resourceAuthority);
    		returnList.add(resourceAuthority);
    	}
    	
    	return returnList;
		
    }
    
    @Override
    public List<SysSecResourceAuthority> getBusiMenuAuthorityByRoleId(String roleId) {   	
		String authorityType = "%%";
		ArrayList<String> roleIdList = new ArrayList<String>();
		roleIdList.add(roleId);
		String roleType = RoleTypeEnum.busiRole.toString();
		String resourceType = ResourceTypeEnum.menu.toString();
		
		return this.getResourceAuthority(roleIdList, roleType, resourceType, authorityType);
    }
    
    @Override
    public List<SysSecResourceAuthority> getBusiMenuAuthority() {   	
		String authorityType = AuthorityTypeEnum.available.toString();
    	
    	return this.getBusiMenuAuthority(authorityType);
    }
	
	@Override
    public List<SysSecResourceAuthority> getBusiMenuAuthorityForbidden() {
		String authorityType = AuthorityTypeEnum.forbidden.toString();
    	
    	return this.getBusiMenuAuthority(authorityType);
    }
    
    private List<SysSecResourceAuthority> getBusiMenuAuthority(String authorityType) {
    	List<SysSecBusiRole> roleList = this.busiRoleService.getBusiRoles();
    	ArrayList<String> roleIdList = new ArrayList<String>();
    	for(SysSecBusiRole role : roleList) {
    		roleIdList.add(role.getId());
    	}
		String roleType = RoleTypeEnum.busiRole.toString();
		String resourceType = ResourceTypeEnum.menu.toString();
    	
    	return this.getResourceAuthority(roleIdList, roleType, resourceType, authorityType);
    }
    
    private List<SysSecResourceAuthority> getResourceAuthority(List<String> roleIdList, String roleType, String resourceType, String authorityType) {

		SysSecResourceAuthorityExample example = new SysSecResourceAuthorityExample();
    	example.or()
    	.andRoleIdIn(roleIdList)
    	.andRoleTypeEqualTo(roleType)
    	.andResourceTypeEqualTo(resourceType)
    	.andAuthorityTypeLike(authorityType);
    	
    	List<SysSecResourceAuthority> resourceAuthorityList = resourceAuthorityMapper.selectByExample(example);
    	
    	return resourceAuthorityList;
		
    }
	

}

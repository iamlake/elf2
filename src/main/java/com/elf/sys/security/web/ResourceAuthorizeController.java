package com.elf.sys.security.web;

import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.security.entity.SysSecResourceAuthority;
import com.elf.sys.security.service.ResourceAuthorizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/sec/auth")
public class ResourceAuthorizeController extends BaseController {

	@Autowired
	private ResourceAuthorizeService resourceAuthorizeService;

	@RequestMapping(value = "/saveBusiPermissionAuthority", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveBusiPermissionAuthority(String busiRoleId, String permissions) {
		List<String> permissionList = new ArrayList<String>();
		for(String permission : permissions.split(",")) {
			permissionList.add(permission);
		}
		List<SysSecResourceAuthority> authorityList = resourceAuthorizeService.saveBusiPermissionAuthority(busiRoleId, permissionList);
		QueryResult<SysSecResourceAuthority> result = new QueryResult<SysSecResourceAuthority>();
		result.setCode(Global.RESULT_STAUTS_SUCCESS);
		result.setData(authorityList);
		result.setCount(authorityList.size());
		return result;
	}

	@RequestMapping(value = "/getBusiMenuAuthorityByRoleId", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result getBusiMenuAuthorityByRoleId(String busiRoleId) {
		List<SysSecResourceAuthority> authorityList = resourceAuthorizeService.getBusiMenuAuthorityByRoleId(busiRoleId);
		QueryResult<SysSecResourceAuthority> result = new QueryResult<SysSecResourceAuthority>();
		result.setCode(Global.RESULT_STAUTS_SUCCESS);
		result.setData(authorityList);
		result.setCount(authorityList.size());
		return result;
	}

	@RequestMapping(value = "/saveBusiMenuAuthority", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveBusiMenuAuthority(String busiRoleId, String menuIds) {
		List<String> menuIdList = new ArrayList<String>();
		for(String menuId : menuIds.split(",")) {
			menuIdList.add(menuId);
		}
		List<SysSecResourceAuthority> authorityList = resourceAuthorizeService.saveBusiMenuAuthority(busiRoleId, menuIdList);
		QueryResult<SysSecResourceAuthority> result = new QueryResult<SysSecResourceAuthority>();
		result.setCode(Global.RESULT_STAUTS_SUCCESS);
		result.setData(authorityList);
		result.setCount(authorityList.size());
		return result;
	}
}

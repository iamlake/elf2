package com.elf.sys.security.web;

import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.security.entity.SysSecBusiRole;
import com.elf.sys.security.entity.SysSecBusiRoleUser;
import com.elf.sys.security.service.BusiRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/sec/busi")
public class BusiRoleController extends BaseController {
    
    @Autowired
    private BusiRoleService busiRoleService;
    
    @RequestMapping(value = "/getBusiRoleById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result getBusiRoleById(String busiRoleId) {
    	SysSecBusiRole busiRole = busiRoleService.getBusiRoleById(busiRoleId);
    	QueryResult<SysSecBusiRole> result = new QueryResult<SysSecBusiRole>();
    	ArrayList<SysSecBusiRole> busiRoleList = new ArrayList<SysSecBusiRole>();
    	busiRoleList.add(busiRole);
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(busiRoleList);
    	result.setCount(busiRoleList.size());
        return result;
    }
    
    @RequestMapping(value = "/saveBusiRole", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result saveBusiRole(SysSecBusiRole paramBusiRole) {
    	SysSecBusiRole busiRole = busiRoleService.saveBusiRole(paramBusiRole);
    	QueryResult<SysSecBusiRole> result = new QueryResult<SysSecBusiRole>();
    	ArrayList<SysSecBusiRole> busiRoleList = new ArrayList<SysSecBusiRole>();
    	busiRoleList.add(busiRole);
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(busiRoleList);
    	result.setCount(busiRoleList.size());
        return result;
    }
    
    @RequestMapping(value = "/updateBusiRole", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result updateBusiRole(SysSecBusiRole paramBusiRole) {
    	SysSecBusiRole busiRole = busiRoleService.updateBusiRole(paramBusiRole);
    	QueryResult<SysSecBusiRole> result = new QueryResult<SysSecBusiRole>();
    	ArrayList<SysSecBusiRole> busiRoleList = new ArrayList<SysSecBusiRole>();
    	busiRoleList.add(busiRole);
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(busiRoleList);
    	result.setCount(busiRoleList.size());
        return result;
    }
    
    @RequestMapping(value = "/deleteBusiRoleById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result deleteBusiRoleById(String busiRoleId) {
    	busiRoleService.deleteBusiRoleById(busiRoleId);
    	QueryResult<SysSecBusiRole> result = new QueryResult<SysSecBusiRole>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
        return result;
    }
    
    @RequestMapping(value = "/getBusiRoles", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result getBusiRoles() {
    	List<SysSecBusiRole> BusiRoleList = busiRoleService.getBusiRoles();
    	QueryResult<SysSecBusiRole> result = new QueryResult<SysSecBusiRole>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(BusiRoleList);
    	result.setCount(BusiRoleList.size());
        return result;
    }
    
    @RequestMapping(value = "/getBusiRolesByUserId", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result getBusiRolesByUserId(String userId) {
    	List<SysSecBusiRole> BusiRoleList = busiRoleService.getBusiRolesByUserId(userId);
    	QueryResult<SysSecBusiRole> result = new QueryResult<SysSecBusiRole>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(BusiRoleList);
    	result.setCount(BusiRoleList.size());
        return result;
    }
    
    @RequestMapping(value = "/saveBusiRoleUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result saveBusiRoleUser(String busiRoleId, List<String> userIdList) {
    	busiRoleService.saveBusiRoleUser(busiRoleId, userIdList);
    	QueryResult<SysSecBusiRoleUser> result = new QueryResult<SysSecBusiRoleUser>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
        return result;
    }
    
    @RequestMapping(value = "/deleteBusiRoleUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result deleteBusiRoleUser(String busiRoleId, String userId) {
    	busiRoleService.deleteBusiRoleUser(busiRoleId, userId);
    	QueryResult<SysSecBusiRoleUser> result = new QueryResult<SysSecBusiRoleUser>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
        return result;
    }
    
    
}

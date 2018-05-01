package com.elf.sys.security.web;

import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.security.entity.SysSecBusiRole;
import com.elf.sys.security.entity.SysSecBusiRoleUser;
import com.elf.sys.security.service.BusiRoleService;
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
    
    /*@RequestMapping(value = "/getBusiRoleById", method = {RequestMethod.POST, RequestMethod.GET})
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
    }*/
    
    
    
}

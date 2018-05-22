package com.elf.sys.security.web;

import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.constants.ResultStatusEnum;
import com.elf.core.persistence.result.JSONResult;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.security.entity.ResourceAuthority;
import com.elf.sys.security.service.ResourceAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: elf
 * @description: ResourceAuthorityController
 * @author: Liyiming
 * @create: 2018-05-20 09:49
 **/
@RestController
public class ResourceAuthorityController extends BaseController {
    @Autowired
    ResourceAuthorityService resourceAuthorityService;

    @GetMapping("/menuAuthority")
    public Result findMenuAuthorityByRoleId(String roleId) {
        List<ResourceAuthority> menuAuthorityList = resourceAuthorityService.getMenuAuthorityByRoleId(roleId);
        QueryResult<ResourceAuthority> result = new QueryResult<>();
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(menuAuthorityList);
        result.setCount(menuAuthorityList.size());
        return result;
    }

    @PostMapping("/menuAuthority")
    public Result saveMenuAuthority(String roleId, String menuIds) {
        List<String> menuIdList = null;
        if(StringUtils.isNotBlank(menuIds)){
            menuIdList = new ArrayList<>();
            for (String menuId : menuIds.split(",")) {
                menuIdList.add(menuId);
            }
        }
        JSONResult result = new JSONResult();
        try {
            List<ResourceAuthority> authorityList = resourceAuthorityService.saveMenuAuthority(roleId, menuIdList);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("保存成功！");
            result.getParameters().put("object", authorityList);
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("保存失败！");
        }
        return result;
    }

    @PostMapping("/permissionAuthority")
    public Result savePermissionAuthority(String roleId, String permissions) {
        List<String> permissionList = new ArrayList<>();
        for(String permission : permissions.split(",")) {
            permissionList.add(permission);
        }
        JSONResult result = new JSONResult();
        try {
            List<ResourceAuthority> authorityList = resourceAuthorityService.savePermissionAuthority(roleId, permissionList);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("保存成功！");
            result.getParameters().put("object", authorityList);
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("保存失败！");
        }
        return result;
    }
}

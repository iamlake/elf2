package com.elf.sys.security.web;

import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.constants.ResultStatusEnum;
import com.elf.core.persistence.result.JSONResult;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.security.entity.Role;
import com.elf.sys.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: elf
 * @description: RoleController
 * @author: Liyiming
 * @create: 2018-05-17 09:18
 **/
@RestController
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public Result findRoles(Role role) {
        List<Role> roleList = roleService.getRoleList(role);
        return new QueryResult<>(ResultStatusEnum.SUCCESS.getValue(), "", roleList, roleList.size());
    }

    @PostMapping("/role")
    public Result addNewRole(Role role) {
        JSONResult result = new JSONResult();
        try {
            Role newRole = roleService.saveRole(role);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("添加成功！");
            result.getParameters().put("object", newRole);
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("添加失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }

    @PutMapping("/role")
    public Result modifyRole(Role role) {
        JSONResult result = new JSONResult();
        try {
            Role newRole = roleService.updateRole(role);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("修改成功！");
            result.getParameters().put("object", newRole);
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("修改失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }

    @DeleteMapping("/role/{roleId}")
    public Result removeRoleById(@PathVariable("roleId") String roleId) {
        JSONResult result = new JSONResult();
        try {
            if (roleService.deleteById(roleId)) {
                result.setCode(ResultStatusEnum.SUCCESS.getValue());
                result.setMsg("删除成功！");
                result.getParameters().put("", "");
            } else {
                result.setCode(ResultStatusEnum.FAILED.getValue());
                result.setMsg("删除失败！");
            }
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("删除失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }

    @PostMapping("/userRoles")
    public Result addUserRoles(String userIds, String roleId) {
        List<String> userIdsList = null;
        if (StringUtils.isNotBlank(userIds)) {
            userIdsList = new ArrayList<>();
            Collections.addAll(userIdsList, userIds.split(","));
        }
        JSONResult result = new JSONResult();
        try {
            roleService.saveUserRoles(userIdsList, roleId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("添加成功！");
            result.getParameters().put("", "");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("添加失败！");
        }
        return result;
    }

    @DeleteMapping("/userRoles")
    public Result removeUserRoles(String userIds, String roleId) {
        List<String> userIdsList = null;
        if (StringUtils.isNotBlank(userIds)) {
            userIdsList = new ArrayList<>();
            Collections.addAll(userIdsList, userIds.split(","));
        }
        JSONResult result = new JSONResult();
        try {
            roleService.deleteUserRoles(userIdsList, roleId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("删除成功！");
            result.getParameters().put("", "");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("删除失败！");
        }
        return result;
    }
}

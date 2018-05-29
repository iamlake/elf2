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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: elf
 * @description: MenuAuthorityController
 * @author: Liyiming
 * @create: 2018-05-26 21:20
 **/
@RestController
public class MenuAuthorityController extends BaseController {

    @Autowired
    @Qualifier("menuAuthorityService")
    private ResourceAuthorityService menuAuthorityService;

    /**
     * @Description: 根据角色查询所有授权的菜单信息
     * @Param: [roleId]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/23
     */
    @GetMapping("/menuAuthority")
    public Result findMenuAuthorityByRoleId(String roleId) {
        List<ResourceAuthority> menuAuthorityList = menuAuthorityService.getResourceAuthorityByRoleId(roleId);
        QueryResult<ResourceAuthority> result = new QueryResult<>();
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(menuAuthorityList);
        result.setCount(menuAuthorityList.size());
        return result;
    }

    /**
     * @Description: 保存角色与菜单的授权信息
     * @Param: [roleId, menuIds]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/23
     */
    @PostMapping("/menuAuthority")
    public Result saveMenuAuthority(String roleId, String menuIds) {
        List<String> menuIdList = null;
        if (StringUtils.isNotBlank(menuIds)) {
            menuIdList = new ArrayList<>();
            for (String menuId : menuIds.split(",")) {
                menuIdList.add(menuId);
            }
        }
        JSONResult result = new JSONResult();
        try {
            List<ResourceAuthority> authorityList = menuAuthorityService.saveResourceAuthority(roleId, menuIdList);
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

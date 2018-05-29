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
 * @description: AppAuthorityController
 * @author: Liyiming
 * @create: 2018-05-26 21:23
 **/
@RestController
public class AppAuthorityController extends BaseController {

    @Autowired
    @Qualifier("appAuthorityService")
    private ResourceAuthorityService appAuthorityService;

    /**
     * @Description: 根据角色查询所有授权的应用信息
     * @Param: [roleId]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/23
     */
    @GetMapping("/appAuthority")
    public Result findAppAuthorityByRoleId(String roleId) {
        List<ResourceAuthority> appAuthorityList = appAuthorityService.getResourceAuthorityByRoleId(roleId);
        QueryResult<ResourceAuthority> result = new QueryResult<>();
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(appAuthorityList);
        result.setCount(appAuthorityList.size());
        return result;
    }

    /**
     * @Description: 保存角色与应用的授权信息
     * @Param: [roleId, appIds]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/23
     */
    @PostMapping("/appAuthority")
    public Result saveAppAuthority(String roleId, String appIds) {
        List<String> appIdList = null;
        if (StringUtils.isNotBlank(appIds)) {
            appIdList = new ArrayList<>();
            for (String appId : appIds.split(",")) {
                appIdList.add(appId);
            }
        }
        JSONResult result = new JSONResult();
        try {
            List<ResourceAuthority> authorityList = appAuthorityService.saveResourceAuthority(roleId, appIdList);
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

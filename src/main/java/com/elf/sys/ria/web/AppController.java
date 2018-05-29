package com.elf.sys.ria.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.persistence.constants.ResultStatusEnum;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.ria.entity.App;
import com.elf.sys.ria.service.AppService;
import com.elf.sys.security.service.ResourceAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: elf
 * @description: AppController
 * @author: Liyiming
 * @create: 2018-03-17 17:23
 **/
@RestController
public class AppController extends BaseController {

    @Autowired
    private AppService appService;

    @Autowired
    @Qualifier("appAuthorityService")
    private ResourceAuthorityService appAuthorityService;

    /**
     * @Description: 查询应用信息
     * @Param: [app]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/18
     */
    @GetMapping("/app")
    public Result findAppList(App app) {
        EntityWrapper<App> entityWrapper = new EntityWrapper<>(app);
        entityWrapper.orderBy("app_order", true);
        List<App> list = appService.selectList(entityWrapper);
        return new QueryResult<>(ResultStatusEnum.SUCCESS.getValue(), "", list, list.size());
    }

    /**
     * @Description: 查询当前用户可使用的应用
     * @Param: [app]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/23
     */
    @GetMapping("/authorityApp")
    public Result findAuthorityAppList(App app) {
        List<String> authorityAppIdList = appAuthorityService.getResourceAuthority();
        List<App> appList = new ArrayList<>();
        if (authorityAppIdList.size() > 0) {
            appList = appService.getAuthorityAppList(authorityAppIdList, app);
        }
        return new QueryResult<>(ResultStatusEnum.SUCCESS.getValue(), "", appList, appList.size());
    }

}

package com.elf.sys.app.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.app.entity.App;
import com.elf.sys.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * @Description: 查询应用信息
     * @Param: [app]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/18
     */
    @GetMapping("/app")
    public Result findAppList(App app) {
        List<App> list = appService.selectList(new EntityWrapper<>(app));
        return new QueryResult<>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }
}

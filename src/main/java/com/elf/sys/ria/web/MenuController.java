package com.elf.sys.ria.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.ria.entity.Menu;
import com.elf.sys.ria.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: elf
 * @description: MenuController
 * @author: Liyiming
 * @create: 2018-03-15 17:08
 **/
@RestController
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * @Description: 查询所有菜单
     * @Param: [menu]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/18
     */
    @GetMapping("/menu")
    public Result findMenuList(Menu menu) {
        List<Menu> list = menuService.selectList(new EntityWrapper<>(menu));
        return new QueryResult<>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }

    /**
     * @Description: 通过应用ID查询所属菜单
     * @Param: [appId]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/17
     */
    @GetMapping("/menu/{appId}")
    public Result findMenuListByAppId(@PathVariable("appId") String appId) {
        Menu menu = new Menu();
        menu.setAppId(appId);
        List<Menu> list = menuService.selectList(new EntityWrapper<>(menu));
        return new QueryResult<>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }
}

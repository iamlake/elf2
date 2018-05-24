package com.elf.sys.ria.web;

import com.elf.core.persistence.constants.ResultStatusEnum;
import com.elf.core.persistence.result.JSONResult;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.ria.entity.Menu;
import com.elf.sys.ria.service.MenuService;
import com.elf.sys.security.service.ResourceAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private ResourceAuthorityService resourceAuthorityService;

    /**
     * @Description: 查询所有菜单
     * @Param: [menu]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/18
     */
    @GetMapping("/menu")
    public Result findMenuList(Menu menu) {
        List<Menu> list = menuService.getMenuList(menu);
        return new QueryResult<>(ResultStatusEnum.SUCCESS.getValue(), "", list, list.size());
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
        List<Menu> list = menuService.getMenuListByAppId(appId);
        return new QueryResult<>(ResultStatusEnum.SUCCESS.getValue(), "", list, list.size());
    }

    /**
     * @Description: 新增菜单信息
     * @Param: [menu]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/4/17
     */
    @PostMapping("/menu")
    public Result addMenu(Menu menu) {
        JSONResult result = new JSONResult();
        try {
            Menu newMenu = menuService.saveMenu(menu);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("添加成功！");
            result.getParameters().put("object", newMenu);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("添加失败！");
            result.getErrors().put("exception", e);
        }
        return result;
    }

    /**
     * @Description: 修改菜单信息
     * @Param: [menu]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/4/17
     */
    @PutMapping("/menu")
    public Result modifyMenu(Menu menu) {
        JSONResult result = new JSONResult();
        try {
            Menu newMenu = menuService.updateMenu(menu);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("修改成功！");
            result.getParameters().put("object", newMenu);
        } catch (Exception e) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("修改失败！");
            result.getErrors().put("exception", e);
        }
        return result;
    }

    /**
     * @Description: 删除菜单
     * @Param: [menu]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/4/19
     */
    @DeleteMapping("/menu/{id}")
    public Result removeMenuById(@PathVariable("id") String menuId) {
        JSONResult result = new JSONResult();
        boolean bRet = menuService.deleteById(menuId);
        if (bRet) {
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("删除成功！");
            result.getParameters().put("", "");
        } else {
            result.setCode(ResultStatusEnum.FAILED.getValue());
            result.setMsg("删除失败！");
        }
        return result;
    }

    /**
     * @Description: 查询当前用户可使用的菜单
     * @Param: [menu]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/23
     */
    @GetMapping("/authorityMenu")
    public Result findAuthorityMenuList(Menu menu) {
        List<String> authorityMenuIdList = resourceAuthorityService.getMenuAuthority();
        List<Menu> menuList = new ArrayList<>();
        if (authorityMenuIdList.size() > 0) {
            menuList = menuService.getAuthorityMenuList(authorityMenuIdList, menu);
        }
        return new QueryResult<>(ResultStatusEnum.SUCCESS.getValue(), "", menuList, menuList.size());
    }
}

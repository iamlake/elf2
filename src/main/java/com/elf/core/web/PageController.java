package com.elf.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>Title: PageController
 * <br>Description: 视图控制器,返回jsp视图给前端
 * <br>Author:李一鸣(liyiming.neu@neusoft.com)
 * <br>Date:2017年10月24日
 */
@Controller
@RequestMapping("/page")
public class PageController extends BaseController{
    /**
     * <br>Description: 页面跳转
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月23日
     * @param path
     * @return
     */
    @RequestMapping(value = "/{path}")
    public String forward(@PathVariable String path) {
        return path.replace("_", "/");
    }
}

package com.elf.core.web;

import com.elf.core.persistence.constants.Global;
import com.elf.sys.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Title: BaseController
 * @Description: 控制器支持类
 * @Author:李一鸣(liyiming.neu@neusoft.com)
 * @Date:2017年11月12日
 */
public abstract class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    protected User getSessionUser() {
        Object userObj = session.getAttribute(Global.USER_SESSION);
        if (userObj == null) {
            return null;
        }
        return (User) userObj;
    }

    protected User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}

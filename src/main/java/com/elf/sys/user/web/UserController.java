package com.elf.sys.user.web;

import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.JSONResult;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.user.entity.User;
import com.elf.sys.user.service.UserService;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello user!";
    }

    @GetMapping("/user")
    @ResponseBody
    public Result findUsers(User user) {
        List<User> list = userService.list(user);
        return new QueryResult<User>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }

    /**
     * <br>Description: 用户登录
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年10月24日
     * @param user
     * @param kaptcha
     * @param rememberMe
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result userLogin(User user, String kaptcha, boolean rememberMe) {
        JSONResult result = new JSONResult();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            result.setCode(Global.RESULT_STAUTS_SUCCESS);
        } else if (user.getAccount() == null || user.getPassword() == null) {
            result.setCode(Global.RESULT_STAUTS_FAILED);
        } else if (!kaptcha.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            result.setCode(Global.RESULT_STAUTS_FAILED);
            result.setMsg("验证码不正确！");
        } else {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
            token.setRememberMe(rememberMe);
            try {
                subject.login(token);
                final User loginUser = userService.getUserByAccount(user.getAccount());
                session.setAttribute(Global.USER_SESSION, loginUser);
                result.setCode(Global.RESULT_STAUTS_SUCCESS);
                result.setMsg("登录成功！");
            } catch (IncorrectCredentialsException e) {
                result.setCode(Global.RESULT_STAUTS_FAILED);
                result.setMsg("用户名或密码不正确！");
            } catch (LockedAccountException e) {
                result.setCode(Global.RESULT_STAUTS_FAILED);
                result.setMsg("账户已被冻结！");
            } catch (AuthenticationException e) {
                result.setCode(Global.RESULT_STAUTS_FAILED);
                result.setMsg("登录失败！");
            }
        }
        return result;
    }

    /**
     * <br>Description: 用户注销
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年10月24日
     * @return
     */
    @RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
    public String userLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/page/login";
    }
}

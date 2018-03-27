package com.elf.sys.user.web;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: UserController
 * @Description: UserController
 * @Author:李一鸣(liyiming.neu@neusoft.com)
 * @Date:2017年10月24日
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello user!";
    }

    /**
     * @Description: 用户登录
     * @Param: [user, kaptcha, rememberMe]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2017/10/24
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
                final User loginUser = userService.selectOne(new EntityWrapper<>(user));
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
     * @Description: 用户注销
     * @Param: []
     * @return: java.lang.String
     * @Author: Liyiming
     * @Date: 2017/10/24
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String userLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/page/login";
    }

    /**
     * @Description: 通过账号查询用户信息
     * @Param: [account]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2017/12/17
     */
    @GetMapping("/user/{account}")
    @ResponseBody
    public Result findUserByAccount(@PathVariable("account") String account) {
        List<User> list = new ArrayList<>();
        User user = userService.selectOne(new EntityWrapper<>(new User(account, null)));
        list.add(user);
        return new QueryResult<>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }

    /**
     * @Description: 查询用户列表
     * @Param: [user]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/24
     */
    @GetMapping("/user")
    @ResponseBody
    public Result findUsers(User user) {
        EntityWrapper entityWrapper = new EntityWrapper();
        if (StringUtils.isNotEmpty(user.getAccount())) {
            entityWrapper.eq("account", user.getAccount());
        }
        if (StringUtils.isNotEmpty(user.getFullname())) {
            entityWrapper.like("fullname", user.getFullname(), SqlLike.DEFAULT);
        }
        List<User> list = userService.selectList(entityWrapper);
        return new QueryResult<>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }

    /**
     * @Description: 添加用户信息
     * @Param: [user]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/25
     */
    @PostMapping("/user")
    @ResponseBody
    public Result addNewUser(User user) {
        JSONResult result = new JSONResult();
        boolean bRet = userService.insert(user);
        if (bRet) {
            result.setCode(Global.RESULT_STAUTS_SUCCESS);
            result.setMsg("添加成功！");
            result.getParameters().put("", "");
        } else {
            result.setCode(Global.RESULT_STAUTS_FAILED);
            result.setMsg("添加失败！");
        }
        return result;
    }

    /**
     * @Description: 修改用户信息
     * @Param: [user]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/25
     */
    @PutMapping("/user")
    @ResponseBody
    public Result modifyUser(User user) {
        JSONResult result = new JSONResult();
        boolean bRet = userService.updateById(user);
        if (bRet) {
            result.setCode(Global.RESULT_STAUTS_SUCCESS);
            result.setMsg("修改成功！");
            result.getParameters().put("", "");
        } else {
            result.setCode(Global.RESULT_STAUTS_FAILED);
            result.setMsg("修改失败！");
        }
        return result;
    }
}

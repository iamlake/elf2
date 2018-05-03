package com.elf.sys.org.web;

import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.Context;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.context.context.impl.ContextImpl;
import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.JSONResult;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.org.entity.User;
import com.elf.sys.org.service.UserService;
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
import java.util.Collections;
import java.util.List;


@Controller
public class UserController extends BaseController {
    
    @Autowired
    private UserService userService;
    
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
                final User loginUser = userService.getUserByAccount(user.getAccount());
                session.setAttribute(Global.USER_SESSION, loginUser);            
                Context context = new ContextImpl();
                context.setCurrentUser(loginUser);
                session.setAttribute("com.elf.core.context.Context", context);
                ContextHolder.setContext(context); 
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
        User user = userService.getUserByAccount(account);
        list.add(user);
        return new QueryResult<>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }

    /**
     * @Description: 查询用户列表
     * @Param: [user] user查询条件
     * @Param: [withoutIds] 排除的UserId
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/1
     */
    @GetMapping("/user")
    @ResponseBody
    public Result findUsers(User user, String withoutIds) {
        List<String> withoutIdsList = null;
        if (StringUtils.isNotBlank(withoutIds)) {
            withoutIdsList = new ArrayList<>();
            Collections.addAll(withoutIdsList, withoutIds.split(","));
        }
        List<User> list = userService.getUsers(user, withoutIdsList);
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
        try {
            User newUser = userService.saveUser(user);
            result.setCode(Global.RESULT_STAUTS_SUCCESS);
            result.setMsg("添加成功！");
            result.getParameters().put("object", newUser);
        }catch (Exception ex){
            result.setCode(Global.RESULT_STAUTS_FAILED);
            result.setMsg("添加失败！");
            result.getErrors().put("exception", ex);
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
        try {
            User newUser = userService.updateUser(user);
            result.setCode(Global.RESULT_STAUTS_SUCCESS);
            result.setMsg("修改成功！");
            result.getParameters().put("object", newUser);
        }catch (Exception ex){
            result.setCode(Global.RESULT_STAUTS_FAILED);
            result.setMsg("修改失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }

    /**
     * @Description: 修改用户密码
     * @Param: [user]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/4/2
     */
    @PutMapping("/user/password")
    @ResponseBody
    public Result modifyUserPassword(User user) {
        JSONResult result = new JSONResult();
        try {
            User newUser = userService.updateUserPassword(user);
            result.setCode(Global.RESULT_STAUTS_SUCCESS);
            result.setMsg("修改成功！");
            result.getParameters().put("object", newUser);
        }catch (Exception ex){
            result.setCode(Global.RESULT_STAUTS_FAILED);
            result.setMsg("修改失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }

    /**
     * @Description: 根据unitId查询用户信息
     * @Param: [unitId, user]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/1
     */
    @GetMapping("/user/unitUser")
    @ResponseBody
    public Result findUnitUsers(String unitId, User user) {
        List<User> list = userService.getUnitUsers(unitId, user);
        return new QueryResult<>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }
}

package com.elf.sys.user.web;

import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.user.entity.User;
import com.elf.sys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello user!";
    }

    @GetMapping("/user")
    public Result findUsers(User user){
        List<User> list = userService.list(user);
        return new QueryResult<User>(Global.RESULT_STAUTS_SUCCESS,"",list,list.size());
    }
}

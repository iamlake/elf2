package com.elf.sys.org.service;

import com.elf.core.service.BaseService;
import com.elf.sys.org.entity.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    List<User> getUsers(User user, List<String> withoutIds);

    User saveUser(User user);

    User updateUser(User user);

    User updateUserPassword(User user);

    User getUserByAccount(String account);

    List<User> getUnitUsers(String unitId, User user);

}

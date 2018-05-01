package com.elf.sys.org.service;

import com.elf.sys.org.entity.User;

import java.util.List;

public interface UserService {

	User getUserByAccount(String account);

	User saveUser(User user);

	User updateUser(User user);

	List<User> getUsers(User user);

	List<User> getUsersByUnitId(String unitId);

}

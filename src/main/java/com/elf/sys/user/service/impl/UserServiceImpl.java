package com.elf.sys.user.service.impl;

import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.user.entity.User;
import com.elf.sys.user.mapper.UserMapper;
import com.elf.sys.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements UserService{
}

package com.elf.sys.org.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.persistence.constants.Global;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.org.entity.User;
import com.elf.sys.org.mapper.UserMapper;
import com.elf.sys.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    @Override
    public User saveUser(User user) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        user.setCreatedBy(currentUser.getAccount());
        user.setCreationTime(currentTime);
        user.setModifiedBy(currentUser.getAccount());
        user.setModificationTime(currentTime);
        user.setUserId(StringUtils.getUUID());
        user.setPassword("1");
        if (StringUtils.isBlank(user.getUserHead())) {
            if (Global.SEX_MALE.equals(user.getSex())) {
                user.setUserHead("/static/assets/images/userhead/default_male.jpg");
            } else if (Global.SEX_FEMALE.equals(user.getSex())) {
                user.setUserHead("/static/assets/images/userhead/default_female.jpg");
            } else {
                user.setUserHead("/static/assets/images/userhead/default_none.jpg");
            }
        }
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        user.setModifiedBy(currentUser.getAccount());
        user.setModificationTime(currentTime);
        userMapper.updateById(user);
        return user;
    }

    @Override
    public User updateUserPassword(User user) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        user.setPwdChangedTime(currentTime);
        user.setModifiedBy(currentUser.getAccount());
        user.setModificationTime(currentTime);
        userMapper.updateById(user);
        return user;
    }

    @Override
    public List<User> getUsers(User user, List<String> withoutIds) {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(user.getAccount())) {
            entityWrapper.eq("account", user.getAccount());
        }
        if (StringUtils.isNotBlank(user.getFullname())) {
            entityWrapper.like("fullname", user.getFullname(), SqlLike.DEFAULT);
        }
        if (withoutIds != null && withoutIds.size() > 0) {
            entityWrapper.notIn("user_id", withoutIds);
        }
        List<User> userList = userMapper.selectList(entityWrapper);
        return userList;
    }

    @Override
    public List<User> getUnitUsers(String unitId, User user) {
        List<User> userList = userMapper.selectUnitUsers(unitId, user.getAccount(), user.getFullname());
        return userList;
    }
}

package com.elf.sys.org.service.impl;

import com.elf.core.context.context.ContextHolder;
import com.elf.sys.org.entity.User;
import com.elf.sys.org.entity.UserExample;
import com.elf.sys.org.mapper.UserMapper;
import com.elf.sys.org.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserMapper userMapper;
    
    @Override
    public User getUserByAccount(String account) {
    	UserExample example = new UserExample();
    	example.or().andAccountEqualTo(account);
    	List<User> userList = userMapper.selectByExample(example);
    	if(userList.size()>0) {
    		return userList.get(0);
    	}
    	return null;
    }
    
    @Override
    public User saveUser(User user) {
    	User currentUser = ContextHolder.getContext().getCurrentUser();
    	String account = currentUser.getAccount();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		user.setUserId(id);
		user.setCreatedBy(account);
		user.setCreationTime(time);
		user.setModifiedBy(account);
		user.setModificationTime(time);
		userMapper.insertSelective(user);
        return user;
        
    }
    
    @Override
    public User updateUser(User user) {
    	User currentUser = ContextHolder.getContext().getCurrentUser();
    	String account = currentUser.getAccount();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		user.setModifiedBy(account);
		user.setModificationTime(time);
		userMapper.updateByPrimaryKeySelective(user);
        return user;
        
    }
    
    @Override
    public List<User> getUsers(User user) {
    	UserExample example = new UserExample();
    	if(StringUtils.isNotBlank(user.getAccount())) {
    		example.or().andAccountEqualTo(user.getAccount());
    	}
    	if(StringUtils.isNotBlank(user.getFullname())) {
    		example.or().andFullnameLike("%"+ user.getFullname() +"%");
    	} 	
    	List<User> userList = userMapper.selectByExample(example);
    	return userList;
        
    }
    
    @Override
    public List<User> getUsersByUnitId(String unitId) {	
    	List<User> userList = userMapper.getUsersByUnitId(unitId);
    	return userList;
        
    }

}

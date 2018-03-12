package com.elf.sys.user.mapper;

import com.elf.core.persistence.BaseMapper;
import com.elf.sys.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    /**
     * @param account
     * @return
     */
    @Select("select * from sys_org_user where account like #{account}")
    User selectByAccount(@Param("account") String account);
}
package com.elf.sys.org.mapper;

import com.elf.core.persistence.BaseMapper;
import com.elf.sys.org.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     * @Description: 根据账号查询用户
     * @Param: [account]
     * @return: com.elf.sys.user.entity.User
     * @Author: Liyiming
     * @Date: 2018/3/14
     */
    @Select("select * from sys_org_user where account = #{account}")
    User selectByAccount(@Param("account") String account);

    /**
     * @Description: 根据组织单元查询用户
     * @Param: [unitId]
     * @return: java.util.List<com.elf.sys.org.entity.User>
     * @Author: Liyiming
     * @Date: 2018/5/1
     */
    List<User> selectUnitUsers(@Param("unitId") String unitId, @Param("account") String account, @Param("fullname") String fullname);
}
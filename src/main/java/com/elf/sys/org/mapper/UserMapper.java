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
    @Select("select u.* from sys_org_user u, sys_org_unit_user uu where u.user_id = uu.user_id and uu.unit_id = #{unitId}")
    List<User> getUsersByUnitId(@Param("unitId") String unitId);
}
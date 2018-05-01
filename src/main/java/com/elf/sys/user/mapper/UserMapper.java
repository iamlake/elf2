package com.elf.sys.user.mapper;//package com.elf.sys.user.mapper;
//
//import com.elf.core.persistence.BaseMapper;
//import com.elf.sys.user.entity.User;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
//public interface UserMapper extends BaseMapper<User> {
//    /**
//    * @Description: 根据账号查询用户
//    * @Param: [account]
//    * @return: com.elf.sys.user.entity.User
//    * @Author: Liyiming
//    * @Date: 2018/3/14
//    */
//    @Select("select * from sys_org_user where account = #{account}")
//    User selectByAccount(@Param("account") String account);
//}
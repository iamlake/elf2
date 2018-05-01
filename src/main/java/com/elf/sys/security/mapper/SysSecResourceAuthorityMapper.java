package com.elf.sys.security.mapper;

import com.elf.sys.security.entity.SysSecResourceAuthority;
import com.elf.sys.security.entity.SysSecResourceAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSecResourceAuthorityMapper {
    long countByExample(SysSecResourceAuthorityExample example);

    int deleteByExample(SysSecResourceAuthorityExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSecResourceAuthority record);

    int insertSelective(SysSecResourceAuthority record);

    List<SysSecResourceAuthority> selectByExample(SysSecResourceAuthorityExample example);

    SysSecResourceAuthority selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSecResourceAuthority record, @Param("example") SysSecResourceAuthorityExample example);

    int updateByExample(@Param("record") SysSecResourceAuthority record, @Param("example") SysSecResourceAuthorityExample example);

    int updateByPrimaryKeySelective(SysSecResourceAuthority record);

    int updateByPrimaryKey(SysSecResourceAuthority record);
}
package com.elf.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseMapper<Record, Example> {
    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(String id);

    int insert(Record record);

    int insertSelective(Record record);

    List<Record> selectByExample(Example example);

    Record selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example);

    int updateByExample(@Param("record") Record record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
}
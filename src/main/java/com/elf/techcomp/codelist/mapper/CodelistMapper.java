package com.elf.techcomp.codelist.mapper;

import com.elf.core.persistence.BaseMapper;
import com.elf.techcomp.codelist.entity.Codelist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: elf
 * @description: CodelistMapper
 * @author: Liyiming
 * @create: 2018-03-30 22:55
 **/
public interface CodelistMapper extends BaseMapper<Codelist> {

    /**
     * @Description: 查询codelist分类列表
     * @Param: [codeType, codeTypeName]
     * @return: java.util.List<com.elf.techcomp.codelist.entity.Codelist>
     * @Author: Liyiming
     * @Date: 2018/5/11
     */
    List<Codelist> selectCodeTypeList(@Param("codeType") String codeType, @Param("codeTypeName") String codeTypeName);
}

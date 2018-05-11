package com.elf.techcomp.codelist.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.techcomp.codelist.entity.Codelist;
import com.elf.techcomp.codelist.mapper.CodelistMapper;
import com.elf.techcomp.codelist.service.CodelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: elf
 * @description: CodelistServiceImpl
 * @author: Liyiming
 * @create: 2018-03-30 22:58
 **/
@Service
public class CodelistServiceImpl extends BaseServiceImpl<CodelistMapper, Codelist> implements CodelistService {
    @Autowired
    private CodelistMapper codelistMapper;

    @Override
    public List<Codelist> getCodeList(Codelist codelist) {
        EntityWrapper<Codelist> entityWrapper = new EntityWrapper<>(codelist);
        List<Codelist> list = codelistMapper.selectList(entityWrapper);
        return list;
    }

    @Override
    public List<Codelist> getCodeTypeList(Codelist codelist) {
        List<Codelist> list = codelistMapper.selectCodeTypeList(codelist.getCodeType(), "%" + codelist.getCodeTypeName() + "%");
        return list;
    }

    @Override
    public int deleteCodeTypeList(String codeType) {
        int ret = 0;
        if (StringUtils.isNotBlank(codeType)) {
            EntityWrapper<Codelist> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("CODE_TYPE", codeType);
            ret = codelistMapper.delete(entityWrapper);
        }
        return ret;
    }
}

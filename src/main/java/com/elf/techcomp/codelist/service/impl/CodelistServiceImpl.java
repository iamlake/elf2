package com.elf.techcomp.codelist.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.org.entity.User;
import com.elf.techcomp.codelist.entity.Codelist;
import com.elf.techcomp.codelist.mapper.CodelistMapper;
import com.elf.techcomp.codelist.service.CodelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
        entityWrapper.orderBy("CODE_ORDER", true);
        List<Codelist> list = codelistMapper.selectList(entityWrapper);
        return list;
    }

    @Override
    public List<Codelist> getCodeTypeList(Codelist codelist) {
        List<Codelist> list = codelistMapper.selectCodeTypeList(codelist.getCodeType(), StringUtils.isNotBlank(codelist.getCodeTypeName()) ? "%" + codelist.getCodeTypeName() + "%" : null);
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

    /**
     * @Description: 批量保存Codelist
     * @Param: [codelists]
     * @return: java.util.List<com.elf.techcomp.codelist.entity.Codelist>
     * @Author: Liyiming
     * @Date: 2018/5/12
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Codelist> saveCodelist(List<Codelist> codelists) {
        String codeType = codelists.get(0).getCodeType();
        this.deleteCodeTypeList(codeType);

        User currentUser = ContextHolder.getContext().getCurrentUser();
        String account = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        for (Codelist codelist : codelists) {
            if (StringUtils.isBlank(codelist.getCodeId())) {
                codelist.setCodeId(StringUtils.getUUID());
                codelist.setCreatedBy(account);
                codelist.setCreationTime(currentTime);
            }
            codelist.setModifiedBy(account);
            codelist.setCreationTime(currentTime);
            codelistMapper.insert(codelist);
        }
        return codelists;
    }
}

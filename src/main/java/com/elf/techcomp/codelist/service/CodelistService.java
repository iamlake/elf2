package com.elf.techcomp.codelist.service;

import com.elf.core.service.BaseService;
import com.elf.techcomp.codelist.entity.Codelist;

import java.util.List;

/**
 * @program: elf
 * @description: CodelistService
 * @author: Liyiming
 * @create: 2018-03-30 22:57
 **/
public interface CodelistService extends BaseService<Codelist> {
    List<Codelist> getCodeList(Codelist codelist);

    List<Codelist> getCodeTypeList(Codelist codelist);

    int deleteCodeTypeList(String codeType);

    List<Codelist> saveCodelist(List<Codelist> codelists);
}

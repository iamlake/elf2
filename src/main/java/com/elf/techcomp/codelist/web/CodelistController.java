package com.elf.techcomp.codelist.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.techcomp.codelist.entity.Codelist;
import com.elf.techcomp.codelist.service.CodelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: elf
 * @description: CodelistController
 * @author: Liyiming
 * @create: 2018-03-30 23:03
 **/
@RestController
public class CodelistController extends BaseController {

    @Autowired
    private CodelistService codelistService;

    /**
     * @Description: 查询代码表
     * @Param: [codelist]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    @GetMapping("/codelist")
    public Result findCodelist(Codelist codelist) {
        List<Codelist> list = codelistService.selectList(new EntityWrapper<>(codelist));
        return new QueryResult<>(Global.RESULT_STAUTS_SUCCESS, "", list, list.size());
    }
}

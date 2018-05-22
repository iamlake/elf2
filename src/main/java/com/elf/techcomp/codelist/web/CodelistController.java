package com.elf.techcomp.codelist.web;

import com.elf.core.persistence.constants.ResultStatusEnum;
import com.elf.core.persistence.result.JSONResult;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.techcomp.codelist.entity.Codelist;
import com.elf.techcomp.codelist.service.CodelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<Codelist> list = codelistService.getCodeList(codelist);
        return new QueryResult<>(ResultStatusEnum.SUCCESS.getValue(), "", list, list.size());
    }

    /**
     * @Description: 查询代码表分类列表
     * @Param: [codelist]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/11
     */
    @GetMapping("/codelist/type")
    public Result findCodeTypeList(Codelist codelist) {
        List<Codelist> list = codelistService.getCodeTypeList(codelist);
        return new QueryResult<>(ResultStatusEnum.SUCCESS.getValue(), "", list, list.size());
    }

    /**
     * @Description: 删除代码表
     * @Param: [codelist]
     * @return: com.elf.core.persistence.result.Result
     * @Author: Liyiming
     * @Date: 2018/5/11
     */
    @DeleteMapping("/codelist")
    public Result deleteCodelist(Codelist codelist) {
        JSONResult result = new JSONResult();
        try {
            int ret = codelistService.deleteCodeTypeList(codelist.getCodeType());
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            if (ret > 0) {
                result.setMsg("删除成功！");
            } else {
                result.setMsg("没有数据被删除！");
            }
            result.getParameters().put("", "");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("删除失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }

    @PostMapping("/codelist/type")
    public Result saveCodelist(@RequestBody List<Codelist> codelists) {
        JSONResult result = new JSONResult();
        try {
            List<Codelist> newCodelists = codelistService.saveCodelist(codelists);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            if (newCodelists.size() > 0) {
                result.setMsg("保存成功！");
            } else {
                result.setMsg("没有数据被保存！");
            }
            result.getParameters().put("object", newCodelists);
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("保存失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }
}
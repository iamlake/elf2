package com.elf.sys.org.web;

import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.constants.ResultStatusEnum;
import com.elf.core.persistence.result.JSONResult;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.org.entity.Dimension;
import com.elf.sys.org.entity.DimensionUnit;
import com.elf.sys.org.entity.Unit;
import com.elf.sys.org.entity.UnitUser;
import com.elf.sys.org.service.UnitService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
public class UnitController extends BaseController {

    @Autowired
    private UnitService unitService;

    @GetMapping("/dimension")
    @RequiresPermissions("org:unit:getDimensionList")
    public Result findDimensionList() {
        List<Dimension> dimensionList = unitService.getDimensionList();
        QueryResult<Dimension> result = new QueryResult<>();
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(dimensionList);
        result.setCount(dimensionList.size());
        return result;
    }

    @PostMapping("/dimension")
    public Result addDimension(Dimension dimension) {
        JSONResult result = new JSONResult();
        Dimension newDimension = unitService.saveDimension(dimension);
        if (newDimension != null) {
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("添加成功！");
            result.getParameters().put("object", newDimension);
        } else {
            result.setCode(ResultStatusEnum.FAILED.getValue());
            result.setMsg("添加失败！");
        }
        return result;
    }

    @DeleteMapping("/dimension")
    public Result removeDimension(String dimensionId) {
        JSONResult result = new JSONResult();
        try {
            unitService.deleteDimension(dimensionId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("删除成功！");
            result.getParameters().put("", "");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("删除失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }

    @GetMapping("/dimensionUnit/root")
    public Result findRootDimensionUnit(String dimensionId) {
        DimensionUnit dimensionUnit = unitService.getRootDimensionUnit(dimensionId);
        QueryResult<DimensionUnit> result = new QueryResult<>();
        ArrayList<DimensionUnit> dimensionUnitList = new ArrayList<>();
        dimensionUnitList.add(dimensionUnit);
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(dimensionUnitList);
        result.setCount(dimensionUnitList.size());
        return result;
    }

    @GetMapping("/dimensionUnit/child")
    public Result findChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
        List<DimensionUnit> dimensionUnitList = unitService.getChildDimensionUnitList(parentDimensionUnitId, dimensionId);
        QueryResult<DimensionUnit> result = new QueryResult<>();
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(dimensionUnitList);
        result.setCount(dimensionUnitList.size());
        return result;
    }

    @GetMapping("/dimensionUnit/allChild")
    public Result findAllChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
        List<DimensionUnit> dimensionUnitList = unitService.getAllChildDimensionUnitList(parentDimensionUnitId, dimensionId);
        QueryResult<DimensionUnit> result = new QueryResult<>();
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(dimensionUnitList);
        result.setCount(dimensionUnitList.size());
        return result;
    }

    @GetMapping("/unit/{unitId}")
    public Result findUnitById(@PathVariable("unitId") String unitId) {
        Unit unit = unitService.getUnitById(unitId);
        QueryResult<Unit> result = new QueryResult<>();
        ArrayList<Unit> unitList = new ArrayList<>();
        unitList.add(unit);
        result.setCode(ResultStatusEnum.SUCCESS.getValue());
        result.setData(unitList);
        result.setCount(unitList.size());
        return result;
    }

    @PostMapping("/unitAndDimensionUnit")
    public Result addUnitAndDimensionUnit(Unit paramUnit, String parentDimensionUnitId, String dimensionId) {
        JSONResult result = new JSONResult();
        try {
            DimensionUnit dimensionUnit = unitService.saveUnitAndDimensionUnit(paramUnit, parentDimensionUnitId, dimensionId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("添加成功！");
            result.getParameters().put("object", dimensionUnit);
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("添加失败！");
        }
        return result;
    }

    @PutMapping("/unitAndDimensionUnit")
    public Result modifyUnitAndDimensionUnit(Unit paramUnit) {
        JSONResult result = new JSONResult();
        try {
            DimensionUnit dimensionUnit = unitService.updateUnitAndDimensionUnit(paramUnit);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("修改成功！");
            result.getParameters().put("object", dimensionUnit);
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("修改失败！");
        }
        return result;
    }

    @DeleteMapping("/unitAndDimensionUnit")
    public Result removeUnitAndDimensionUnit(String dimensionUnitId) {
        JSONResult result = new JSONResult();
        try {
            unitService.deleteUnitAndDimensionUnit(dimensionUnitId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("删除成功！");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("删除失败！");
        }
        return result;
    }

    @PostMapping("/unitUsers")
    public Result addUnitUsers(String userIds, String unitId) {
        List<String> userIdsList = null;
        if (StringUtils.isNotBlank(userIds)) {
            userIdsList = new ArrayList<>();
            Collections.addAll(userIdsList, userIds.split(","));
        }
        JSONResult result = new JSONResult();
        try {
            unitService.saveUnitUsers(userIdsList, unitId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("添加成功！");
            result.getParameters().put("", "");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("添加失败！");
        }
        return result;
    }

    @DeleteMapping("/unitUsers")
    public Result removeUnitUsers(String userIds, String unitId) {
        List<String> userIdsList = null;
        if (StringUtils.isNotBlank(userIds)) {
            userIdsList = new ArrayList<>();
            Collections.addAll(userIdsList, userIds.split(","));
        }
        JSONResult result = new JSONResult();
        try {
            unitService.deleteUnitUsers(userIdsList, unitId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("删除成功！");
            result.getParameters().put("", "");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.getErrors().put("exception", ex);
            result.setMsg("删除失败！");
        }
        return result;
    }

    @PostMapping("/unitUser")
    public Result addUnitUser(String userId, String unitId) {
        JSONResult result = new JSONResult();
        try {
            UnitUser newUnitUser = unitService.saveUnitUser(userId, unitId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("添加成功！");
            result.getParameters().put("object", newUnitUser);
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("添加失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }

    @DeleteMapping("/unitUser")
    public Result removeUnitUser(String userId, String unitId) {
        JSONResult result = new JSONResult();
        try {
            unitService.deleteUnitUser(userId, unitId);
            result.setCode(ResultStatusEnum.SUCCESS.getValue());
            result.setMsg("删除成功！");
            result.getParameters().put("", "");
        } catch (Exception ex) {
            result.setCode(ResultStatusEnum.ERROR.getValue());
            result.setMsg("删除失败！");
            result.getErrors().put("exception", ex);
        }
        return result;
    }
}

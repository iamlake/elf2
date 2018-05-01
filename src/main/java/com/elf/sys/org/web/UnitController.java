package com.elf.sys.org.web;

import com.elf.core.persistence.constants.Global;
import com.elf.core.persistence.result.QueryResult;
import com.elf.core.persistence.result.Result;
import com.elf.core.web.BaseController;
import com.elf.sys.org.entity.SysOrgDimension;
import com.elf.sys.org.entity.SysOrgDimensionUnit;
import com.elf.sys.org.entity.SysOrgUnit;
import com.elf.sys.org.entity.SysOrgUnitUser;
import com.elf.sys.org.service.UnitService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/org/unit")
public class UnitController extends BaseController {
    
    @Autowired
    private UnitService unitService;
    
    @RequestMapping(value = "/getDimensionList", method = {RequestMethod.POST, RequestMethod.GET})
    @RequiresPermissions("org:unit:getDimensionList")
    @ResponseBody
    public Result getDimensionList() {
    	List<SysOrgDimension> dimensionList = unitService.getDimensionList();
    	QueryResult<SysOrgDimension> result = new QueryResult<SysOrgDimension>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(dimensionList);
    	result.setCount(dimensionList.size());
        return result;
    }
    
    @RequestMapping(value = "/saveDimension", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result saveDimension(SysOrgDimension dimension) {
    	dimension = unitService.saveDimension(dimension);
    	List<SysOrgDimension> dimensionList = new ArrayList<SysOrgDimension>();
    	dimensionList.add(dimension);
    	QueryResult<SysOrgDimension> result = new QueryResult<SysOrgDimension>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(dimensionList);
    	result.setCount(dimensionList.size());
        return result;
    }
    
    @RequestMapping(value = "/deleteDimension", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result deleteDimension(String dimensionId) {
    	unitService.deleteDimension(dimensionId);
    	QueryResult<SysOrgDimension> result = new QueryResult<SysOrgDimension>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
        return result;
    }
    
    @RequestMapping(value = "/getRootDimensionUnit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result getRootDimensionUnit(String dimensionId) {
    	SysOrgDimensionUnit dimensionUnit = unitService.getRootDimensionUnit(dimensionId);
    	QueryResult<SysOrgDimensionUnit> result = new QueryResult<SysOrgDimensionUnit>();
    	ArrayList<SysOrgDimensionUnit> dimensionUnitList = new ArrayList<SysOrgDimensionUnit>();
    	dimensionUnitList.add(dimensionUnit);
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(dimensionUnitList);
    	result.setCount(dimensionUnitList.size());
        return result;
    }
    
    @RequestMapping(value = "/getChildDimensionUnitList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result getChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
    	List<SysOrgDimensionUnit> dimensionUnitList = unitService.getChildDimensionUnitList(parentDimensionUnitId, dimensionId);
    	QueryResult<SysOrgDimensionUnit> result = new QueryResult<SysOrgDimensionUnit>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(dimensionUnitList);
    	result.setCount(dimensionUnitList.size());
        return result;
    }
    
    @RequestMapping(value = "/getAllChildDimensionUnitList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result getAllChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
    	List<SysOrgDimensionUnit> dimensionUnitList = unitService.getAllChildDimensionUnitList(parentDimensionUnitId, dimensionId);
    	QueryResult<SysOrgDimensionUnit> result = new QueryResult<SysOrgDimensionUnit>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(dimensionUnitList);
    	result.setCount(dimensionUnitList.size());
        return result;
    }
    
    @RequestMapping(value = "/getUnitById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result getUnitById(String id) {
    	SysOrgUnit unit = unitService.getUnitById(id);
    	QueryResult<SysOrgUnit> result = new QueryResult<SysOrgUnit>();
    	ArrayList<SysOrgUnit> unitList = new ArrayList<SysOrgUnit>();
    	unitList.add(unit);
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(unitList);
    	result.setCount(unitList.size());
        return result;
    }
    
    @RequestMapping(value = "/saveUnitAndDimensionUnit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result saveUnitAndDimensionUnit(SysOrgUnit paramUnit, String parentDimensionUnitId, String dimensionId) {
    	SysOrgDimensionUnit dimensionUnit = unitService.saveUnitAndDimensionUnit(paramUnit, parentDimensionUnitId, dimensionId);
    	QueryResult<SysOrgDimensionUnit> result = new QueryResult<SysOrgDimensionUnit>();
    	ArrayList<SysOrgDimensionUnit> dimensionUnitList = new ArrayList<SysOrgDimensionUnit>();
    	dimensionUnitList.add(dimensionUnit);
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(dimensionUnitList);
    	result.setCount(dimensionUnitList.size());
        return result;
    }
    
    @RequestMapping(value = "/updateUnitAndDimensionUnit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result updateUnitAndDimensionUnit(SysOrgUnit paramUnit, String parentDimensionUnitId, String dimensionId) {
    	SysOrgDimensionUnit dimensionUnit = unitService.updateUnitAndDimensionUnit(paramUnit);
    	QueryResult<SysOrgDimensionUnit> result = new QueryResult<SysOrgDimensionUnit>();
    	ArrayList<SysOrgDimensionUnit> dimensionUnitList = new ArrayList<SysOrgDimensionUnit>();
    	dimensionUnitList.add(dimensionUnit);
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
    	result.setData(dimensionUnitList);
    	result.setCount(dimensionUnitList.size());
        return result;
    }
    
    @RequestMapping(value = "/deleteUnitAndDimensionUnit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result deleteUnitAndDimensionUnit(String dimensionUnitId) {
    	unitService.deleteUnitAndDimensionUnit(dimensionUnitId);
    	QueryResult<SysOrgDimensionUnit> result = new QueryResult<SysOrgDimensionUnit>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
        return result;
    }
    
    @RequestMapping(value = "/saveUnitUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result saveUnitUser(String userId, String unitId) {
    	unitService.saveUnitUser(userId, unitId);
    	QueryResult<SysOrgUnitUser> result = new QueryResult<SysOrgUnitUser>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
        return result;
    }
    
    @RequestMapping(value = "/deleteUnitUser", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result deleteUnitUser(String userId, String unitId) {
    	unitService.deleteUnitUser(userId, unitId);
    	QueryResult<SysOrgUnitUser> result = new QueryResult<SysOrgUnitUser>();
    	result.setCode(Global.RESULT_STAUTS_SUCCESS);
        return result;
    }
    
}

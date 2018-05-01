package com.elf.sys.org.service.impl;

import com.elf.core.context.context.ContextHolder;
import com.elf.core.exception.BusinessException;
import com.elf.sys.org.entity.SysOrgDimension;
import com.elf.sys.org.entity.SysOrgDimensionExample;
import com.elf.sys.org.entity.SysOrgDimensionUnit;
import com.elf.sys.org.entity.SysOrgDimensionUnitExample;
import com.elf.sys.org.entity.SysOrgUnit;
import com.elf.sys.org.entity.SysOrgUnitExample;
import com.elf.sys.org.entity.SysOrgUnitUser;
import com.elf.sys.org.entity.SysOrgUnitUserExample;
import com.elf.sys.org.entity.User;
import com.elf.sys.org.mapper.DimensionMapper;
import com.elf.sys.org.mapper.DimensionUnitMapper;
import com.elf.sys.org.mapper.UnitMapper;
import com.elf.sys.org.mapper.UnitUserMapper;
import com.elf.sys.org.service.UnitService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {

	@Autowired
    private DimensionMapper dimensionMapper;
	
    @Autowired
    private UnitMapper unitMapper;
    
    @Autowired
    private DimensionUnitMapper dimensionUnitMapper;
    
    @Autowired
    private UnitUserMapper unitUserMapper;
    
    @Override
    public List<SysOrgDimension> getDimensionList() {
    	List<SysOrgDimension> dimensionList = dimensionMapper.selectByExample(new SysOrgDimensionExample());
        return dimensionList;
    }
    
    @Override
    public SysOrgDimension saveDimension(SysOrgDimension dimension) {
    	User user = ContextHolder.getContext().getCurrentUser();
    	String account = user.getAccount();
		Timestamp time = new Timestamp(new Date().getTime());
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		dimension.setId(id);
    	dimension.setCreatedBy(account);
    	dimension.setCreationTime(time);
    	dimension.setModifiedBy(account);
    	dimension.setModificationTime(time);
    	dimensionMapper.insertSelective(dimension);
        return dimension;
    }
    
    @Override
    public void deleteDimension(String dimensionId) {
    	SysOrgDimensionUnitExample example = new SysOrgDimensionUnitExample();
    	example.or().andDimensionIdEqualTo(dimensionId);
    	if(dimensionUnitMapper.countByExample(example)>0) {
    		//当前维度下存在维度组织单元
    		throw new BusinessException("SYSORG0005");
    	} 	
    	dimensionMapper.deleteByPrimaryKey(dimensionId);
    }
    
    @Override
    public List<SysOrgDimensionUnit> getChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
    	List<SysOrgDimensionUnit> dimensionUnitList = dimensionUnitMapper.getChildDimensionUnitList(parentDimensionUnitId, dimensionId);
        return dimensionUnitList;
    }
    
    @Override
    public List<SysOrgDimensionUnit> getAllChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
    	SysOrgDimensionUnit dimensionUnit = dimensionUnitMapper.selectByPrimaryKey(parentDimensionUnitId);
    	List<SysOrgDimensionUnit> dimensionUnitList = dimensionUnitMapper.getDimensionUnitsByUnitPath(dimensionUnit.getUnitPath()+dimensionUnit.getDimensionUnitId()+"/"+"%", dimensionId);
        return dimensionUnitList;
    }
    
    @Override
    public SysOrgUnit getUnitById(String id) {
    	SysOrgUnit unit = unitMapper.getUnitById(id);
        return unit;
    }
     
    @Override
    public SysOrgDimensionUnit getRootDimensionUnit(String dimensionId) {
    	SysOrgDimensionUnitExample example = new SysOrgDimensionUnitExample();
    	example.or().andDimensionIdEqualTo(dimensionId).andParentDimensionUnitIdEqualTo("-1");
    	List<SysOrgDimensionUnit> dimensionUnitList = dimensionUnitMapper.selectByExample(example);
    	if(dimensionUnitList.size()<1) {
    		return null;
    	}
    	SysOrgDimensionUnit dimensionUnit = dimensionUnitList.get(0);
    	return dimensionUnit;
    }
    
    @Override
    public SysOrgDimensionUnit saveUnitAndDimensionUnit(SysOrgUnit paramUnit, String parentDimensionUnitId, String dimensionId) {
    	User user = ContextHolder.getContext().getCurrentUser();
    	String account = user.getAccount();
		Timestamp time = new Timestamp(new Date().getTime());
		String unitId = UUID.randomUUID().toString().replaceAll("-", "");
		paramUnit.setId(unitId);
		paramUnit.setCreatedBy(account);
		paramUnit.setCreationTime(time);
		paramUnit.setModifiedBy(account);
		paramUnit.setModificationTime(time);
		paramUnit.setActiveFlag("T");
    	unitMapper.insertSelective(paramUnit);
    	
    	SysOrgDimensionUnit dimensionUnit = new SysOrgDimensionUnit();
    	String dimensionUnitId = UUID.randomUUID().toString().replaceAll("-", "");
    	dimensionUnit.setDimensionUnitId(dimensionUnitId);
    	dimensionUnit.setCreationTime(time);
		dimensionUnit.setCreatedBy(account);
		dimensionUnit.setModifiedBy(account);
		dimensionUnit.setModificationTime(time);
		dimensionUnit.setUnitId(paramUnit.getId());
		dimensionUnit.setDimensionId(dimensionId);
		dimensionUnit.setParentDimensionUnitId(parentDimensionUnitId);
		dimensionUnit.setAliasName(paramUnit.getName());
		dimensionUnit.setActiveFlag("T");
		
		
		SysOrgDimensionUnit parentDimensionUnit = dimensionUnitMapper.selectByPrimaryKey(parentDimensionUnitId);
		if (parentDimensionUnit != null) {
			dimensionUnit.setDepth(new BigDecimal((parentDimensionUnit).getDepth().intValue() + 1));
			dimensionUnit.setUnitPath(parentDimensionUnit.getUnitPath() + parentDimensionUnitId + "/");
		} else {
			dimensionUnit.setDepth(new BigDecimal(1));
			dimensionUnit.setUnitPath("/");
		}
		dimensionUnitMapper.insertSelective(dimensionUnit);
		
    	return dimensionUnit;
    }
    
    @Override
    public SysOrgDimensionUnit updateUnitAndDimensionUnit(SysOrgUnit paramUnit) {
    	User user = ContextHolder.getContext().getCurrentUser();
    	String account = user.getAccount();
		Timestamp time = new Timestamp(new Date().getTime());
		paramUnit.setModifiedBy(account);
		paramUnit.setModificationTime(time);
    	unitMapper.updateByPrimaryKeySelective(paramUnit);
    	
    	SysOrgDimensionUnitExample example = new SysOrgDimensionUnitExample();
    	example.or().andUnitIdEqualTo(paramUnit.getId());
    	List<SysOrgDimensionUnit> dimensionUnitList = dimensionUnitMapper.selectByExample(example);
		SysOrgDimensionUnit dimensionUnit = dimensionUnitList.get(0);
    	dimensionUnit.setModifiedBy(account);
		dimensionUnit.setModificationTime(time);
		dimensionUnit.setAliasName(paramUnit.getName());
		dimensionUnit.setActiveFlag("T");	
		dimensionUnitMapper.updateByPrimaryKeySelective(dimensionUnit);
		
    	return dimensionUnit;
    }
    
    @Override
    public void deleteUnitAndDimensionUnit(String dimensionUnitId) {
    	SysOrgDimensionUnit dimensionUnit = dimensionUnitMapper.selectByPrimaryKey(dimensionUnitId);
    	String unitId = dimensionUnit.getUnitId();
    	SysOrgUnitUserExample example = new SysOrgUnitUserExample();
    	example.or().andUnitIdEqualTo(unitId);
    	if (unitUserMapper.countByExample(example) > 0) {
    		//当前单元有用户绑定
			throw new BusinessException("SYSORG0001");
		}

		/*if (stationMapper.getStationsByUnitId(unitId).size() > 0) {
			//当前单元有职位绑定
			throw new BusinessException("EAPTECHORG0003");
		}*/
    	
    	SysOrgDimensionUnitExample dimensionUnitExample = new SysOrgDimensionUnitExample();
		String unitPath = dimensionUnit.getUnitPath();
		dimensionUnitExample.or().andUnitPathLike(unitPath+"%");

		if (dimensionUnitMapper.countByExample(dimensionUnitExample) > 0) {
			//当前单元有子节点
			throw new BusinessException("SYSORG0002");
		}
		
		dimensionUnitExample = new SysOrgDimensionUnitExample();
		dimensionUnitExample.or().andDimensionUnitIdEqualTo(dimensionUnitId);
		dimensionUnitMapper.deleteByExample(dimensionUnitExample);
		
		SysOrgUnitExample unitExample = new SysOrgUnitExample();
		unitExample.or().andIdEqualTo(unitId);
		unitMapper.deleteByExample(unitExample);		
    	
    }
    
    @Override
    public void saveUnitUser(String userId, String unitId) {
    	User user = ContextHolder.getContext().getCurrentUser();
    	String account = user.getAccount();
		Timestamp time = new Timestamp(new Date().getTime());
		
		SysOrgUnitUser unitUser = new SysOrgUnitUser();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		unitUser.setId(id);
		unitUser.setUserId(userId);
		unitUser.setUnitId(unitId);
		unitUser.setCreatedBy(account);
		unitUser.setCreationTime(time);
		unitUser.setModifiedBy(account);
		unitUser.setModificationTime(time);
		unitUserMapper.insertSelective(unitUser);
    	
    }
    
    @Override
    public void deleteUnitUser(String userId, String unitId) {
    	SysOrgUnitUserExample example = new SysOrgUnitUserExample();
    	example.or().andUserIdEqualTo(userId).andUnitIdEqualTo(unitId);
    	unitUserMapper.deleteByExample(example);
    	
    }

}

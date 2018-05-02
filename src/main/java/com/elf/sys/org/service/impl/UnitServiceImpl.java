package com.elf.sys.org.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.exception.BusinessException;
import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.org.entity.*;
import com.elf.sys.org.mapper.DimensionMapper;
import com.elf.sys.org.mapper.DimensionUnitMapper;
import com.elf.sys.org.mapper.UnitMapper;
import com.elf.sys.org.mapper.UnitUserMapper;
import com.elf.sys.org.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UnitServiceImpl extends BaseServiceImpl<UnitMapper, Unit> implements UnitService {

    @Autowired
    private DimensionMapper dimensionMapper;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private DimensionUnitMapper dimensionUnitMapper;

    @Autowired
    private UnitUserMapper unitUserMapper;

    @Override
    public List<Dimension> getDimensionList() {
        List<Dimension> dimensionList = dimensionMapper.selectList(new EntityWrapper<>());
        return dimensionList;
    }

    @Override
    public Dimension saveDimension(Dimension dimension) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String account = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        dimension.setId(StringUtils.getUUID());
        dimension.setCreatedBy(account);
        dimension.setCreationTime(currentTime);
        dimension.setModifiedBy(account);
        dimension.setModificationTime(currentTime);
        dimensionMapper.insert(dimension);
        return dimension;
    }

    @Override
    public int deleteDimension(String dimensionId) {
        EntityWrapper<DimensionUnit> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("dimension_id", dimensionId);
        if (dimensionUnitMapper.selectCount(entityWrapper) > 0) {
            //当前维度下存在维度组织单元
            throw new BusinessException("SYSORG0005");
        }
        int count = dimensionMapper.deleteById(dimensionId);
        return count;
    }

    @Override
    public List<DimensionUnit> getChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
        List<DimensionUnit> dimensionUnitList = dimensionUnitMapper.getChildDimensionUnitList(parentDimensionUnitId, dimensionId);
        return dimensionUnitList;
    }

    @Override
    public List<DimensionUnit> getAllChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
        DimensionUnit dimensionUnit = dimensionUnitMapper.selectById(parentDimensionUnitId);
        List<DimensionUnit> dimensionUnitList = dimensionUnitMapper.getDimensionUnitsByUnitPath(dimensionUnit.getUnitPath() + dimensionUnit.getDimensionUnitId() + "/" + "%", dimensionId);
        return dimensionUnitList;
    }

    @Override
    public Unit getUnitById(String id) {
        Unit unit = unitMapper.getUnitById(id);
        return unit;
    }

    @Override
    public DimensionUnit getRootDimensionUnit(String dimensionId) {
        EntityWrapper<DimensionUnit> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("dimension_id", dimensionId);
        entityWrapper.eq("parent_dimension_unit_id", "-1");
        List<DimensionUnit> dimensionUnitList = dimensionUnitMapper.selectList(entityWrapper);
        if (dimensionUnitList.size() < 1) {
            return null;
        }
        DimensionUnit dimensionUnit = dimensionUnitList.get(0);
        return dimensionUnit;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DimensionUnit saveUnitAndDimensionUnit(Unit paramUnit, String parentDimensionUnitId, String dimensionId) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String account = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        paramUnit.setId(StringUtils.getUUID());
        paramUnit.setCreatedBy(account);
        paramUnit.setCreationTime(currentTime);
        paramUnit.setModifiedBy(account);
        paramUnit.setModificationTime(currentTime);
        paramUnit.setActiveFlag("1");
        unitMapper.insert(paramUnit);

        DimensionUnit dimensionUnit = new DimensionUnit();
        dimensionUnit.setDimensionUnitId(StringUtils.getUUID());
        dimensionUnit.setCreationTime(currentTime);
        dimensionUnit.setCreatedBy(account);
        dimensionUnit.setModifiedBy(account);
        dimensionUnit.setModificationTime(currentTime);
        dimensionUnit.setUnitId(paramUnit.getId());
        dimensionUnit.setDimensionId(dimensionId);
        dimensionUnit.setParentDimensionUnitId(parentDimensionUnitId);
        dimensionUnit.setAliasName(paramUnit.getName());
        dimensionUnit.setActiveFlag("1");

        DimensionUnit parentDimensionUnit = dimensionUnitMapper.selectById(parentDimensionUnitId);
        if (parentDimensionUnit != null) {
            dimensionUnit.setDepth(new BigDecimal((parentDimensionUnit).getDepth().intValue() + 1));
            dimensionUnit.setUnitPath(parentDimensionUnit.getUnitPath() + parentDimensionUnitId + "/");
        } else {
            dimensionUnit.setDepth(new BigDecimal(1));
            dimensionUnit.setUnitPath("/");
        }
        dimensionUnitMapper.insert(dimensionUnit);

        return dimensionUnit;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DimensionUnit updateUnitAndDimensionUnit(Unit paramUnit) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String account = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        paramUnit.setModifiedBy(account);
        paramUnit.setModificationTime(currentTime);
        unitMapper.updateById(paramUnit);

        EntityWrapper<DimensionUnit> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("unit_id", paramUnit.getId());
        List<DimensionUnit> dimensionUnitList = dimensionUnitMapper.selectList(entityWrapper);
        DimensionUnit dimensionUnit = dimensionUnitList.get(0);
        dimensionUnit.setModifiedBy(account);
        dimensionUnit.setModificationTime(currentTime);
        dimensionUnit.setAliasName(paramUnit.getName());
        dimensionUnit.setActiveFlag("1");
        dimensionUnitMapper.updateById(dimensionUnit);

        return dimensionUnit;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUnitAndDimensionUnit(String dimensionUnitId) {
        DimensionUnit dimensionUnit = dimensionUnitMapper.selectById(dimensionUnitId);
        EntityWrapper<UnitUser> unitUserWrapper = new EntityWrapper<>();
        unitUserWrapper.eq("unit_id", dimensionUnit.getUnitId());
        if (unitUserMapper.selectCount(unitUserWrapper) > 0) {
            //当前单元有用户绑定
            throw new BusinessException("SYSORG0001");
        }

		/*if (stationMapper.getStationsByUnitId(unitId).size() > 0) {
			//当前单元有职位绑定
			throw new BusinessException("EAPTECHORG0003");
		}*/

        EntityWrapper<DimensionUnit> dimensionUnitWrapper = new EntityWrapper<>();
        dimensionUnitWrapper.like("unit_path", dimensionUnit.getUnitPath() + "%");

        if (dimensionUnitMapper.selectCount(dimensionUnitWrapper) > 0) {
            //当前单元有子节点
            throw new BusinessException("SYSORG0002");
        }

        dimensionUnitMapper.deleteById(dimensionUnitId);
        unitMapper.deleteById(dimensionUnit.getUnitId());
    }

    @Override
    public UnitUser saveUnitUser(String userId, String unitId) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String currentAccount = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        UnitUser unitUser = new UnitUser();
        unitUser.setId(StringUtils.getUUID());
        unitUser.setUserId(userId);
        unitUser.setUnitId(unitId);
        unitUser.setCreatedBy(currentAccount);
        unitUser.setCreationTime(currentTime);
        unitUser.setModifiedBy(currentAccount);
        unitUser.setModificationTime(currentTime);
        unitUserMapper.insert(unitUser);
        return unitUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUnitUsers(List<String> userIdsList, String unitId) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String currentAccount = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        UnitUser unitUser = null;
        for (String userId : userIdsList) {
            unitUser = new UnitUser();
            unitUser.setId(StringUtils.getUUID());
            unitUser.setUserId(userId);
            unitUser.setUnitId(unitId);
            unitUser.setCreatedBy(currentAccount);
            unitUser.setCreationTime(currentTime);
            unitUser.setModifiedBy(currentAccount);
            unitUser.setModificationTime(currentTime);
            unitUserMapper.insert(unitUser);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUnitUsers(List<String> userIdsList, String unitId) {
//        User currentUser = ContextHolder.getContext().getCurrentUser();
//        String currentAccount = currentUser.getAccount();
//        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        EntityWrapper<UnitUser> entityWrapper = null;
        for (String userId : userIdsList) {
            entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("user_id", userId).eq("unit_id", unitId);
            unitUserMapper.delete(entityWrapper);
        }
    }

    @Override
    public int deleteUnitUser(String userId, String unitId) {
        EntityWrapper<UnitUser> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_id", userId).eq("unit_id", unitId);
        int count = unitUserMapper.delete(entityWrapper);
        return count;
    }
}

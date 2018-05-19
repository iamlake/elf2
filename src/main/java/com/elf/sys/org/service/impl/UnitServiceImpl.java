package com.elf.sys.org.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.context.context.ContextHolder;
import com.elf.core.exception.BusinessException;
import com.elf.core.persistence.constants.Global;
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

    /**
     * @Description: 新增维度
     * @Param: [dimension]
     * @return: com.elf.sys.org.entity.Dimension
     * @Author: Liyiming
     * @Date: 2018/5/16
     */
    @Override
    public Dimension saveDimension(Dimension dimension) {
        User currentUser = ContextHolder.getContext().getCurrentUser();
        String account = currentUser.getAccount();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        dimension.setDimensionId(StringUtils.getUUID());
        dimension.setCreatedBy(account);
        dimension.setCreationTime(currentTime);
        dimension.setModifiedBy(account);
        dimension.setModificationTime(currentTime);
        dimensionMapper.insert(dimension);
        return dimension;
    }

    /**
     * @Description: 根据ID删除维度
     * @Param: [dimensionId]
     * @return: int
     * @Author: Liyiming
     * @Date: 2018/5/16
     */
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
        List<DimensionUnit> dimensionUnitList = dimensionUnitMapper.selectChildDimensionUnitList(parentDimensionUnitId, dimensionId);
        return dimensionUnitList;
    }

    /**
     * @Description: 通过父ID和维度ID查询所有组织维度数据
     * @Param: [parentDimensionUnitId, dimensionId]
     * @return: java.util.List<com.elf.sys.org.entity.DimensionUnit>
     * @Author: Liyiming
     * @Date: 2018/5/16
     */
    @Override
    public List<DimensionUnit> getAllChildDimensionUnitList(String parentDimensionUnitId, String dimensionId) {
        String defaultPId = "-1";
        String unitPath = "/";
        if (!defaultPId.equals(parentDimensionUnitId)) {
            DimensionUnit dimensionUnit = dimensionUnitMapper.selectById(parentDimensionUnitId);
            unitPath = dimensionUnit.getUnitPath() + dimensionUnit.getDimensionUnitId() + "/";
        }
        List<DimensionUnit> dimensionUnitList = dimensionUnitMapper.selectDimensionUnitsByUnitPath(unitPath + "%", dimensionId);
        return dimensionUnitList;
    }

    @Override
    public Unit getUnitById(String unitId) {
        Unit unit = unitMapper.selectById(unitId);
        return unit;
    }

    @Override
    public DimensionUnit getRootDimensionUnit(String dimensionId) {
        EntityWrapper<DimensionUnit> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("DIMENSION_ID", dimensionId);
        entityWrapper.eq("PARENT_DIMENSION_UNIT_ID", "-1");
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
        paramUnit.setUnitId(StringUtils.getUUID());
        paramUnit.setCreatedBy(account);
        paramUnit.setCreationTime(currentTime);
        paramUnit.setModifiedBy(account);
        paramUnit.setModificationTime(currentTime);
        paramUnit.setActiveFlag(Global.ACTIVE_FLAG_ENABLED);
        unitMapper.insert(paramUnit);

        DimensionUnit dimensionUnit = new DimensionUnit();
        dimensionUnit.setDimensionUnitId(StringUtils.getUUID());
        dimensionUnit.setCreationTime(currentTime);
        dimensionUnit.setCreatedBy(account);
        dimensionUnit.setModifiedBy(account);
        dimensionUnit.setModificationTime(currentTime);
        dimensionUnit.setUnitId(paramUnit.getUnitId());
        dimensionUnit.setDimensionId(dimensionId);
        dimensionUnit.setParentDimensionUnitId(parentDimensionUnitId);
        dimensionUnit.setAliasName(paramUnit.getUnitName());
        dimensionUnit.setActiveFlag(Global.ACTIVE_FLAG_ENABLED);

        DimensionUnit parentDimensionUnit = dimensionUnitMapper.selectById(parentDimensionUnitId);
        if (parentDimensionUnit != null) {
            dimensionUnit.setDimensionUnitLevel(new BigDecimal((parentDimensionUnit).getDimensionUnitLevel().intValue() + 1));
            dimensionUnit.setUnitPath(parentDimensionUnit.getUnitPath() + parentDimensionUnitId + "/");
        } else {
            dimensionUnit.setDimensionUnitLevel(new BigDecimal(1));
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
        entityWrapper.eq("unit_id", paramUnit.getUnitId());
        List<DimensionUnit> dimensionUnitList = dimensionUnitMapper.selectList(entityWrapper);
        DimensionUnit dimensionUnit = dimensionUnitList.get(0);
        dimensionUnit.setModifiedBy(account);
        dimensionUnit.setModificationTime(currentTime);
        dimensionUnit.setAliasName(paramUnit.getUnitName());
        dimensionUnit.setActiveFlag(Global.ACTIVE_FLAG_ENABLED);
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
        dimensionUnitWrapper.eq("parent_dimension_unit_id", dimensionUnit.getDimensionUnitId());

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
        unitUser.setUnitUserId(StringUtils.getUUID());
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
        UnitUser unitUser;
        for (String userId : userIdsList) {
            unitUser = new UnitUser();
            unitUser.setUnitUserId(StringUtils.getUUID());
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
        EntityWrapper<UnitUser> entityWrapper;
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

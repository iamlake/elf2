package com.elf.sys.security.service.impl;

import com.elf.core.context.context.ContextHolder;
import com.elf.core.exception.BusinessException;
import com.elf.sys.org.entity.User;
import com.elf.sys.org.mapper.UserMapper;
import com.elf.sys.security.entity.SysSecBusiRole;
import com.elf.sys.security.entity.SysSecBusiRoleExample;
import com.elf.sys.security.entity.SysSecBusiRoleUser;
import com.elf.sys.security.entity.SysSecBusiRoleUserExample;
import com.elf.sys.security.mapper.SysSecBusiRoleMapper;
import com.elf.sys.security.mapper.SysSecBusiRoleUserMapper;
import com.elf.sys.security.service.BusiRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BusiRoleServiceImpl implements BusiRoleService {

	@Autowired
	private SysSecBusiRoleMapper busiRoleMapper;

	@Autowired
	private SysSecBusiRoleUserMapper busiRoleUserMapper;

	@Autowired
	private UserMapper userMapper;

/*    @Autowired
    private SysSecAdminRoleBusiRoleMapper adminRoleBusiRoleMapper;

    @Autowired
    private SysSecResourceAuthorityMapper resourceAuthorityMapper;
    */

	@Override
	public List<SysSecBusiRole> getAllBusiRoles() {
		List<SysSecBusiRole> busiRoleList= busiRoleMapper.selectByExample(new SysSecBusiRoleExample());

		return busiRoleList;
	}

	@Override
	public SysSecBusiRole getBusiRoleById(String busiRoleId) {
		SysSecBusiRole busiRole = busiRoleMapper.selectByPrimaryKey(busiRoleId);

		return busiRole;
	}

	@Override
	public SysSecBusiRole saveBusiRole(SysSecBusiRole busiRole) {
		SysSecBusiRoleExample example = new SysSecBusiRoleExample();
		example.or().andNameEqualTo(busiRole.getName());
		if(busiRoleMapper.countByExample(example)>0) {
			throw new BusinessException("SYSSEC0001");
		}

		User user = ContextHolder.getContext().getCurrentUser();
		String account = user.getAccount();
		Timestamp time = new Timestamp(new Date().getTime());
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		busiRole.setId(id);
		busiRole.setActiveFlag("T");
		busiRole.setCreatedBy(account);
		busiRole.setCreationTime(time);
		busiRole.setModifiedBy(account);
		busiRole.setModificationTime(time);
		busiRoleMapper.insertSelective(busiRole);

		return busiRole;
	}

	@Override
	public SysSecBusiRole updateBusiRole(SysSecBusiRole busiRole) {
		SysSecBusiRoleExample example = new SysSecBusiRoleExample();
		example.or().andNameEqualTo(busiRole.getName());
		if(busiRoleMapper.countByExample(example)>0) {
			throw new BusinessException("SYSSEC0001");
		}

		User user = ContextHolder.getContext().getCurrentUser();
		String account = user.getAccount();
		Timestamp time = new Timestamp(new Date().getTime());
		busiRole.setModifiedBy(account);
		busiRole.setModificationTime(time);
		busiRoleMapper.updateByPrimaryKeySelective(busiRole);

		return busiRole;
	}

	@Override
	public void deleteBusiRoleById(String busiRoleId) {

    	/*busiRoleUserMapper.deleteByExample(example)

        adminRoleBusiRoleMapper.deleteByExample(example)

        resourceAuthorityMapper.deleteByExample(example)*/

		busiRoleMapper.deleteByPrimaryKey(busiRoleId);

	}

	@Override
	public List<SysSecBusiRole> getBusiRoles() {
		User currentUser = ContextHolder.getContext().getCurrentUser();
		String userId = currentUser.getUserId();
		List<SysSecBusiRole> busiRoleList= this.getBusiRolesByUserId(userId);

		return busiRoleList;
	}

	@Override
	public List<SysSecBusiRole> getBusiRolesByUserId(String userId) {
		List<SysSecBusiRole> busiRoleList = busiRoleMapper.getBusiRolesByUserId(userId);

		return busiRoleList;
	}

	@Override
	public void saveBusiRoleUser(String busiRoleId, List<String> userIdList) {
		User currentUser = ContextHolder.getContext().getCurrentUser();
		String account = currentUser.getAccount();
		Timestamp time = new Timestamp(new Date().getTime());
		String id = null;
		SysSecBusiRoleUser busiRoleUser = null;
		for(String userId : userIdList) {
			busiRoleUser = new SysSecBusiRoleUser();
			id = UUID.randomUUID().toString().replaceAll("-", "");
			busiRoleUser.setId(id);
			busiRoleUser.setRoleId(busiRoleId);
			busiRoleUser.setUserId(userId);
			busiRoleUser.setActiveFlag("T");
			busiRoleUser.setCreatedBy(account);
			busiRoleUser.setCreationTime(time);
			busiRoleUser.setModifiedBy(account);
			busiRoleUser.setModificationTime(time);
			busiRoleUserMapper.insertSelective(busiRoleUser);
		}
	}

	@Override
	public void deleteBusiRoleUser(String busiRoleId, String userId) {
		SysSecBusiRoleUserExample example = new SysSecBusiRoleUserExample();
		example.or().andRoleIdEqualTo(busiRoleId).andUserIdEqualTo(userId);
		busiRoleUserMapper.deleteByExample(example);

	}
}

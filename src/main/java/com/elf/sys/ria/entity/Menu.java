package com.elf.sys.ria.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("SYS_MENU")
public class Menu extends DataEntity{

    @TableId
    private String menuId;

    private String title;

    private String target;

    private String href;

    private String icon;

    private String spread;

    private String menuOrder;

    private String showDefault;

    private String parentMenuId;

    private String appId;

    @Override
    public void preInsert() {
        this.setMenuId(StringUtils.getUUID());
        this.setCreationTime(new Date());
    }

    @Override
    public void preUpdate() {
        this.setModificationTime(new Date());
    }

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getShowDefault() {
		return showDefault;
	}

	public void setShowDefault(String showDefault) {
		this.showDefault = showDefault;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
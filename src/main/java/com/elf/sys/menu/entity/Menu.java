package com.elf.sys.menu.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("SYS_MENU")
public class Menu extends DataEntity {

    @TableId
    private String menuId;

    private String title;

    private String target;

    private String href;

    private String icon;

    private String spread;

    private String menuOrder;

    private String level;

    private Boolean leaf;

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
}
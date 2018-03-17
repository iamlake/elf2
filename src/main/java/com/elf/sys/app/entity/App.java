package com.elf.sys.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("SYS_APP")
public class App extends DataEntity {
    @TableId
    private String appId;

    private String title;

    private String style;

    private String appType;

    private String appOrder;

    private String href;

    private String showDefault;

    @Override
    public void preInsert() {
        this.setAppId(StringUtils.getUUID());
        this.setCreationTime(new Date());
    }

    @Override
    public void preUpdate() {
        this.setModificationTime(new Date());
    }
}
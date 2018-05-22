package com.elf.sys.ria.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@TableName("SYS_MENU")
public class Menu extends DataEntity {

    @TableId
    private String menuId;

    private String title;

    private String target;

    private String href;

    private String icon;

    private String iconfont;

    private String spread;

    private String menuOrder;

    private String showDefault;

    private String parentMenuId;

    private String appId;

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}
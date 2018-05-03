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

    /**
     * @Description: serialVersionUID
     * @Author:李一鸣(liyiming.neu@neusoft.com)
     * @Date:2017年11月15日
     */
    private static final long serialVersionUID = 1L;
}
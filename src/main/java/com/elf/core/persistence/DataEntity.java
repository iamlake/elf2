package com.elf.core.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Title: DataEntity
 * @Description: 数据Entity支持类
 * @Author:李一鸣(liyiming.neu@neusoft.com)
 * @Date:2017年11月12日
 */
@Setter
@Getter
@ToString
public abstract class DataEntity<T> extends BaseEntity<T> {

    protected String remark;

    protected String activeFlag;

    protected String createdBy;

    protected Date creationTime;

    protected String modifiedBy;

    protected Date modificationTime;

    protected String ext1;

    protected String ext2;

    protected String ext3;

    /**
     * <br>Description: serialVersionUID
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月12日
     */
    private static final long serialVersionUID = 1L;

}
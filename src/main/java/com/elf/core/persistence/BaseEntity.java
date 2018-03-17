package com.elf.core.persistence;


import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * @Title: BaseEntity
 * @Description: Entity支持类
 * @Author:李一鸣(liyiming.neu@neusoft.com)
 * @Date:2017年11月12日
 */
public abstract class BaseEntity implements Serializable {

    /**
     * @Description: 新增标识
     * @Author:李一鸣(liyiming.neu@neusoft.com)
     * @Date:2017年11月12日
     */
    @TableField(exist=false)
    protected boolean isNew = false;

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    /**
     * @Description: 插入之前执行方法，子类实现
     * @Author:李一鸣(liyiming.neu@neusoft.com)
     * @Date:2017年11月23日
     */
    public abstract void preInsert();

    /**
     * @Description: 更新之前执行方法，子类实现
     * @Author:李一鸣(liyiming.neu@neusoft.com)
     * @Date:2017年11月23日
     */
    public abstract void preUpdate();

    /**
     * @Description: serialVersionUID
     * @Author:李一鸣(liyiming.neu@neusoft.com)
     * @Date:2017年11月12日
     */
    private static final long serialVersionUID = 1L;
}

package com.elf.core.persistence;


import javax.persistence.Transient;
import java.io.Serializable;

/**
 * <br>Title: BaseEntity
 * <br>Description: Entity支持类
 * <br>Author:李一鸣(liyiming.neu@neusoft.com)
 * <br>Date:2017年11月12日
 */
public abstract class BaseEntity<T> implements Serializable {

    /**
     * <br>Description: 新增标识
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月12日
     */
    @Transient
    protected boolean isNew = false;

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    /**
     * <br>Description: 插入之前执行方法，子类实现
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月23日
     */
    public abstract void preInsert();

    /**
     * <br>Description: 更新之前执行方法，子类实现
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月23日
     */
    public abstract void preUpdate();

    /**
     * <br>Description: serialVersionUID
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月12日
     */
    private static final long serialVersionUID = 1L;
}

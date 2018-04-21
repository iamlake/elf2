package com.elf.techcomp.codelist.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.common.utils.StringUtils;
import com.elf.core.persistence.DataEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Title Codelist
 * @Description Codelist
 * @Author Liyiming
 * @Date 2018/3/30 22:51
 */
@Data
@TableName("SYS_CODELIST")
public class Codelist extends DataEntity {
    @TableId
    private String codeId;

    private String codeValue;

    private String codeValueName;

    private String codeType;

    private String codeTypeName;

    private String codeParent;

    private String codeOrder;

    private String filter;

    private String language;

    @Override
    public void preInsert() {
        this.setCodeId(StringUtils.getUUID());
        this.setCreationTime(new Date());
    }

    @Override
    public void preUpdate() {
        this.setModificationTime(new Date());
    }

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}
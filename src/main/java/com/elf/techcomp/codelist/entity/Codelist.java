package com.elf.techcomp.codelist.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.elf.core.persistence.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Title Codelist
 * @Description Codelist
 * @Author Liyiming
 * @Date 2018/3/30 22:51
 */
@Setter
@Getter
@ToString
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

    /**
     * @Description: serialVersionUID
     * @Author: Liyiming
     * @Date: 2018/3/30
     */
    private static final long serialVersionUID = 1L;
}
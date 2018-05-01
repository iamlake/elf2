package com.elf.core.persistence.result;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Setter
@Getter
@ToString
public class Result implements Serializable {

    /**
     * <br>Description: 状态码
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月24日
     */
    private int code;

    /**
     * <br>Description: 返回信息
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月24日
     */
    private String msg;

    /**
     * <br>Description: serialVersionUID
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月24日
     */
    private static final long serialVersionUID = 1L;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
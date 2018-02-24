package com.elf.core.persistence.result;

import java.util.List;

public class QueryResult<T> extends Result {

    /**
     * <br>Description: 记录数
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月24日
     */
    private int count;

    /**
     * <br>Description: 封装查询结果集
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月24日
     */
    private List<T> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public QueryResult() {
        super();
    }

    public QueryResult(int code, String msg, List<T> data, int count) {
        this.data = data;
        this.count = count;
        super.setCode(code);
        super.setMsg(msg);
    }

    /**
     * <br>Description: serialVersionUID
     * <br>Author:李一鸣(liyiming.neu@neusoft.com)
     * <br>Date:2017年11月24日
     */
    private static final long serialVersionUID = 1L;

}
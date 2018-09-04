package com.kevin.demo.entity;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * DataGrid 数据返回模型
 *
 * @author fansheng
 */
public class DataGrid<T> implements Serializable {

    //---------------------------------普通返回 start
    private T obj;

    private boolean flag;

    private String msg;

    private String code;
    //---------------------------------普通返回 end

    //---------------------------------分页 start
    //数量
    private long total;
    //所有数据
    private List<T> rows = Lists.newArrayList();

    public DataGrid() {
        super();
    }

    public DataGrid(boolean flag, List<T> rows) {
        this.flag = flag;
        this.rows = rows;
        if(rows != null){
            this.total = rows.size();
        }
    }

    public DataGrid(boolean flag, T obj) {
        this.obj = obj;
        this.flag = flag;
    }

    public DataGrid(long total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public DataGrid(boolean flag, long total) {
        this.flag = flag;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

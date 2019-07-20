package com.qlhx.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李大
 */
public class DataGridResult implements Serializable {

    /**
     * 总条
     */
    private int total;
    /**
     * 数据list
     */
    private List<?> rows;
    /**
     * 数据list
     */
    private List<?> data;
    /**
     * 用于列表统计
     */
    private List<?> footer;

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the rows
     */
    public List<?> getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    /**
     * @return the footer
     */
    public List<?> getFooter() {
        return footer;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(List<?> footer) {
        this.footer = footer;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }


}

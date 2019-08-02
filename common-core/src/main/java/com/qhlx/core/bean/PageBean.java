package com.qhlx.core.bean;

import java.util.List;

/**
 * Create by xb
 * The file is [ PageBean] on [ book-system ] project
 * The file path is com.example.booksystem.domain.PageBean
 *
 * @versio 1.0.o
 * @Author he ming xi
 * @date 2019/4/6 17:11
 * @description
 *      分页对象
 */
public class PageBean<T> {
    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 起始行
     */
    private int startRow;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 返回数据
     */
    private List<T> resultData;


    private T requestData;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getResultData() {
        return resultData;
    }

    public void setResultData(List<T> resultData) {
        this.resultData = resultData;
    }

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }
}

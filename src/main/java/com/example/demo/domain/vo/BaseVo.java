package com.example.demo.domain.vo;

public class BaseVo {

    //当前页
    private Integer pageNum = 1;
    //每页数量
    private Integer pageSize = 20;
    //每页开始
    private Integer startRow;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartRow() {
        if(null == startRow){
            startRow = (pageNum - 1) * pageSize;
        }
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }
    
}

package com.kevin.demo.entity;

import java.io.Serializable;

public class PagingDto implements Serializable{
	private static final long serialVersionUID = -1595504837020950968L;
	
	private Integer pageNo;
    private Integer pageSize;
    private String[] orderBy;
    private String[] order;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String[] getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String[] orderBy) {
        this.orderBy = orderBy;
    }

    public String[] getOrder() {
        return order;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }

    public String toOrderSql(String tableAlias) {
        StringBuilder sb = new StringBuilder();

        if (this.order != null && this.order.length > 0) {
            sb.append(" order by ");

            for (int i = 0; i < this.order.length; i++) {
                // 替换成到数据库字段+order by 条件

                sb.append(tableAlias + "." + this.orderBy[i] + " ");
                sb.append(this.order[i] + " ");

                if (i < this.orderBy.length - 1) {
                    sb.append(",");
                }
            }
        }

        return sb.toString();
    }

}

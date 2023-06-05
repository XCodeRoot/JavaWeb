package com.CodeRoot.pojo;

import java.util.List;

/**
 *  Page是分页对象
 *
 * @param <T> 是具体的 模块的 javaBean类 , 可以适用于 不同类型对象的 分页显示功能
 */
public class Page<T> {




    public static final  Integer PAGE_SIZE=4;//当前页数量(容量


    private Integer pageNo;//当前页码
    private Integer pageTotal;//总页码
    private Integer pageSize=PAGE_SIZE;//当前页数量(容量
    private Integer pageTotalCount;//总记录数
    private List<T> items;//当前页数据

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;//分页条的请求地址
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //对pageNo 进行 限制范围
        if(pageNo<1){
            pageNo=1;
        }
        if(pageNo>pageTotal){
            pageNo=pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}

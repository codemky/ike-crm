package com.ike.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;

/**
 * ClassName PageInfo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/10 21:19
 **/
public class PageInfo implements Serializable {

    /**
     * Author: mokuanyuan
     * @apiNote: 分别为 当前页码 当前页大小 当前页记录数 总共页数
     * @since:  2019/10/10 22:14
     */
    private Long pageNum;
    private Long pageSize;
    private Long total;
    private Long totalPage;

    /**
     * Author: mokuanyuan
     * @param page
     * @apiNote:
     * @since:  2019/10/10 21:21
     */
    public static PageInfo getPageVo(IPage page){

        return new PageInfo(page.getCurrent(),page.getSize(),page.getTotal(),page.getPages());
    }

    private PageInfo(Long pageNum, Long pageSize, Long total, Long totalPage) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = totalPage;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
}

package com.myproject.pojo.base;


import lombok.Data;

/**
 * 分页参数
 * @author lihuazeng
 * @version Id: PageVO.java, v 0.1 2019-7-1 15:17 lihuazeng Exp $$
 */
@Data
public class PageVO {
    /**
     * 当前页, 默认第一页
     */
    private Integer pageNum = 1;
    /**
     * 每页的数量，默认10
     */
    private Integer pageSize = 10;
    /**
     * 当前页的数量
     */
    private Integer size;
    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 前一页
     */
    private Integer prePage;
    /**
     * 下一页
     */
    private Integer nextPage;

    /**
     * 排序字段
     */
    private String orderColumn;

    /**
     * 排序类型
     */
    public enum  OrderDirection{
        DESC,
        ASC;
        /**
         * 从String中获取Direction
         *
         * @param value 值
         * @return String对应的Direction
         */
        public static OrderDirection fromString(String value) {
            return OrderDirection.valueOf(value.toUpperCase());
        }
    }
    private OrderDirection orderDirection;


}
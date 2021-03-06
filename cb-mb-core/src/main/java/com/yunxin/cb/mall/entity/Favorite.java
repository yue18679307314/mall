package com.yunxin.cb.mall.entity;

import java.util.Date;

/**
 * @title: 收藏夹实体类
 * @auther: eleven
 * @date: 2018/7/18 20:01
 */
public class Favorite implements java.io.Serializable{
    /** 收藏Id */
    private Integer favoriteId;

    /** 创建时间 */
    private Date createTime;

    /** 销售价 */
    private Float salePrice;

    /** 商品id */
    private Integer commodityId;

    /** 货品id */
    private Integer productId;

    private Commodity commodity;

    /** 客户id */
    private Integer customerId;

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
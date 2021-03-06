package com.yunxin.cb.mall.vo;


import com.yunxin.cb.mall.entity.meta.SortBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@ApiModel(value="商品搜索对象",description="商品搜索对象 SearchVo")
public class SearchVo implements java.io.Serializable {

    private static final long serialVersionUID = -3123560903203859821L;

    @ApiModelProperty(value="品牌ID",name="brandId",example="1")
    private int brandId;

    @ApiModelProperty(value="分类ID",name="categoryId",example="1")
    private int categoryId;

    @ApiModelProperty(value="商家ID",name="sellerId",example="1")
    private int sellerId;

    @ApiModelProperty(value="最低销售价",name="lowestPrice",example="50")
    private int lowestPrice;

    @ApiModelProperty(value="最高销售价",name="highestPrice",example="500")
    private int highestPrice;

    @ApiModelProperty(value="价格段",name="priceSection",example="价格段")
    private PriceSection priceSection;

    @ApiModelProperty(value="商品筛选属性",name="commoditySpecs",example="商品筛选属性")
    private Set<CommoditySpec> commoditySpecs = new HashSet<>();

    @ApiModelProperty(value="城市编码",name="cityCode",example="440300")
    private String cityCode;

    @ApiModelProperty(value="排序字段",name="sortBy",example="sellPrice")
    private SortBy sortBy;

    @ApiModelProperty(value="排序方向，枚举:升序or降序",name="direction",example="ASC|DESC")
    private Sort.Direction direction;

    @NotNull(message = "返回行数size不能为空")
    @ApiModelProperty(value="返回行数",name="size",example="10", required = true)
    private Integer size;
    @NotNull(message = "页码page不能为空")
    @ApiModelProperty(value="页码",name="page",example="0", required = true)
    private Integer page;

    @ApiModelProperty(value="地理纬度",name="lat",example="18.257776")
    private Double lat;
    @ApiModelProperty(value="地理经度",name="lon",example="109.522771")
    private Double lon;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(int highestPrice) {
        this.highestPrice = highestPrice;
    }

    public PriceSection getPriceSection() {
        return priceSection;
    }

    public void setPriceSection(PriceSection priceSection) {
        this.priceSection = priceSection;
    }

    public Set<CommoditySpec> getCommoditySpecs() {
        return commoditySpecs;
    }

    public void setCommoditySpecs(Set<CommoditySpec> commoditySpecs) {
        this.commoditySpecs = commoditySpecs;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}

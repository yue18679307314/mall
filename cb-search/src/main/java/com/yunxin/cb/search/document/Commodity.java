package com.yunxin.cb.search.document;

import com.yunxin.cb.search.vo.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品
 *
 * @author
 */
@Document(indexName = "crystal_ball", type = "commodity", createIndex = false)
public class Commodity implements java.io.Serializable {

    public static final String index_name = "crystal_ball";
    public static final String index_type = "commodity";

    private static final long serialVersionUID = -3993560903203859821L;

    @Id
    private String id;

    /**
     * 商品ID
     */
    private int commodityId;

    /**
     * 商品所属价格段
     */
    private com.yunxin.cb.search.vo.PriceSection priceSection;
    /**
     * 品牌
     */
    @Field(type = FieldType.Object, fielddata = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private com.yunxin.cb.search.vo.Brand brand;
    /**
     * 供应商
     */
    private Seller seller;
    /**
     * 商品编码
     */
    private String commodityCode;
    /**
     * 商品名
     */
    @Field(type = FieldType.Text, fielddata = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String commodityName;
    /**
     * 商品拼音名
     */
    private String commodityPYName;
    /**
     * 简称
     */
    @Field(type = FieldType.Text, fielddata = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String shortName;
    /**
     * 商品标题
     */
    @Field(type = FieldType.Text, fielddata = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String commodityTitle;
    /**
     * 描述
     */
    @Field(type = FieldType.Text, fielddata = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String description;
    /**
     * 销售价
     */
    private double sellPrice;
    /**
     * 市场价格
     */
    private double marketPrice;
    /**
     * 产地省份
     */
    private String province;
    /**
     * 产地市区
     */
    private String city;
    /**
     * 默认图片路径
     */
    private String defaultPicPath;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否为热门商品
     */
    private boolean popular;
    /**
     * 是否为特惠商品
     */
    private boolean special;
    /**
     * 是否为推荐商品
     */
    private boolean recommend;
    /**
     * 销量
     */
    private int saleNum;
    /**
     * 商品分类
     */
    private Set<com.yunxin.cb.search.vo.Category> categories = new HashSet<>();

    /**
     * 商品规格
     */
    private Set<CommoditySpec> commoditySpecs = new HashSet<>();

    /**
     * 默认货品id
     */
    private int defaultProduct;

    /**
     * 地理位置经纬度
     * lat纬度，lon经度 "40.715,-74.011"
     * 如果用数组则相反[-73.983, 40.719]
     */
    @GeoPointField
    private GeoPoint location;

    /**
     * 距离
     */
    private BigDecimal distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public com.yunxin.cb.search.vo.PriceSection getPriceSection() {
        return priceSection;
    }

    public void setPriceSection(com.yunxin.cb.search.vo.PriceSection priceSection) {
        this.priceSection = priceSection;
    }

    public com.yunxin.cb.search.vo.Brand getBrand() {
        return brand;
    }

    public void setBrand(com.yunxin.cb.search.vo.Brand brand) {
        this.brand = brand;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityPYName() {
        return commodityPYName;
    }

    public void setCommodityPYName(String commodityPYName) {
        this.commodityPYName = commodityPYName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public Set<com.yunxin.cb.search.vo.Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<com.yunxin.cb.search.vo.Category> categories) {
        this.categories = categories;
    }

    public Set<CommoditySpec> getCommoditySpecs() {
        return commoditySpecs;
    }

    public void setCommoditySpecs(Set<CommoditySpec> commoditySpecs) {
        this.commoditySpecs = commoditySpecs;
    }

    public int getDefaultProduct() {
        return defaultProduct;
    }

    public void setDefaultProduct(int defaultProduct) {
        this.defaultProduct = defaultProduct;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}

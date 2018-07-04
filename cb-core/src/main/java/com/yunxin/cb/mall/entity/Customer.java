/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.CustomerType;
import com.yunxin.core.web.json.deserializer.JsonTimestampDeserializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author z001075  客户
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Customer implements java.io.Serializable {

    private static final long serialVersionUID = 3814946735437297136L;

    /**
     * id
     */
    private int customerId;


    /**
     * 优惠券
     */
    private Set<Coupon> coupons = new HashSet<>();

    /**
     * 我的食谱
     */
    private Set<Recipe> recipes = new HashSet<>();

    /**
     * 我的厨房
     */
    private Set<Kitchen> kitchens = new HashSet<>();


    /**
     * 账户名
     */
    private String accountName;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 性别
     */
    private boolean sex = true;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 所在省
     */
    private String province;
    /**
     * 所在市
     */
    private String city;
    /**
     * 所在区
     */
    private String district;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号码
     * 长度设置为32，方便在用户进行QQ登录时，生成本平台新账号时，用openId(长度32)填充手机号字段，以严格保证mobile唯一。
     */
    private String mobile;
    /**
     * 固定电话
     */
    private String telephone;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮编
     */
    private String postCode;
    /**
     * 当前可用积分
     */
    private int integral;
    /**
     * 总积分
     */
    private int totalIntegral;
    /**
     * 已兑换积分
     */
    private int exchangeIntegral;
    /**
     * 会员卡号
     */
    private String cardNo;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;
    /**
     * 手机是否验证
     */
    private boolean mobileChecked;
    /**
     * 邮箱是否验证
     */
    private boolean emailChecked;
    /**
     * 备注
     */
    private String remark;
    /**
     * 等级
     */
    private Rank rank;

    /**
     * openid是唯一对应用户身份的标识，将此ID进行存储便于用户下次登录时辨识其身份，或将其与用户在网站上的原有帐号进行绑定。
     * 以便用户下次登录时可对应到其之前的身份信息，不需要重新授权。
     */
    private String qqOpenId;

    /**
     * 表示当前用户在此网站/应用的登录状态与授权信息
     */
    private String qqAccessToken;

    /**
     * 昵称
     */
    private String qqNickName;

    /**
     * 头像URL
     */
    private String qqFigureUrl;

    /**
     * 用户账号类型
     */
    private CustomerType customerType;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Column(unique = true, nullable = false, length = 64)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(length = 16, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 64, nullable = true)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(precision = 1)
    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(nullable = true, length = 32)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(nullable = true, length = 32)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = true, length = 32)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 32, nullable = false, unique = true)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(length = 14, nullable = true)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(length = 255, nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(length = 6, nullable = true)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(length = 12, nullable = true)
    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    @Column(length = 12, nullable = true)
    public int getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(int totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    @Column(length = 1024, nullable = true)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "RANK")
    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Column(length = 12)
    public int getExchangeIntegral() {
        return exchangeIntegral;
    }

    public void setExchangeIntegral(int exchangeIntegral) {
        this.exchangeIntegral = exchangeIntegral;
    }

    @Column(length = 32)
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(precision = 1)
    public boolean isMobileChecked() {
        return mobileChecked;
    }

    public void setMobileChecked(boolean mobileChecked) {
        this.mobileChecked = mobileChecked;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @Fetch(FetchMode.SELECT)
    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<Kitchen> getKitchens() {
        return kitchens;
    }

    public void setKitchens(Set<Kitchen> kitchens) {
        this.kitchens = kitchens;
    }

    @Column(unique = true, length = 32)
    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    @Column(length = 32)
    public String getQqAccessToken() {
        return qqAccessToken;
    }

    public void setQqAccessToken(String qqAccessToken) {
        this.qqAccessToken = qqAccessToken;
    }

    @Column(length = 32)
    public String getQqNickName() {
        return qqNickName;
    }

    public void setQqNickName(String qqNickName) {
        this.qqNickName = qqNickName;
    }

    @Column(length = 512)
    public String getQqFigureUrl() {
        return qqFigureUrl;
    }

    public void setQqFigureUrl(String qqFigureUrl) {
        this.qqFigureUrl = qqFigureUrl;
    }

    @Column(nullable = false, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Column(precision = 1, columnDefinition = "bit default 0")
    public boolean isEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(boolean emailChecked) {
        this.emailChecked = emailChecked;
    }
}
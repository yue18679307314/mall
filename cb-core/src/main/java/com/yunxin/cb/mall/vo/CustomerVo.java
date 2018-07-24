package com.yunxin.cb.mall.vo;

import java.io.Serializable;

/**
 * @author wangteng  客户
 */
public class CustomerVo  implements Serializable {
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 邀请码
     */
    private String recommendMobile;
    /**
     * 验证码
     */
    private String code;
    /**
     * 证件类型
     */
    private String cardType;
    /**
     * 证件号码
     */
    private String customerCardNo;
    /**
     * 证件正面照
     */
    private String cardPositiveImg;
    /**
     * 证件反面照
     */
    private String cardNegativeImg;
    /**
     * 银行照片
     */
    private String bankCardImg;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRecommendMobile() {
        return recommendMobile;
    }

    public void setRecommendMobile(String recommendMobile) {
        this.recommendMobile = recommendMobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCustomerCardNo() {
        return customerCardNo;
    }

    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public String getCardPositiveImg() {
        return cardPositiveImg;
    }

    public void setCardPositiveImg(String cardPositiveImg) {
        this.cardPositiveImg = cardPositiveImg;
    }

    public String getCardNegativeImg() {
        return cardNegativeImg;
    }

    public void setCardNegativeImg(String cardNegativeImg) {
        this.cardNegativeImg = cardNegativeImg;
    }

    public String getBankCardImg() {
        return bankCardImg;
    }

    public void setBankCardImg(String bankCardImg) {
        this.bankCardImg = bankCardImg;
    }
}
package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.ReimbursementState;
import com.yunxin.cb.mall.entity.meta.RepaymentType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Reimbursement implements Serializable {
    private static final long serialVersionUID = 7464814763140764059L;
    /**
     * id
     */
    private Integer reimbursementId;
    /**
     * 报账单号
     */
    private String reimbursementNo;

    /**
     * 用户ID
     */
    private Integer customerId;

    /**
     * 报账总金额
     */
    private BigDecimal amount;
    /**
     * 税
     */
    private BigDecimal tax;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 报账状态
     */
    private ReimbursementState orderState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品分类
     */
    private int catalogId;

    /**
     * 还款金额
     */
    private BigDecimal repaymentAmount;
    /**
     * 还款类型
     */
    private RepaymentType repaymentType;

    /**
     * 税率
     */
    private BigDecimal taxRate;
    public Integer getReimbursementId() {
        return reimbursementId;
    }


    public void setReimbursementId(Integer reimbursementId) {
        this.reimbursementId = reimbursementId;
    }


    public String getReimbursementNo() {
        return reimbursementNo;
    }


    public void setReimbursementNo(String reimbursementNo) {
        this.reimbursementNo = reimbursementNo == null ? null : reimbursementNo.trim();
    }


    public Integer getCustomerId() {
        return customerId;
    }


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public BigDecimal getTax() {
        return tax;
    }


    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }


    public BigDecimal getOrderAmount() {
        return orderAmount;
    }


    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }


    public ReimbursementState getOrderState() {
        return orderState;
    }


    public void setOrderState(ReimbursementState orderState) {
        this.orderState = orderState;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public BigDecimal getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(BigDecimal repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public RepaymentType getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(RepaymentType repaymentType) {
        this.repaymentType = repaymentType;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}

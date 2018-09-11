package com.yunxin.cb.mall.entity.meta;

public enum FinancialLogPayType {

    VX("微信"),ALIPAY("支付宝"),
    RB("报账"),LOAN("还款"),TRANSFER("返利转账");

    private String name;

    private FinancialLogPayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + "("+name+")";
    }
}

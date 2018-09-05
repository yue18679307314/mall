package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2018/7/21.
 */
public enum LoanState {
    WAIT_LOAN("申请"), APPLY_SUCCESS("已审核"), APPLY_FAILURE("已拒绝"), CANCELED("已取消"),APPLY_TRANSFERRED("已转账");

    private String name;

    private LoanState(String name) {
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
        return name;
    }
}

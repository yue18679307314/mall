package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
* @author gws
* @date 2018/7/24 19:59
* @param 
* @return 
*/
@ApiModel(value="订单确认货品信息",description="订单确认货品信息VO对象")
public class OrderConfirmProductVO implements java.io.Serializable{

    /**
     * 货品id
     */
    @NotNull(message = "货品id不能为空")
    @ApiModelProperty(value="货品id",name="productId",example="1", required = true)
    private Integer productId;
    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空")
    @Min(value = 1, message = "购买数量不能小于1")
    @ApiModelProperty(value="购买数量",name="productNum",example="1", required = true)
    private Integer productNum;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "OrderConfirmProductVO{" +
                "productId=" + productId +
                ", productNum=" + productNum +
                '}';
    }
}

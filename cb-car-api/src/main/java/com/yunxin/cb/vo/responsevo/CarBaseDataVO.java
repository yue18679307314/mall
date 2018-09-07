package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value="基础数据",description="基础数据VO对象 CarBaseDataVO")
public class CarBaseDataVO implements java.io.Serializable{
    private static final long serialVersionUID = 3473099803823395986L;
    /**
     * 基础数据名称
     */
    @ApiModelProperty(value="基础数据名称",name="baseDataName",example="国别")
    private String baseDataName;
    /**
     * 基础数据编码
     */
    @ApiModelProperty(value="基础数据编码",name="baseDataName",example="0001")
    private String baseDataCode;
    /**
     * 排序
     */
    @ApiModelProperty(value="排序",name="sortOrder",example="1")
    private Integer sortOrder;
    /**
     * 父节点数据
     */
    @ApiModelProperty(value="父节点数据",name="chilrenList",example="父节点数据")
    private CarBaseDataVO parentNode;

    public String getBaseDataName() {
        return baseDataName;
    }

    public void setBaseDataName(String baseDataName) {
        this.baseDataName = baseDataName;
    }

    public String getBaseDataCode() {
        return baseDataCode;
    }

    public void setBaseDataCode(String baseDataCode) {
        this.baseDataCode = baseDataCode;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public CarBaseDataVO getParentNode() {
        return parentNode;
    }

    public void setParentNode(CarBaseDataVO parentNode) {
        this.parentNode = parentNode;
    }
}
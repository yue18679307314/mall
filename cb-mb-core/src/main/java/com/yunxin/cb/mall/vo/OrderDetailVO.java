package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
/**
 * @title: 订单详情VO
* @author gws
* @date 2018/7/24 20:00
* @param 
* @return 
*/
@ApiModel(value="商城订单详情",description="订单详情VO对象")
public class OrderDetailVO implements java.io.Serializable{

    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id",name="orderId",example="1")
    private Integer orderId;
    /**
     * 订单编号
     */
    @ApiModelProperty(value="订单编号",name="orderCode",example="1111111")
    private String orderCode;

    /** 订单基本状态 */
    @ApiModelProperty(value="订单基本状态",name="orderState",example="1")
    private Integer orderState;

    /** 货品数量 */
    @ApiModelProperty(value="货品总数量",name="prodQuantity",example="1")
    private Integer prodQuantity;

    /** 订单付费总计 */
    @ApiModelProperty(value="订单付费总计",name="feeTotal",example="1")
    private Double feeTotal;

    /** 订单金额 */
    @ApiModelProperty(value="订单金额",name="totalPrice",example="1")
    private Double totalPrice;

    /**
     * 支付方式
     */
    @ApiModelProperty(value="支付方式",name="paymentType",example="0")
    private Integer paymentType;

    /** 创建时间 */
    @ApiModelProperty(value="提交时间",name="createTime",example="2018-07-24")
    private Date createTime;

    /** 商家地址 */
    @ApiModelProperty(value="商家地址",name="sellerAddress",example="深圳市")
    private String sellerAddress;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Integer prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public Double getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(Double feeTotal) {
        this.feeTotal = feeTotal;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public Set<OrderItemDetailVO> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(Set<OrderItemDetailVO> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }

    /**货品信息*/
    Set<OrderItemDetailVO> orderItemDetails;

    @Override
    public String toString() {
        return "OrderDetailVO{" +
                "orderId=" + orderId +
                ", orderCode='" + orderCode + '\'' +
                ", orderState=" + orderState +
                ", prodQuantity=" + prodQuantity +
                ", feeTotal=" + feeTotal +
                ", totalPrice=" + totalPrice +
                ", paymentType=" + paymentType +
                ", createTime=" + createTime +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", orderItemDetails=" + orderItemDetails +
                '}';
    }

    /**
     * 分页DO转换VO
     */
    public static PageFinder<OrderDetailVO> dOconvertVOPage (PageFinder<Order> pageFinder) throws Exception{
        PageFinder<OrderDetailVO> page = new PageFinder<OrderDetailVO> (pageFinder.getPageNo(), pageFinder.getPageSize());
        if (pageFinder != null) {
            List<OrderDetailVO> list = OrderDetailVO.dOconvertVOList(pageFinder.getData());
            page.setData(list);
        }
        page.setRowCount(pageFinder.getRowCount());//记录总数
        page.setPageCount(pageFinder.getPageCount());//总页数
        return page;
    }

    /**
     * 列表DO转换VO
     */
    public static List<OrderDetailVO> dOconvertVOList (List<Order> modelList) throws Exception{
        List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
        if (modelList != null) {
            for (Order model : modelList) {
                OrderDetailVO orderDetailVO =  OrderDetailVO.dOconvertVO(model);
                list.add(orderDetailVO);
            }
        }
        return list;
    }

    /**
     * 订单DO转换VO
     */
    public static OrderDetailVO dOconvertVO (Order model) throws Exception{
        if (model == null) {
            return null;
        }
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(orderDetailVO, model);
        //商家地址信息
        if (model.getSeller() != null) {
            orderDetailVO.setSellerAddress(model.getSeller().getSellerAddress());
        }
        if (model.getOrderItems() != null && !model.getOrderItems().isEmpty()) {
            Set<OrderItemDetailVO> orderItemDetails = new HashSet<OrderItemDetailVO>();
            for (OrderItem orderItem : model.getOrderItems()) {
                OrderItemDetailVO oderItemDetailVO = new OrderItemDetailVO();
                BeanUtils.copyProperties(oderItemDetailVO, orderItem);
                //货品信息
                if (orderItem.getProduct() != null) {
                    oderItemDetailVO.setProductName(orderItem.getProduct().getProductName());
                    oderItemDetailVO.setProductNo(orderItem.getProduct().getProductNo());
                    //商品信息
                    Commodity commodity = orderItem.getProduct().getCommodity();
                    if (commodity != null) {
                        oderItemDetailVO.setCommodityId(commodity.getCommodityId());
                        oderItemDetailVO.setCommodityName(commodity.getCommodityName());
                    }
                }
                orderItemDetails.add(oderItemDetailVO);
            }
            orderDetailVO.setOrderItemDetails(orderItemDetails);
        }
        return orderDetailVO;
    }
}
package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangteng
 *
 */
public interface IInsuranceOrderService {
    /**
     * 分页
     * @param query
     * @return
     */
    Page<InsuranceOrder> pageInsuranceOrder(PageSpecification<InsuranceOrder> query);

    /**
     * 获取详情
     * @param orderId
     * @return
     */
    public InsuranceOrder getInsuranceOrderDetailById(int orderId);

    /**
     * 根据用户ID查询保险订单列表
     * @return
     */
    List<InsuranceOrder> getInsuranceOrderByCustomer();

    /**
     * 添加保险订单
     * @param insuranceOrder
     * @return
     */
    InsuranceOrder addInsuranceOrder(InsuranceOrder insuranceOrder);
}
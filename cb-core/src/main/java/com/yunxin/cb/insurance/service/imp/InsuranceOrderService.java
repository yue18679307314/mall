package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.*;
import com.yunxin.cb.insurance.entity.*;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.mall.entity.meta.InsuranceOrderState;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InsuranceOrderService implements IInsuranceOrderService {

    @Resource
    private InsuranceOrderDao insuranceOrderDao;
    @Resource
    private InsuranceOrderBeneficiaryDao insuranceOrderBeneficiaryDao;
    @Resource
    private InsuranceOrderInformedMatterDao insuranceOrderInformedMatterDao;
    @Resource
    private InsuranceOrderInsuredDao insuranceOrderInsuredDao;
    @Resource
    private InsuranceOrderPolicyholderBankDao insuranceOrderPolicyholderBankDao;
    @Resource
    private InsuranceOrderPolicyholderDao insuranceOrderPolicyholderDao;
    @Resource
    private InsuranceInformedMatterDao insuranceInformedMatterDao;



    /**
     * 根据用户ID查询保险订单列表
     * @return
     */
    public List<InsuranceOrder> getInsuranceOrderByCustomer() {
        return insuranceOrderDao.findAll();
    }

    /**
     * 添加保险订单
     * @param insuranceOrder
     * @return
     */
    @Override
    @Transactional
    public InsuranceOrder addInsuranceOrder(InsuranceOrder insuranceOrder) {
        insuranceOrder.setOrderCode("INS2018070600105");
        insuranceOrder.setCreateTime(new Date());

        InsuranceOrderInsured insuranceOrderInsured= insuranceOrder.getInsuranceOrderInsured();
        insuranceOrderInsuredDao.save(insuranceOrderInsured);

        InsuranceOrderPolicyholder insuranceOrderPolicyholder =insuranceOrder.getInsuranceOrderPolicyholder();
        insuranceOrderPolicyholderDao.save(insuranceOrderPolicyholder);

        insuranceOrderInsuredDao.save(insuranceOrder.getInsuranceOrderInsured());
        insuranceOrderPolicyholderDao.save(insuranceOrder.getInsuranceOrderPolicyholder());
        insuranceOrderPolicyholderBankDao.save(insuranceOrder.getInsuranceOrderPolicyholderBank());
        insuranceOrderPolicyholderDao.save(insuranceOrder.getInsuranceOrderPolicyholder());
        insuranceOrder = insuranceOrderDao.save(insuranceOrder);
        insuranceOrder.setOrderCode(insuranceOrder.getOrderCode()+insuranceOrder.getOrderId());
        insuranceOrder.setContractNo(insuranceOrder.getOrderCode());

        Set<InsuranceOrderInformedMatter> insuranceOrderInformedMatters = insuranceOrder.getInsuranceOrderInformedMatters();
        for(InsuranceOrderInformedMatter insuranceOrderInformedMatter: insuranceOrderInformedMatters)
        {
            insuranceOrderInformedMatter.setInsuranceOrder(insuranceOrder);
        }
        insuranceOrderInformedMatterDao.save(insuranceOrderInformedMatters);

        Set<InsuranceOrderBeneficiary> insuranceOrderBeneficiarys = insuranceOrder.getInsuranceOrderBeneficiarys();
        for(InsuranceOrderBeneficiary insuranceOrderBeneficiary: insuranceOrderBeneficiarys)
        {
            insuranceOrderBeneficiary.setInsuranceOrder(insuranceOrder);
        }
        insuranceOrderBeneficiaryDao.save(insuranceOrderBeneficiarys);

        return insuranceOrder;

    }

    /**
     * 修改状态
     * @param orderId
     * @param orderState
     * @return
     */
    @Override
    @Transactional
    public boolean updInsuranceOrderState(int orderId,InsuranceOrderState orderState) {
        boolean flag=true;
        try {
            insuranceOrderDao.updInsuranceOrderState(orderState,orderId);
        }catch (Exception e){
            flag=false;
        }
        return flag;
    }

    /**
     * 获取事项
     * @param orderId
     * @return
     */
    @Override
    public List<Map<String, Object>> findMatter(int orderId) {
        List<InsuranceOrderInformedMatter> insuranceOrderInformedMatterList =  insuranceOrderInformedMatterDao.getInsuranceOrderInformedMatter(orderId);
        List<Map<String, Object>> listMap=new ArrayList<>();
        int groupId=0;
        for(InsuranceOrderInformedMatter list:insuranceOrderInformedMatterList
             ) {
            Map<String,Object> map=new HashMap<>();
            InsuranceInformedMatter insuranceInformedMatter= insuranceInformedMatterDao.getInsuranceInformedMatter(list.getInsuranceInformedMatter().getMatterId());

           if (null!=insuranceInformedMatter){
                if(null!=insuranceInformedMatter.getMatterGroup()){
                    if(groupId!=insuranceInformedMatter.getMatterGroup().getGroupId()){
                        Map<String,Object> maps=new HashMap<>();
                        maps.put("matter",insuranceInformedMatter.getMatterGroup().getDescription());
                        listMap.add(maps);
                        groupId=insuranceInformedMatter.getMatterGroup().getGroupId();
                    }
                }
                map.put("matter",insuranceInformedMatter.getMatterDescription());
                map.put("o_value",list.getCollectValues());
                listMap.add(map);
           }
        }
        return listMap;
    }

    /**
     * 保单分页
     * @param query
     * @return
     */
    @Override
    public Page<InsuranceOrder> pageInsuranceOrder(PageSpecification<InsuranceOrder> query) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        List<PageSpecification.FilterDescriptor> list=query.getFilter().getFilters();
        for (PageSpecification.FilterDescriptor filterDescriptor:list
             ) {
            if("createTime".equals(filterDescriptor.getField())){

                Date createTime= null;
                    SimpleDateFormat simpleDateFormats=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dates=simpleDateFormats.parse(String.valueOf(filterDescriptor.getValue()));
                    String createTimes=simpleDateFormat.format(dates);
                    filterDescriptor.setValue(createTimes);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        query.setCustomSpecification(new CustomSpecification<InsuranceOrder>(){
            @Override
            public void buildFetch(Root<InsuranceOrder> root) {
                root.fetch(InsuranceOrder_.insuranceOrderPolicyholder, JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceProduct,JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceProductPrice,JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceOrderInsured,JoinType.LEFT);
            }
        });
        return insuranceOrderDao.findAll(query,query.getPageRequest());
    }

    /**
     * 保单详情
     * @param orderId
     * @return
     */
    @Override
    public InsuranceOrder getInsuranceOrderDetailById(int orderId) {
        InsuranceOrder InsuranceOrder = insuranceOrderDao.getInsuranceOrderDetailById(orderId);
        return InsuranceOrder;
    }
}

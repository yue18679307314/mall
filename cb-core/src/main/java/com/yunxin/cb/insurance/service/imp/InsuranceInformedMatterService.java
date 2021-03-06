package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceInformedMatterDao;
import com.yunxin.cb.insurance.dao.InsuranceOrderCodeDao;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatter_;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterService;
import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InsuranceInformedMatterService implements IInsuranceInformedMatterService {

    @Resource
    private InsuranceInformedMatterDao insuranceInformedMatterDao;

    /**
     * 事项组分页
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceInformedMatter>
     * @exception
     * @date        2018/7/18 20:21
     */
    @Override
    public Page<InsuranceInformedMatter> pageInsuranceInformedMatter(PageSpecification<InsuranceInformedMatter> query){
        query.setCustomSpecification(new CustomSpecification<InsuranceInformedMatter>() {
            @Override
            public void buildFetch(Root<InsuranceInformedMatter> root) {
                root.fetch(InsuranceInformedMatter_.matterGroup, JoinType.LEFT);
            }
            @Override
            public void addConditions(Root<InsuranceInformedMatter> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<InsuranceInformedMatter> page = insuranceInformedMatterDao.findAll(query, query.getPageRequest());
        List<InsuranceInformedMatter> listmatter=page.getContent();
        for (InsuranceInformedMatter matter:listmatter){
            if(null==matter.getMatterGroup()){
                InsuranceInformedMatterGroup group =new InsuranceInformedMatterGroup();
                group.setDescription("");
                matter.setMatterGroup(group);
            }
        }
        return page;
    }

    /**
     * 分页事项组用于产品添加事项组（防止后期修改，重新写一个分页）
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceInformedMatter>
     * @exception
     * @date        2018/7/18 20:22
     */
    @Override
    public Page<InsuranceInformedMatter> pageaddMatter(PageSpecification<InsuranceInformedMatter> query){
        //添加一个只查询启用状态的条件
        PageSpecification.FilterDescriptor filterDescriptor1 = new PageSpecification.FilterDescriptor();
        filterDescriptor1.setField("enabled");
        filterDescriptor1.setLogic("and");
        filterDescriptor1.setOperator("eq");
        filterDescriptor1.setValue(1);
        List<PageSpecification.FilterDescriptor> list=query.getFilter().getFilters();
        list.add(filterDescriptor1);
        query.setCustomSpecification(new CustomSpecification<InsuranceInformedMatter>() {
            @Override
            public void buildFetch(Root<InsuranceInformedMatter> root) {
                root.fetch(InsuranceInformedMatter_.matterGroup, JoinType.LEFT);
            }
            @Override
            public void addConditions(Root<InsuranceInformedMatter> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<InsuranceInformedMatter> page = insuranceInformedMatterDao.findAll(query, query.getPageRequest());
        List<InsuranceInformedMatter> listmatter=page.getContent();
        for (InsuranceInformedMatter matter:listmatter){
            if(null==matter.getMatterGroup()){
                InsuranceInformedMatterGroup group =new InsuranceInformedMatterGroup();
                group.setDescription("");
                matter.setMatterGroup(group);
            }
        }
        return page;
    }

    /**
     * 根据id获取事项
     * @author      likang
     * @param metterId
     * @return      com.yunxin.cb.insurance.entity.InsuranceInformedMatter
     * @exception
     * @date        2018/7/18 20:23
     */
    @Override
    public InsuranceInformedMatter getInsuranceInformedMatter(int metterId){
        return insuranceInformedMatterDao.getInsuranceInformedMatter(metterId);
    }

    /**
     * 添加事项
     * @author      likang
     * @param insuranceInformedMatter
     * @return      com.yunxin.cb.insurance.entity.InsuranceInformedMatter
     * @exception
     * @date        2018/7/18 20:24
     */
    @Override
    public InsuranceInformedMatter addInsuranceInformedMatter(InsuranceInformedMatter insuranceInformedMatter){
        insuranceInformedMatterDao.save(insuranceInformedMatter);
        return insuranceInformedMatter;
    }

    /**
     * 根据id删除事项组
     * @author      likang
     * @param metterId
     * @return      void
     * @exception
     * @date        2018/7/18 20:24
     */
    @Override
    public void removeById(int metterId){
        insuranceInformedMatterDao.delete(metterId);
    }

    /**
     * 更新事项
     * @author      likang
     * @param insuranceInformedMatter
     * @return      com.yunxin.cb.insurance.entity.InsuranceInformedMatter
     * @exception
     * @date        2018/7/18 20:24
     */
    @Override
    @Transactional
    public InsuranceInformedMatter update(InsuranceInformedMatter insuranceInformedMatter){
        InsuranceInformedMatter oldMatter = insuranceInformedMatterDao.findOne(insuranceInformedMatter.getMatterId());
        oldMatter.setMatterType(insuranceInformedMatter.getMatterType());
        oldMatter.setMatterDescription(insuranceInformedMatter.getMatterDescription());
        oldMatter.setSerNo(insuranceInformedMatter.getSerNo());
        oldMatter.setMatterGroup(insuranceInformedMatter.getMatterGroup());
        oldMatter.setEnabled(insuranceInformedMatter.getEnabled());
        oldMatter.setInsuredPeople(insuranceInformedMatter.getInsuredPeople());
        oldMatter.setInsurePeople(insuranceInformedMatter.getInsurePeople());
        return oldMatter;
    }

    /**
     * 获取所有事项
     * @author      likang
     * @param
     * @return      java.util.List<com.yunxin.cb.insurance.entity.InsuranceInformedMatter>
     * @exception
     * @date        2018/7/18 20:25
     */
    @Override
    public List<InsuranceInformedMatter> getInsuranceInformedMatterList(){
        return insuranceInformedMatterDao.findAll();
    }



    /**
     * 修改事项的启用状态
     * @author      likang
     * @param matterId
    * @param enabled
     * @return      void
     * @exception
     * @date        2018/7/20 10:36
     */
    @Override
    @Transactional
    public void enableInformedMatterById(int matterId,int enabled){
        insuranceInformedMatterDao.enableInformedMatterById(matterId,enabled);
    }

}

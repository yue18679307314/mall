package com.yunxin.cb.insurance.dao;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceInformedMatterDao extends JpaRepository<InsuranceInformedMatter, Integer>, JpaSpecificationExecutor<InsuranceInformedMatter> {

    @Query("select c from InsuranceInformedMatter c left join fetch c.matterGroup  where c.matterId=?1")
    public InsuranceInformedMatter getInsuranceInformedMatter(int matterId);


    @Modifying
    @Query("update InsuranceInformedMatter iim set iim.enabled =?2 where iim.matterId=?1")
    public void enableInformedMatterById(int matterId,int enabled);


}

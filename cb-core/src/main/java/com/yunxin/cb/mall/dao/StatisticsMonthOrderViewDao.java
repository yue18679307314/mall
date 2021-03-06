/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsMonthOrderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chenxing
 */
public interface StatisticsMonthOrderViewDao extends JpaRepository<StatisticsMonthOrderView, Integer>, JpaSpecificationExecutor<StatisticsMonthOrderView> {


    /**
     * 每天的总订单量
     *
     * @param year
     * @return
     */
    @Query("select orderView from StatisticsMonthOrderView orderView where orderView.year=?1 order by orderView.month asc")
    public List<StatisticsMonthOrderView> findMonthOrder(int year);


}


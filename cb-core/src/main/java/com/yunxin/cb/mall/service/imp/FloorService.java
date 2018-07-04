package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FloorCategoryDao;
import com.yunxin.cb.mall.dao.FloorCommodityDao;
import com.yunxin.cb.mall.dao.HomeFloorDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.IFloorService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional
public class FloorService implements IFloorService {

    @Resource
    private HomeFloorDao homeFloorDao;

    @Resource
    private FloorCommodityDao floorCommodityDao;

    @Resource
    private FloorCategoryDao floorCategoryDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HomeFloor getHomeFloorById(int floorId) {
        return homeFloorDao.findOne(floorId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HomeFloor getHomeFloorFetchAllById(int floorId) {
        return homeFloorDao.fetchAllById(floorId);
    }

    @Override
    public HomeFloor addHomeFloor(HomeFloor homeFloor) {
        int[] categoryId = homeFloor.getCategoryId();
        int[] categoryOrder = homeFloor.getCategoryOrder();
        int[] commodityId = homeFloor.getCommodityId();
        int[] commodityOrder = homeFloor.getCommodityOrder();
        homeFloor = homeFloorDao.save(homeFloor);
        batchAddFloorCategoryAndCommodity(homeFloor, categoryId, categoryOrder, commodityId, commodityOrder);
        return homeFloor;
    }

    private void batchAddFloorCategoryAndCommodity(HomeFloor homeFloor, int[] categoryId, int[] categoryOrder, int[] commodityId, int[] commodityOrder) {
        if (categoryId != null) {
            for (int i = 0; i < categoryId.length; i++) {
                FloorCategoryId id = new FloorCategoryId(homeFloor.getFloorId(), categoryId[i]);
                FloorCategory floorCategory = new FloorCategory();
                floorCategory.setId(id);
                floorCategory.setCategory(new Category(categoryId[i]));
                floorCategory.setHomeFloor(homeFloor);
                floorCategory.setSortOrder(categoryOrder[i]);
                floorCategoryDao.save(floorCategory);
            }
            homeFloor.setCategoryAmount(categoryId.length);
        }
        if (commodityId != null) {
            for (int i = 0; i < commodityId.length; i++) {
                FloorCommodityId id = new FloorCommodityId(homeFloor.getFloorId(), commodityId[i]);
                FloorCommodity floorCommodity = new FloorCommodity();
                floorCommodity.setId(id);
                floorCommodity.setCommodity(new Commodity(commodityId[i]));
                floorCommodity.setHomeFloor(homeFloor);
                floorCommodity.setSortOrder(commodityOrder[i]);
                floorCommodityDao.save(floorCommodity);
            }
            homeFloor.setCommodityAmount(commodityId.length);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HomeFloor> pageServiceRulesForClean(
            PageSpecification<HomeFloor> serviceRuleQuery) {
        serviceRuleQuery.setCustomSpecification(new CustomSpecification<HomeFloor>() {
            @Override
            public void addConditions(Root<HomeFloor> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                builder.desc(root.get(HomeFloor_.enabled));
                builder.asc(root.get(HomeFloor_.sortOrder));
            }
        });
        Page<HomeFloor> pages = homeFloorDao.findAll(serviceRuleQuery, serviceRuleQuery.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HomeFloor> getEnabledHomeFloors() {
        return homeFloorDao.findEnabledHomeFloors();
    }

    @Override
    public HomeFloor updateHomeFloor(HomeFloor homeFloor) throws EntityExistException {
        if (!homeFloorDao.isUnique(homeFloor, HomeFloor_.floorName)) {
            throw new EntityExistException("楼层名称已存在！");
        }
        HomeFloor dbhomeFloor = homeFloorDao.findOne(homeFloor.getFloorId());

        AttributeReplication.copying(homeFloor, dbhomeFloor, HomeFloor_.categoryAmount, HomeFloor_.commodityAmount, HomeFloor_.enabled, HomeFloor_.floorName,
                HomeFloor_.imagePath, HomeFloor_.iconPath, HomeFloor_.remark, HomeFloor_.sortOrder, HomeFloor_.floorLayout);
        int[] categoryId = homeFloor.getCategoryId();
        int[] categoryOrder = homeFloor.getCategoryOrder();
        int[] commodityId = homeFloor.getCommodityId();
        int[] commodityOrder = homeFloor.getCommodityOrder();
        floorCategoryDao.emptyByHomeFloor(dbhomeFloor);
        floorCommodityDao.emptyByHomeFloor(dbhomeFloor);
        batchAddFloorCategoryAndCommodity(dbhomeFloor, categoryId, categoryOrder, commodityId, commodityOrder);
        return dbhomeFloor;
    }

    @Override
    public void removeHomeFloorById(int floorId) {
        homeFloorDao.delete(floorId);
    }

    @Override
    public void enableHomeFloorById(int floorId, boolean enabled) {
        homeFloorDao.enableHomeFloorById(enabled, floorId);
    }


}
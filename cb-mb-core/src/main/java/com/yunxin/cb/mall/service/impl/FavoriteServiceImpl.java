package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.mapper.CommodityMapper;
import com.yunxin.cb.mall.mapper.FavoriteMapper;
import com.yunxin.cb.mall.mapper.ProductMapper;
import com.yunxin.cb.mall.service.FavoriteService;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @title: Favorite 服务实现类
 * @auther: eleven
 * @date: 2018/7/18 19:58
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

	private static final Log log = LogFactory.getLog(FavoriteServiceImpl.class);

	@Resource
	private FavoriteMapper favoriteMapper;

	@Resource
	private ProductMapper productMapper;

	@Resource
	private CommodityMapper commodityMapper;

	@Override
	public Favorite addFavorite(Favorite favorite) {
		Product product=productMapper.selectByPrimaryKey(favorite.getProductId());
		if(null==product){
			return null;//货品不存在，返回失败
		}else{
			Favorite getFavorite = favoriteMapper.findByCustomerAndCommodity(favorite);
			if (getFavorite == null) {//不存在，则新增
				favorite.setCreateTime(new Date());
				favorite.setCommodityId(product.getCommodityId());
				favoriteMapper.insert(favorite);
			}else{
				favorite=getFavorite;//已存在，则返回成功
			}
			return favorite;
		}
	}

	@Override
	public int removeFavoriteBatch(List<Integer> favoriteIds,Integer customerId){
		return favoriteMapper.removeFavoriteBatch(favoriteIds,customerId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PageFinder<Favorite> pageCustomerFavorites(Query q) {
		List<Favorite> list = null;
		long rowCount = 0L;
		try {
			//调用dao查询满足条件的分页数据
			list = favoriteMapper.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = favoriteMapper.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<Favorite>(0) : list;
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<Favorite> page = new PageFinder<>(q.getPageNo(), q.getPageSize(), rowCount);
		page.setData(list);
		return page;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Favorite findByCustomerAndCommodity(Favorite favorite) {
		return favoriteMapper.findByCustomerAndCommodity(favorite);
	}

}

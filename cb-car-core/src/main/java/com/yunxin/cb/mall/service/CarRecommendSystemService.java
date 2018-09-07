package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.CarRecommendSystem;

/**
 * 推荐车系中间表 服务接口类
 */
public interface CarRecommendSystemService extends BaseService {

	/**
	 * 根据查询条件，查询并返回CarRecommendSystem的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<CarRecommendSystem> getCarRecommendSystemList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回CarRecommendSystem的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<CarRecommendSystem> getCarRecommendSystemPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个CarRecommendSystem对象
	 * @param id 主键id
	 * @return
	 */
	public CarRecommendSystem getCarRecommendSystem(Integer id);

	/**
	 * 根据主键数组，查询并返回一组CarRecommendSystem对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<CarRecommendSystem> getCarRecommendSystemByIds(Integer[] ids);
	
	/**
	 * 根据主键，删除特定的CarRecommendSystem记录
	 * @param id 主键id
	 * @return
	 */
	public int delCarRecommendSystem(Integer id);
	
	/**
	 * 新增一条CarRecommendSystem记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carRecommendSystem 新增的CarRecommendSystem数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addCarRecommendSystem(CarRecommendSystem carRecommendSystem);
	
	/**
	 * 根据主键，更新一条CarRecommendSystem记录
	 * @param carRecommendSystem 更新的CarRecommendSystem数据，且其主键不能为空
	 * @return
	 */
	public int updateCarRecommendSystem(CarRecommendSystem carRecommendSystem);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK();

		/**
	 * 为CarRecommendSystem对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarRecommendSystem obj);
		
}
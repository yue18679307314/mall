package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.CarHomeBrand;

/**
 * 汽车之家的品牌 服务接口类
 */
public interface CarHomeBrandService extends BaseService {

	/**
	 * 根据查询条件，查询并返回CarHomeBrand的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<CarHomeBrand> getCarHomeBrandList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回CarHomeBrand的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<CarHomeBrand> getCarHomeBrandPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个CarHomeBrand对象
	 * @param id 主键id
	 * @return
	 */
	public CarHomeBrand getCarHomeBrand(Integer id);

	/**
	 * 根据主键数组，查询并返回一组CarHomeBrand对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<CarHomeBrand> getCarHomeBrandByIds(Integer[] ids);
	
	/**
	 * 根据主键，删除特定的CarHomeBrand记录
	 * @param id 主键id
	 * @return
	 */
	public int delCarHomeBrand(Integer id);
	
	/**
	 * 新增一条CarHomeBrand记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carHomeBrand 新增的CarHomeBrand数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addCarHomeBrand(CarHomeBrand carHomeBrand);
	
	/**
	 * 根据主键，更新一条CarHomeBrand记录
	 * @param carHomeBrand 更新的CarHomeBrand数据，且其主键不能为空
	 * @return
	 */
	public int updateCarHomeBrand(CarHomeBrand carHomeBrand);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK();

		/**
	 * 为CarHomeBrand对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarHomeBrand obj);
		
}

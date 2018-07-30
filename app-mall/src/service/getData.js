import {post,get,patch,put,del} from '../config/http'

//首页获取数据
export const getIndex = function () {
  return get('/v1/mall/index/getIndex.do');
};

//获取收藏夹
export const getCustomerFavorite = function (query) {
  return post('/v1/mall/favorite/getCustomerFavorite.do',{},query);
};

//商品是否收藏
export const getCommodityFavoriteById = function (commodityId) {
  return get('/v1/mall/favorite/findByCustomerAndCommodity/' + commodityId);
};

//商品添加收藏
export const addCommodityFavorite = function (favoriteVo) {
  return post('/v1/mall/favorite/addFavorite.do', favoriteVo);
};

//将商品移出收藏夹
export const delFavoriteByFavoriteId = function (favoriteId) {
  return del('/v1/mall/favorite/delFavorite/' + favoriteId);
};

//删除勾选的收藏商品
export const delFavoriteListByFavoriteIds = function (favoriteIds) {
  return del('/v1/mall/favorite/delFavorite/' + favoriteIds);
};

//分类搜索
export const categorySearch = function (searchVo, page, size) {
  return post('/mall/search/categorySearch/'+page + '/' + size,searchVo)
}

//获取用户收货地址列表
export const getDeliveryAddress = function () {
  return get('/v1/mall/deliveryAddress/list.do');
};

//新增用户收货地址
export const saveDeliveryAddress = function (addressVo) {
  return post('/v1/mall/deliveryAddress.do', addressVo);
};

//根据id获取用户收货地址详情
export const getDeliveryAddressByAdderssId = function (addressId) {
  return get('/v1/mall/deliveryAddress/' + addressId);
};

//编辑保存用户收货地址
export const updateDeliveryAddress = function (addressVo) {
  return put('/v1/mall/deliveryAddress/' + addressVo.addressId, addressVo);
};

//根据id删除用户收货地址
export const deleteDeliveryAddressByAdderssId = function (addressId) {
  return del('/v1/mall/deliveryAddress/' + addressId);
};


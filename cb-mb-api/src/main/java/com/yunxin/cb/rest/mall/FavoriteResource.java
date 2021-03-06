package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.service.FavoriteService;
import com.yunxin.cb.mall.vo.FavoriteVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @title:  商城收藏夹接口
 * @auther: eleven
 * @date: 2018/7/17 18:28
 */
@Api(description = "商城收藏夹接口")
@RestController
@RequestMapping(value = "/{version}/mall/favorite")
public class FavoriteResource extends BaseResource {

    @Resource
    private FavoriteService favoriteService;

    /**
     * @title: 获取用户收藏夹
     * @param: [customerId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:28
     */
    @ApiOperation(value = "获取用户收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "getCustomerFavorite")
    @ApiVersion(1)
    public ResponseResult<PageFinder<FavoriteVo>> getCustomerFavorite(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize){
        Query q = new Query(pageNo, pageSize);
        Favorite favorite=new Favorite();
        favorite.setCustomerId(getCustomerId());
        q.setData(favorite);
        PageFinder<Favorite> pageFinder=favoriteService.pageCustomerFavorites(q);
        PageFinder<FavoriteVo> page=FavoriteVo.dOconvertVOPage(pageFinder);
        return new ResponseResult(page);
    }

    /**
     * @title: 商品添加收藏夹
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品添加收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "货品ID", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "salePrice", value = "销售价", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "addFavorite")
    @ApiVersion(1)
    public ResponseResult<FavoriteVo> addFavorite(@Validated @RequestBody FavoriteVo favoriteVo) {
        ResponseResult result=new ResponseResult(Result.FAILURE);
        try {
            logger.info("input Parameter favoriteVo:" + favoriteVo.toString());
            Favorite favorite = new Favorite();
            BeanUtils.copyProperties(favorite, favoriteVo);
            favorite.setCustomerId(getCustomerId());
            favorite = favoriteService.addFavorite(favorite);
            if (favorite != null) {
                BeanUtils.copyProperties(favoriteVo, favorite);
                result.setData(favoriteVo);
                result.setResult(Result.SUCCESS);
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException is "+e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException is "+e);
        }
        return result;
    }

    /**
     * @title: 商品移出收藏夹(批量)
     * @param: [favoriteIds]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:27
     */
    @ApiOperation(value = "商品移出收藏夹(批量)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "favoriteIds", value = "收藏夹id集合", required = true, paramType = "post", dataType = "list<int>")})
    @PostMapping(value = "delFavorites")
    @ApiVersion(1)
    public ResponseResult delFavorites(@RequestBody List<Integer> favoriteIds){
        ResponseResult result=new ResponseResult(Result.FAILURE);
        try {
            if(LogicUtils.isNullOrEmpty(favoriteIds)){
                return new ResponseResult(Result.FAILURE,"参数为空");//失败
            }
            favoriteService.removeFavoriteBatch(favoriteIds, getCustomerId());
            result.setResult(Result.SUCCESS);
        }catch (Exception e){
            logger.error("Exception is "+e);
        }
        return result;
    }
}
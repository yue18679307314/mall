package com.yunxin.cb.rest.mall;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Attribute;
import com.yunxin.cb.mall.entity.AttributeGroup;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.Profile;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.ProfileState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.mall.service.ProfileService;
import com.yunxin.cb.mall.vo.AttributeGroupVO;
import com.yunxin.cb.mall.vo.CommodityVo;
import com.yunxin.cb.mall.vo.SellerVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @title: 商城商品接口
 * @auther: eleven
 * @date: 2018/7/17 18:29
 */
@Api(description = "商城商品接口")
@RestController
@RequestMapping(value = "/{version}/mall/commodity")
public class CommodityResource extends BaseResource {

    @Resource
    private CommodityService commodityService;

    @Resource
    private ProfileService profileService;

    /**
     * @title: 通过货品ID查询商品详情
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/17 18:29
     */
    @ApiOperation(value = "通过货品ID查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "货品ID", required = true, paramType = "path", dataType = "Integer")})
    @GetMapping(value = "getCommdityDetail/{productId}")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<CommodityVo> getCommdityDetail(@PathVariable int productId){
        ResponseResult result=new ResponseResult(Result.FAILURE);
        try {
            int customerId=getCustomerId();
            CommodityVo commodityVo = commodityService.getCommdityDetail(productId,customerId);
            result.setData(commodityVo);
            if(LogicUtils.isNull(commodityVo)){
                result.setMessage("商品或货品未上架");
            }else{
                result.setResult(Result.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("Exception is "+e);
        }
        return result;
    }

    /**
     * @title: 通过商品ID查询所有货品属性
     * @param: [commodityId]
     * @return: com.yunxin.cb.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/7/20 17:59
     */
    @ApiOperation(value = "通过商品ID查询所有货品属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "Integer")})
    @GetMapping(value = "getProductsByCommodityId/{commodityId}")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<AttributeGroupVO>> getProductsByCommodityId(@PathVariable int commodityId) {
        ResponseResult result=new ResponseResult(Result.FAILURE);
        try {
            //查询商品下面所有货品
            List<Product> products = commodityService.getProductsByCommodityId(commodityId,ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
            if(LogicUtils.isNullOrEmpty(products)){
                result.setMessage("该商品没有货品");
                return result;
            }
            //商品属性集合
            Set<Attribute> attributes = new HashSet<>();
            products.stream().forEach(p -> {
                //遍历所有货品，取出所有货品属性
                p.getProductAttributes().stream().forEach(pa -> attributes.add(pa.getAttribute()));
            });
            //根据属性里面的属性组对象进行对象分组
            Map<AttributeGroup, List<Attribute>> attributeGroups = attributes.stream().collect(
                    Collectors.groupingBy(Attribute::getAttributeGroup));
            //属性组集合
            List<AttributeGroup> groups=new ArrayList<>();
            //迭代对象分组，将属性与属性组关联
            for (AttributeGroup attributeGroup : attributeGroups.keySet()) {
                attributeGroup.setAttributes(new TreeSet<>());
                //此处需实现属性实体中Comparable的compareTo排序方法
                attributeGroup.getAttributes().addAll(attributeGroups.get(attributeGroup));
                groups.add(attributeGroup);
            }
            //转换成VO返回(需实现VO中Comparable的compareTo排序方法)
            result.setData(AttributeGroupVO.convertVO(groups));
            result.setResult(Result.SUCCESS);
        } catch (Exception e) {
            logger.error("Exception is "+e);
        }
        return result;
    }

    @ApiOperation(value = "热门城市")
    @GetMapping(value = "hotCity")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<String> hotCity() {
        ResponseResult result=new ResponseResult(Result.FAILURE);
        try {
            JSONArray jsonArray=new JSONArray();
            Profile profile = profileService.getProfileByName(ProfileState.HOT_CITY.name());
            if (profile != null && StringUtils.isNotBlank(profile.getFileValue())) {
                String [] hotSearchArr = profile.getFileValue().split(",");
                for (String city:hotSearchArr){
                    String [] cityArr = city.split("&");
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("city",cityArr[0]);
                    jsonObject.put("code",cityArr[1]);
                    jsonArray.add(jsonObject);
                }
            }
            result.setResult(Result.SUCCESS);
            result.setData(jsonArray.toJSONString());
        } catch (Exception e) {
            logger.info("hotCity failed", e);
        }
        return result;
    }

}

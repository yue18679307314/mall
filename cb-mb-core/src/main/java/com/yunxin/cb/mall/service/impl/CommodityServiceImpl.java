package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.CommoditySpec;
import com.yunxin.cb.mall.entity.PriceSection;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.mapper.*;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.mall.service.HistoryRecordService;
import com.yunxin.cb.mall.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @title: 商品接口实现类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    private static final Log log = LogFactory.getLog(CommodityServiceImpl.class);

    @Resource
    private CommodityMapper commodityMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private FavoriteMapper favoriteMapper;

    @Resource
    private AttachmentMapper attachmentMapper;

    @Resource
    private HistoryRecordService historyRecordService;

    @Resource
    private SellerMapper sellerMap;

    @Override
    public Commodity selectByPrimaryKey(int commodityId) {
        return commodityMapper.selectByPrimaryKey(commodityId);
    }

    @Override
    public CommodityVo getCommdityDetail(int productId,int customerId) throws Exception {
        //查询货品详情
        Product product = productMapper.selectProductById(productId,ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        if(product==null){
            return null;
        }
        //查询货品的商品详情
        Commodity commodity = commodityMapper.selectCommodityDetailById(product.getCommodityId(),ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        if(commodity==null){
            return null;
        }
        //查询商品的所有货品
        List<Product> products = productMapper.selectAllByCommodityId(product.getCommodityId(),ProductState.AUDITED.ordinal(),PublishState.UP_SHELVES.ordinal());//审核通过并上架状态
        PriceSection priceSection = commodity.getPriceSection();//商品价格段
        Seller seller = commodity.getSeller();//商家
        Map specs = new HashMap();//商品规格Map
        String showLevel="";
        for (CommoditySpec spec : commodity.getCommoditySpecs()) {
            if(spec.getSpec().getSpecName().equals("级别")){
                showLevel = spec.getValue();//级别（页面单独显示）
            }
            specs.put(spec.getSpec().getSpecName(), spec.getValue());//将规格名称和规格内容封装
        }
        Map paymentType=new HashMap();//支付方式
        for (PaymentType pay : PaymentType.values()){
            paymentType.put(pay,pay.getName());
        }
        Favorite favorite=null;
        if(customerId>0){//用户存在则查询商品收藏夹和加入浏览历史
            favorite=new Favorite();
            favorite.setCustomerId(customerId);
            favorite.setProductId(productId);
            favorite.setCommodityId(product.getCommodityId());
            favorite=favoriteMapper.findByCustomerAndCommodity(favorite);

            HistoryRecord hr=new HistoryRecord();
            hr.setCommodityId(product.getCommodityId());
            hr.setProductId(productId);
            hr.setCustomerId(customerId);
            hr.setSalePrice(product.getSalePrice());
            historyRecordService.addHistoryRecord(hr);
        }
        List<Attachment> attachments=attachmentMapper.selectByObjectTypeAndId(ObjectType.COMMODITY.name(),commodity.getCommodityId());//商品图片组
        Set imageSet=new HashSet<>();
        for (Attachment attachment:attachments){
            imageSet.add(attachment.getFilePath());
        }
        //重组数据后清空
        commodity.setBrand(null);
        commodity.setSeller(null);
        commodity.setPriceSection(null);
        commodity.setCommoditySpecs(null);
        CommodityVo commodityVo=new CommodityVo();
        ProductVo productVo=null;
        List<ProductVo> productVos=null;
        PriceSectionVo priceSectionVo=null;
        SellerVo sellerVo=null;
        FavoriteVo favoriteVo=null;
        BeanUtils.copyProperties(commodityVo,commodity);
        if(!StringUtils.isEmpty(product)){
            productVo=new ProductVo();
            BeanUtils.copyProperties(productVo,product);
        }
        if(!StringUtils.isEmpty(products)&&products.size()>0){
            productVos=new ArrayList<ProductVo>();
            for(Product pro:products){
                pro.setProductAttributes(null);
                ProductVo proVo=new ProductVo();
                BeanUtils.copyProperties(proVo,pro);
                productVos.add(proVo);
            }
            commodityVo.setProductVos(productVos);
        }
        if(!StringUtils.isEmpty(priceSection)){
            priceSectionVo=new PriceSectionVo();
            BeanUtils.copyProperties(priceSectionVo,priceSection);
        }
        if(!StringUtils.isEmpty(seller)){
            sellerVo=new SellerVo();
            BeanUtils.copyProperties(sellerVo,seller);
        }
        if(!StringUtils.isEmpty(favorite)){
            favoriteVo=new FavoriteVo();
            BeanUtils.copyProperties(favoriteVo,favorite);
        }
        commodityVo.setProductVo(productVo);
        commodityVo.setPriceSectionVo(priceSectionVo);
        commodityVo.setSellerVo(sellerVo);
        commodityVo.setFavoriteVo(favoriteVo);
        commodityVo.setShowLevel(showLevel);
        commodityVo.setSpecs(specs);
        commodityVo.setPaymentType(paymentType);
        commodityVo.setImageSet(imageSet);
        return commodityVo;
    }

    @Override
    public List<Product> getProductsByCommodityId(Integer commodityId,Integer state,Integer publish) {
        List<Product> products = productMapper.selectAllByCommodityId(commodityId,state,publish);
        return products;
    }

    @Override
    public List<Commodity> selectByBrandId(Integer brandId) {
        return commodityMapper.selectByBrandId(brandId);
    }

    @Override
    public List<SellerVo> getAllSellerAddress() {
        List<SellerVo> sellerVos=new ArrayList<>();
        List<Seller> sellers=sellerMap.getAllSellerAddress();
        sellers.stream().forEach(seller -> {
            try {
                SellerVo sellerVo=new SellerVo();
                BeanUtils.copyProperties(sellerVo,seller);
                sellerVos.add(sellerVo);
            } catch (IllegalAccessException e) {
                log.error("error "+e);
            } catch (InvocationTargetException e) {
                log.error("error "+e);
            }
        });
        return sellerVos;
    }
}

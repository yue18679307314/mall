package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.service.IBrandService;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.mall.service.IBrandService;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author keymean
 */
@Controller
@RequestMapping(value = "/commodity")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class BrandController {

    @Resource
    private IBrandService brandService;

    @Resource
    private ICategoryService categoryService;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "brands")
    public String brands(ModelMap modelMap) {
        return "commodity/brands";
    }

    /**
     * brand列表分页
     *
     * @param brandQuery
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<Brand> pageBrands(@RequestBody PageSpecification<Brand> brandQuery) {
        return brandService.pageBrands(brandQuery);
    }

    /**
     * 跳转到brand新增页面
     *
     * @param brand
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toAddBrand(@ModelAttribute("brand") Brand brand, ModelMap modelMap) {
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "commodity/addBrand";
    }

    /**
     * 保存新增数据，上传图片
     *
     * @param brand
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addBrand(@ModelAttribute("brand") Brand brand,BindingResult result, ModelMap modelMap,Locale locale) {

        try {
            brandService.addBrand(brand);
        } catch (EntityExistException e) {
            result.addError(new FieldError("brand", "brandName", brand.getBrandEnName(), true, null, null,
                    messageSource.getMessage("brand_brandName_repeat", null, locale)));
            return toAddBrand(brand, modelMap);
        }
        return "redirect:../common/success.do?reurl=commodity/brands.do";
    }

    /**
     * 跳转到品牌修改页面
     *
     * @param brandId
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toEditBrand(@RequestParam("brandId") int brandId,ModelMap modelMap) {
        Brand brand = brandService.getBrandById(brandId);
        modelMap.addAttribute("brand", brand);
        TreeViewItem categoryTree = categoryService.getCategoryTree();
        modelMap.addAttribute("categoryTree", Arrays.asList(categoryTree));
        return "commodity/editBrand";
    }

    /**
     * 保存品牌修改后的数据，并跳转到品牌列表页面
     *
     * @param brand
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String editBrand(@ModelAttribute("brand") Brand brand,BindingResult result,HttpServletRequest request,ModelMap modelMap,Locale locale) {

        try {
            brandService.updateBrand(brand);
        } catch (EntityExistException e) {
            result.addError(new FieldError("brand", "brandName", brand.getBrandEnName(), true, null, null,
                    messageSource.getMessage("brand_brandName_repeat", null, locale)));
            return toEditBrand(brand.getBrandId(), modelMap);
        }
        return "redirect:../common/success.do?reurl=commodity/brands.do";
    }

    /**
     * 根据品牌id删除品牌
     *
     * @param brandId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public boolean removeBrandById(@RequestParam("brandId") int brandId) {
        try{
            brandService.removeBrandById(brandId);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * 品牌详情
     *
     * @param brandId
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String brandDetail(@RequestParam("brandId") int brandId, ModelMap modelMap) {
        modelMap.addAttribute("brand", brandService.getBrandById(brandId));
        return "commodity/brandDetail";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String enableBrandById(@RequestParam("brandId") int brandId, @RequestParam("enabled") boolean enabled) {
        try {
            brandService.enableBrandById(brandId, enabled);
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

}
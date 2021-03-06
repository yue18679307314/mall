package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.entity.CatalogAttribute;
import com.yunxin.cb.mall.entity.CatalogAttributeGroup;
import com.yunxin.cb.mall.service.IAttributeService;
import com.yunxin.cb.mall.service.ICatalogService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author z001075
 */
@Controller
@RequestMapping(value = "/commodity")
public class CatalogAttributeGroupController implements ServletContextAware {

    private static Logger logger = LoggerFactory.getLogger(CatalogAttributeGroupController.class);

    @Value("${application.uploadPath}")
    private String uploadPath;
    @Resource
    private IAttributeService attributeService;

    @Resource
    private ICatalogService catalogService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @RequestMapping(value = "catalogAttributeGroups", method = RequestMethod.GET)
    public String catalogAttributeGroups(ModelMap modelMap) {
        TreeViewItem catalog = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogItem", catalog);
        return "commodity/catalogAttributeGroups";
    }

    @RequestMapping(value = "pageCatalogAttributeGroups",method = RequestMethod.POST)
    @ResponseBody
    public Page<CatalogAttributeGroup> pageCatalogAttributeGroups(@RequestBody PageSpecification<CatalogAttributeGroup> query) {
        Page<CatalogAttributeGroup> page = attributeService.pageCatalogAttributeGroups(query);
        return page;
    }

    @RequestMapping(value = "getAttributesByGroupId", method = RequestMethod.POST)
    @ResponseBody
    public List<CatalogAttribute> getAttributesByGroupId(@RequestBody PageSpecification pageRequest) {
        String groupId = (String) pageRequest.getData().get("groupId");
        return attributeService.findAttributeByGroupId(Integer.parseInt(groupId));
    }

    @RequestMapping(value = "toAddCatalogAttributeGroup",method = RequestMethod.GET)
    public String toAddCatalogAttributeGroup(@ModelAttribute("attributeGroup") CatalogAttributeGroup attributeGroup, BindingResult result, ModelMap modelMap) {
        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        return "commodity/addCatalogAttributeGroup";
    }

    @RequestMapping(value = "addCatalogAttributeGroup",method = RequestMethod.POST)
    public String addCatalogAttributeGroup(@Valid @ModelAttribute("attributeGroup") CatalogAttributeGroup attributeGroup,
                                           BindingResult result, ModelMap modelMap,RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return toAddCatalogAttributeGroup(attributeGroup, result, modelMap);
        }
        try {
            attributeService.addCatalogAttributeGroup(attributeGroup);
        } catch (EntityExistException e) {
            logger.error("添加商品属性组失败",e);
            redirectAttributes.addFlashAttribute("msgTitle","商品属性组名称已存在，添加失败！");
            redirectAttributes.addFlashAttribute("msgContent",e.getMessage());
            return "redirect:../common/failure.do?reurl=commodity/catalogAttributeGroups.do";

        }
        return "redirect:../common/success.do?reurl=commodity/catalogAttributeGroups.do";
    }


    @RequestMapping(value = "toEditCatalogAttributeGroup",method = RequestMethod.GET)
    public String toEditCatalogAttributeGroup(@RequestParam("groupId") int groupId, ModelMap modelMap) {
        CatalogAttributeGroup attributeGroup = attributeService.getCatalogAttributeGroupById(groupId);
        modelMap.addAttribute("attributeGroup", attributeGroup);
        return toEditAttributeGroup(attributeGroup, modelMap);
    }

    private String toEditAttributeGroup(CatalogAttributeGroup attributeGroup, ModelMap modelMap) {
        TreeViewItem catalogTree = catalogService.getCatalogTree();
        modelMap.addAttribute("catalogTree", Arrays.asList(catalogTree));
        return "commodity/editCatalogAttributeGroup";
    }

    @RequestMapping(value = "editCatalogAttributeGroup",method = RequestMethod.POST)
    public String editCatalogAttributeGroup(@Valid @ModelAttribute("attributeGroup") CatalogAttributeGroup attributeGroup, BindingResult result,
                                            ModelMap modelMap) throws IOException {
        if (result.hasErrors()) {
            return toEditAttributeGroup(attributeGroup, modelMap);
        }
        try {
            attributeService.updateCatalogAttributeGroup(attributeGroup);
        } catch (EntityExistException e) {
            e.printStackTrace();
            return toEditAttributeGroup(attributeGroup, modelMap);
        }
        return "redirect:../common/success.do?reurl=commodity/catalogAttributeGroups.do";
    }

    @RequestMapping(value = "removeCatalogAttributeGroup",method = RequestMethod.GET)
    @ResponseBody
    public String removeCatalogAttributeGroup(@RequestParam("groupId") int groupId) {
        try {
            /*List<CatalogAttribute> catalogAttributes = attributeService.findAttributeByGroupId(groupId);
            List<String> imagePaths = new ArrayList<>();
            for (CatalogAttribute catalogAttribute : catalogAttributes) {
                String imgDirectory = MediaPather.getPicStoreRealPath(servletContext, catalogAttribute.getImagePath());
                imagePaths.add(imgDirectory);
            }*/
            attributeService.removeCatalogAttributeGroupById(groupId);
            //待删除动作执行成功后删除文件
            /*for (String imgPath : imagePaths) {
                File imgFilePath = new File(imgPath);
                if (imgFilePath.exists()) {
                    imgFilePath.delete();
                }
            }*/
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


    @RequestMapping(value = "getCatalogAttributeGroupsByCatalogId",method = RequestMethod.GET)
    @ResponseBody
    public List<CatalogAttributeGroup> getCatalogAttributeGroupsByCatalogId(@RequestParam("catalogId") int catalogId) {
        List<CatalogAttributeGroup> attributeGroups = attributeService.getAttributeGroupsByCatalogId(catalogId);
        return attributeGroups;
    }


}


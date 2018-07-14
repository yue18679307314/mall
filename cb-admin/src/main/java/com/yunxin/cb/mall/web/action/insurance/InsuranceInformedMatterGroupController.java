package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterGroupService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author likang
 */
@Controller
@RequestMapping(value = "/insuranceInformedMatterGroup")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class InsuranceInformedMatterGroupController {

    @Resource
    private IInsuranceInformedMatterGroupService insuranceInformedMatterGroupService;


    @RequestMapping(value = "insuranceInformedMatterGroups")
    public String insuranceordercodes(ModelMap modelMap) {
        return "imattergroup/listgroup";
    }

    /**
     * InsuranceInformedMatter分页
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceInformedMatterGroup> pageInsuranceInformedMatterGroup(@RequestBody PageSpecification<InsuranceInformedMatterGroup> query) {
        return insuranceInformedMatterGroupService.pageInsuranceInformedMatterGroup(query);
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toAddGroup(@ModelAttribute("InsuranceInformedMatterGroup") InsuranceInformedMatterGroup insuranceInformedMatterGroup, ModelMap modelMap){
        modelMap.addAttribute("insuranceInformedMatterGroup",insuranceInformedMatterGroup);
        return "imattergroup/addgroup";
    }

    /**
     *
     * @param groupId
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toEditGroup(@RequestParam("groupId") int groupId, ModelMap modelMap){
        InsuranceInformedMatterGroup insuranceInformedMatterGroup=insuranceInformedMatterGroupService.getInsuranceInformedMatterGroup(groupId);
        modelMap.addAttribute("insuranceInformedMatterGroup",insuranceInformedMatterGroup);
        return "imattergroup/editgroup";
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addinsuranceInformedMatterGroup(@ModelAttribute("InsuranceInformedMatterGroup") InsuranceInformedMatterGroup insuranceInformedMatterGroup) {
        insuranceInformedMatterGroup.setEnabled(0);
        insuranceInformedMatterGroup.setCreateTime(new Date());
        insuranceInformedMatterGroupService.addInsuranceInformedMatterGroup(insuranceInformedMatterGroup);
        return "redirect:../common/success.do?reurl=insuranceInformedMatterGroup/insuranceInformedMatterGroups.do";
    }

    /**
     *
     * @param insuranceInformedMatterGroup
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String updateinsuranceInformedMatterGroup(@ModelAttribute("InsuranceInformedMatterGroup") InsuranceInformedMatterGroup insuranceInformedMatterGroup) {
        insuranceInformedMatterGroupService.update(insuranceInformedMatterGroup);
        return "redirect:../common/success.do?reurl=insuranceInformedMatterGroup/insuranceInformedMatterGroups.do";
    }

    /**
     *
     * @param groupId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public boolean removeById(@RequestParam("groupId") int groupId) {
        try{
            insuranceInformedMatterGroupService.removeById(groupId);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}

package com.yunxin.cb.system.service.imp;

import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.system.MobileOSType;
import com.yunxin.cb.system.dao.ProfileDao;
import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.cb.system.vo.AppCheckUpdate;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProfileService implements IProfileService {

    @Resource
    private ProfileDao profileDao;

    /**
     * 根据手机系统类型查询APP版本信息
     * @param mobileOSType
     * @return
     */
    @Override
    public AppCheckUpdate getAppCheckUpdate(MobileOSType mobileOSType){
        if(mobileOSType == MobileOSType.ANDROID){
            String v = profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_CODE).getFileValue();
            int versionCode = StringUtils.isNotBlank(v)?Integer.parseInt(v):0;
            String versionName = profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_NAME).getFileValue();
            String appName = profileDao.getProfileByProfileName(ProfileName.ANDROID_APP_NAME).getFileValue();
            String url = profileDao.getProfileByProfileName(ProfileName.ANDROID_URL).getFileValue();
            String description = profileDao.getProfileByProfileName(ProfileName.ANDROID_DESCRIPTION).getFileValue();
            return new AppCheckUpdate(versionCode,versionName, appName, url, description);
        }

        return null;
    }


    /**
     * 系统配置分页信息
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.system.entity.Profile>
     * @exception
     * @date        2018/7/19 9:50
     */
    @Override
    public Page<Profile> pageProfile(PageSpecification<Profile> query){
        query.setCustomSpecification(new CustomSpecification<Profile>() {
            @Override
            public void buildFetch(Root<Profile> root) {

            }
            @Override
            public void addConditions(Root<Profile> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<Profile> page = profileDao.findAll(query, query.getPageRequest());
        return page;
    }

    /**
     * 添加系统配置
     * @author      likang
     * @return      com.yunxin.cb.system.entity.Profile
     * @exception
     * @date        2018/7/19 10:14
     */
    @Override
    @Transactional
    public Profile addProfile(){
        //如果不存在ANDROID的系统配置，直接添加
        if(null==profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_CODE)){
            profileDao.save(new Profile(ProfileName.ANDROID_VERSION_CODE,ProfileName.ANDROID_VERSION_CODE.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.ANDROID_VERSION_NAME,ProfileName.ANDROID_VERSION_NAME.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.ANDROID_APP_NAME,ProfileName.ANDROID_APP_NAME.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.ANDROID_URL,ProfileName.ANDROID_URL.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.ANDROID_DESCRIPTION,ProfileName.ANDROID_DESCRIPTION.getDefaultValue()));
        }else{
            profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_CODE).setFileValue(ProfileName.ANDROID_VERSION_CODE.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_NAME).setFileValue(ProfileName.ANDROID_VERSION_NAME.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.ANDROID_APP_NAME).setFileValue(ProfileName.ANDROID_APP_NAME.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.ANDROID_URL).setFileValue(ProfileName.ANDROID_URL.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.ANDROID_DESCRIPTION).setFileValue(ProfileName.ANDROID_DESCRIPTION.getDefaultValue());
        }
        return null;
    }
}
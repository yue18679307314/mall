package com.yunxin.cb.mall.service;

import com.yunxin.cb.insurance.meta.GratitudeType;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Fridge;
import com.yunxin.cb.mall.vo.*;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

//import com.yunxin.cb.mall.query.CustomerQuery;

/**
 * @author z001075
 */
public interface ICustomerService {

    Fridge addFridge(Fridge fridge);

    Fridge updateFridge(Fridge fridge);

    Fridge getFridgeById(int fridgeId);

    Page<Fridge> pageFridges(final PageSpecification<Fridge> query);

    void removeFridgeById(int fridgeId);

    List<Fridge> getFridgesByCustomerId(int customerId);

    public Customer addCustomer(Customer customer) throws Exception;
    public Customer addAdminCustomer(Customer customer) throws Exception;
    Customer updateMobile(int customerId, String mobile);

    Customer updateMobileChecked(int customerId, boolean mobileChecked);

    Customer updateEmail(int customerId, String email);

    Customer updateEmailChecked(int customerId, boolean mailChecked);

    public Customer updateCustomer(Customer customer) throws EntityExistException;

    public Customer updateCustomerMsg(int customerId, CustomerUpdateVo customerUpdateVo);
    public Customer updateCustomerRank(Customer customer);

    public Customer updateCustomerIntegral(Customer customer);
    public Customer getCustomerById(int customerId);

    public void removeCustomerById(int customerId);

    /**
     * 重置等级编码
     */
    public void resetLevelCodeCode();
    /**
     * 重置邀请码
     */
    public void resetInvitationCode();
    /**
     * 重置重复邀请码
     */
    public List<Customer>  resetRepeatInvitationCode(List<Customer> customerRepeat);

    /**
     * 重置
     * @param customerRepeat
     * @return
     */
    public List<Customer>  resetRepeatLevelCode(List<Customer> customerRepeat);
    /**
     * 生成邀请码跟等级编码
     * @param invitationCode
     * @return
     */
    public Customer  generateCode(String invitationCode);

    /**
     * 根据邀请人生成等级编码
     * @param recommendCustomer
     * @return
     */
    public Customer generateCodeByRecommendCustomer(Customer recommendCustomer) throws  Exception;

    /**
     * 根据等级编码获取上级用户
     * @param levelCode
     * @return
     */
    public List<Customer> findCustomerByLevelCode(String levelCode);

    public Page<Customer> pageCustomers(PageSpecification<Customer> specification);

    void resetCustomerPwd(int customerId);

    public List<Customer> getAllCustomers();

    Customer findCustomerByName(String name);

    /**
     * 修改密码
     *
     * @param customerId
     * @param password
     * @return
     */
    Customer updatePassword(int customerId, String password);

    public Customer getCustomerByAccountNameAndPassword(String accountName, String password);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Customer getCustomerByAccountNameAndMobile(String accountName, String mobile);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Customer getCustomerByMobile(String mobile);
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Customer getCustomerByInvitationCode(String invitationCode);
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Customer getByLevelCode(String levelCode);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Customer getCustomerByEmail(String email);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Customer getCustomerByQqOpenId(String qqOpenId);

    /**
     * 用于前端页面更改
     *
     * @param customer
     * @return
     */
    public Customer updateCustomerForPre(Customer customer);


    /**
     * 邮箱是否存在
     *
     * @param email
     * @return
     */
    public boolean findByEmail(String email);

    public Customer getByEmail(String email);

    /**
     * 用户找回密码
     *
     * @param
     * @param pwds
     */
    public void updatePwdById(String email, String pwds);

    public Customer findByAccountName(String accountName);

    /**
     * 根据用户名查询
     * @param accountName
     * @param
     * @return
     */
    public Customer getAccountName(String accountName);

    /**
     * 统计会员 VIP 数量
     */
    public Map<String, Long> getCustomerNumberForRank();

    public List<Customer> findByRankId(int rankId);

    public Customer findByCustomerId(int customerId);

    long getCustomerCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long countByCreateTimeBetween(Date startDate, Date endDate);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long countByQqOpenId(String qqOpenId);


    public List<CustomerFriend> getFriendByCustomerId(int customerId);

    public CustomerFriend addFriend(CustomerFriend customerFriend);

    public void delFriendById(int customerId, int friendId) throws Exception;

    boolean isFriend(int customerId, int friendId);
    CustomerFriend getFriend(int customerId, int friendId);

    Customer updateAvatar(int customerId, String avatar) throws Exception;

    Customer updateNickName(int customerId, String nickName) throws Exception;

    Customer updateSex(int customerId, boolean sex);

    Customer updateAddress(int customerId, String province, String city, String district, String address);

    CustomerFriend updateFriendsProfile(CustomerFriend customerFriend);

    @Transactional
    boolean customerPraise(int customerId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Customer> getPraiseCustomers(int customerId);
    List<CustomerMatchVo> matchAddressBook(CustomerMatchsVo[] customerMatchsVo);
    void addBlacklist(int friendId, int customerId) throws Exception;

    void removeBlacklist(int friendId, int customerId) throws Exception;

    List<CustomerFriend> getBlacklist(int customerId)throws Exception;

    public void addTwoWayFriend(Customer myself,Customer customer) throws Exception;

    void batchPwdEncode();

    public List<Customer> findCustomerByLikeLevelCode(Customer customer);

    CustomerGratitudeVo findCustomerGratitude(int customerId);

    List<CustomerGratitudeDataVo> findCustomerGratitudeData(int customerId,GratitudeType gratitudeType);

    /**
     * 个人中心数据统计
     * @param customerId
     * @return
     */
    MyTotalVo getInterpersonal(int customerId);

    void enableCustomerById(int customerId,boolean enabled);

    public void CancellationCustomerById(int customerId,boolean ynDelete,String time);

}

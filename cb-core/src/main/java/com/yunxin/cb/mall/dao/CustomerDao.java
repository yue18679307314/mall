/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.meta.PolicyType;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author z001075
 */
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer>, CustomerPlusDao, BaseDao<Customer> {

    @Query("select c from Customer c left join fetch c.rank where (c.accountName=?1 or c.mobile=?1) and c.enabled=?2")
    public Customer findByAccountNameAndEnabled(String accountName, boolean enabled);

    @Query("select c from Customer c where c.accountName=?1 and c.mobile=?2 and c.enabled=?3")
    public Customer findByAccountNameAndMobileAndEnabled(String accountName, String mobile, boolean enabled);

    @Query("select c from Customer c where c.invitationCode is null or c.invitationCode='' ")
    public List<Customer> findByInvitationCodeIsNulls();


    @Query("select c from Customer c group by c.invitationCode having count(c.invitationCode)>1 ORDER BY c.createTime desc ")
    public List<Customer> findInvitationCodeByRepeat();

    @Query("select c from Customer c where c.levelCode is null or c.levelCode='' ")
    public List<Customer> findByLevelCodeIsNulls();

    @Query("select c from Customer c where c.mobile=?1 and c.enabled=?2")
    public Customer findByMobileAndEnabled(String mobile, boolean enabled);
    @Query("select c from Customer c left join fetch c.recommendCustomer where c.customerId=?1")
    public Customer findRecommendCustomer(int customerId);

    @Query("select c from Customer c where c.mobile=:invitationCode or c.invitationCode=:invitationCode")
    public Customer findByMobileOrInvitationCode(@Param("invitationCode") String invitationCode);
    @Query("select c from Customer c where c.levelCode=?1")
    public Customer findByLevelCode(String levelCode);

    public List<Customer> findByLevelCodeIn(List<String> levelCodes);

    @Query("select c from Customer c where c.email=?1 and c.enabled=?2")
    public Customer findByEmailAndEnabled(String email, boolean enabled);

    public Customer findByQqOpenId(String qqOpenId);

    Customer findTopByAccountName(String accountName);

    @Query("select c from Customer c where c.accountName=?1 and c.customerId<>?2")
    public Customer findByAccountNameAndCustomerIdNot(String accountName, int customerId);

    @Query("select c from Customer c left join fetch c.rank where c.customerId=?1")
    public Customer findByCustomerId(int customerId);

    @Query("select count(c.customerId) from Customer c where c.recommendCustomer.customerId=?1 and c.policy=?2 and c.praise=?3")
    public int getCustomerByRecommendCustomer(int customerId,PolicyType policy,boolean praise);

    @Query("select c from Customer c where c.recommendCustomer.customerId=?1 and c.policy=?2 and c.praise=?3")
    public List<Customer> getCustomerByRecommendCustomers(int customerId,PolicyType policy,boolean praise);

    public Customer findByEmail(String email);

    @Query("update Customer c set c.password=?2 where c.email=?1")
    @Modifying
    public void updatePwdById(String email, String pwds);

    @Query("update Customer c set c.enabled=?1 where c.email=?2")
    @Modifying
    public void enableCustomerById(boolean enabled, int customerId);

    @Query("update Customer c set c.invitationCode=?1 where c.customerId=?2")
    @Modifying
    public void updateInvitationCode(String invitationCode,int customerId);

    @Query("update Customer c set c.levelCode=?1,c.customerLevel=?2 where c.customerId=?3")
    @Modifying
    public void updateLevelCode(String LevelCode,int level,int customerId);

    @Query("update Customer c set c.integral=?1 where c.customerId=?2")
    @Modifying
    public void updateIntegralById(int integral, int customerId);

    @Query("update Customer c set c.policy=?1 where c.customerId=?2")
    @Modifying
    public void updatePolicy(PolicyType policy,int customerId);
    @Query("select c from Customer c left join fetch c.rank where c.accountName=?1 ")
    public Customer findByAccountName(String accountName);
    @Query("select c from Customer c where c.accountName=?1")
    public Customer getAccountName(String accountName);

    @Query("select count(c.customerId) from Customer c left join  c.rank r where r.rankName=?1 ")
    public Long findCustomerNumberByRankName(String s);

    public List<Customer> findByRank_RankId(int rankId);


    @Query("select c.customerId from Customer c where c.rank.rankId=?1")
    List<Integer> findCustomerIdsByRandIds(List<Integer> Ids);

    long countByCreateTimeBetween(Date startDate, Date endDate);


    @Query("select c from Customer c left join fetch c.recommendCustomer where c.levelCode like ?1")
    public List<Customer> findCustomerByLikeLevelCode(String levelCode);

    @Query("select count(c.customerId) from Customer c where c.customerId <> ?1 and c.levelCode like ?2 and c.policy=?3")
    public int findAllCustomerByLikeLevelCode(int customerId,String levelCode,PolicyType policy);
    long countByQqOpenId(String qqOpenId);

    List<Customer> findByRecommendCustomer_CustomerIdAndPraise(int customerId, boolean paraise);

    @Modifying
    @Query("update Customer c set c.enabled =?2 where c.customerId=?1")
    public void enableCustomerById(int customerId,boolean enabled);

    @Modifying
    @Query("update Customer c set c.ynDelete =?2,c.mobile=?3 where c.customerId=?1")
    public void CancellationCustomerById(int customerId,boolean ynDelete,String time);
}

interface CustomerPlusDao {

}

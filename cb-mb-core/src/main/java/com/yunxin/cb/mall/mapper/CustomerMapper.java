package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface CustomerMapper {
    @Delete({
            "delete from customer",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer customerId);

    @Insert({
            "insert into customer (CUSTOMER_ID, ACCOUNT_NAME, ",
            "ADDRESS, BIRTHDAY, ",
            "CARD_NO, CITY, CREATE_TIME, ",
            "CUSTOMER_TYPE, DISTRICT, ",
            "EMAIL, ENABLED, EXCHANGE_INTEGRAL, ",
            "INTEGRAL, MAIL_CHECKED, ",
            "MOBILE, MOBILE_CHECKED, ",
            "PASSWORD, POST_CODE, ",
            "PROVINCE, QQ_ACCESS_TOKEN, ",
            "QQ_FIGURE_URL, QQ_NICK_NAME, ",
            "QQ_OPEN_ID, REAL_NAME, ",
            "REMARK, SEX, TELEPHONE, ",
            "TOTAL_INTEGRAL, RANK, ",
            "EMAIL_CHECKED, RONG_CLOUD_TOKEN, ",
            "AVATAR_URL, RECOMMEND_CUSTOMER_ID, ",
            "NICK_NAME, PRAISE, ",
            "PRAISE_NUM, CARD_TYPE, ",
            "CUSTOMER_CARD_NO, CARD_POSITIVE_IMG, ",
            "CARD_NEGATIVE_IMG, BANK_CARD_IMG, ",
            "CUSTOMER_LEVEL, LEVEL_CODE, ",
            "INVITATION_CODE, POLICY, ",
            "CUSTOMER_COUNTRY, CUSTOMER_CARD_PEROID, ",
            "OCCUPATIONAL_CATEGORY, PAYMENT_PASSWORD, AUTH_FLAG)",
            "values (#{customerId,jdbcType=INTEGER}, #{accountName,jdbcType=VARCHAR}, ",
            "#{address,jdbcType=VARCHAR}, #{birthday,jdbcType=TIMESTAMP}, ",
            "#{cardNo,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{customerType,jdbcType=INTEGER}, #{district,jdbcType=VARCHAR}, ",
            "#{email,jdbcType=VARCHAR}, #{enabled,jdbcType=BIT}, #{exchangeIntegral,jdbcType=INTEGER}, ",
            "#{integral,jdbcType=INTEGER}, #{mailChecked,jdbcType=BIT}, ",
            "#{mobile,jdbcType=VARCHAR}, #{mobileChecked,jdbcType=BIT}, ",
            "#{password,jdbcType=VARCHAR}, #{postCode,jdbcType=VARCHAR}, ",
            "#{province,jdbcType=VARCHAR}, #{qqAccessToken,jdbcType=VARCHAR}, ",
            "#{qqFigureUrl,jdbcType=VARCHAR}, #{qqNickName,jdbcType=VARCHAR}, ",
            "#{qqOpenId,jdbcType=VARCHAR}, #{realName,jdbcType=VARCHAR}, ",
            "#{remark,jdbcType=VARCHAR}, #{sex,jdbcType=BIT}, #{telephone,jdbcType=VARCHAR}, ",
            "#{totalIntegral,jdbcType=INTEGER}, #{rank,jdbcType=INTEGER}, ",
            "#{emailChecked,jdbcType=BIT}, #{rongCloudToken,jdbcType=VARCHAR}, ",
            "#{avatarUrl,jdbcType=VARCHAR}, #{recommendCustomerId,jdbcType=INTEGER}, ",
            "#{nickName,jdbcType=VARCHAR}, #{praise,jdbcType=INTEGER}, ",
            "#{praiseNum,jdbcType=INTEGER}, #{cardType,jdbcType=VARCHAR}, ",
            "#{customerCardNo,jdbcType=VARCHAR}, #{cardPositiveImg,jdbcType=VARCHAR}, ",
            "#{cardNegativeImg,jdbcType=VARCHAR}, #{bankCardImg,jdbcType=VARCHAR}, ",
            "#{customerLevel,jdbcType=INTEGER}, #{levelCode,jdbcType=VARCHAR}, ",
            "#{invitationCode,jdbcType=VARCHAR}, #{policy,jdbcType=INTEGER}, ",
            "#{customerCountry,jdbcType=VARCHAR}, #{customerCardPeroid,jdbcType=DATE}, ",
            "#{occupationalCategory,jdbcType=VARCHAR}, #{paymentPassword,jdbcType=VARCHAR}",
            "#{authFlag,jdbcType=INTEGER})"
    })
    int insert(Customer record);

    @Select({
            "select",
            "CUSTOMER_ID, ACCOUNT_NAME, ADDRESS, BIRTHDAY, CARD_NO, CITY, CREATE_TIME, CUSTOMER_TYPE, ",
            "DISTRICT, EMAIL, ENABLED, EXCHANGE_INTEGRAL, INTEGRAL, MAIL_CHECKED, MOBILE, ",
            "MOBILE_CHECKED, PASSWORD, POST_CODE, PROVINCE, QQ_ACCESS_TOKEN, QQ_FIGURE_URL, ",
            "QQ_NICK_NAME, QQ_OPEN_ID, REAL_NAME, REMARK, SEX, TELEPHONE, TOTAL_INTEGRAL, ",
            "RANK, EMAIL_CHECKED, RONG_CLOUD_TOKEN, AVATAR_URL, RECOMMEND_CUSTOMER_ID, NICK_NAME, ",
            "PRAISE, PRAISE_NUM, CARD_TYPE, CUSTOMER_CARD_NO, CARD_POSITIVE_IMG, CARD_NEGATIVE_IMG, ",
            "BANK_CARD_IMG, CUSTOMER_LEVEL, LEVEL_CODE, INVITATION_CODE, POLICY, CUSTOMER_COUNTRY, ",
            "CUSTOMER_CARD_PEROID, OCCUPATIONAL_CATEGORY, PAYMENT_PASSWORD, AUTH_FLAG",
            "from customer",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ACCOUNT_NAME", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADDRESS", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="BIRTHDAY", property="birthday", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="CARD_NO", property="cardNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="CUSTOMER_TYPE", property="customerType", jdbcType=JdbcType.INTEGER),
            @Result(column="DISTRICT", property="district", jdbcType=JdbcType.VARCHAR),
            @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="EXCHANGE_INTEGRAL", property="exchangeIntegral", jdbcType=JdbcType.INTEGER),
            @Result(column="INTEGRAL", property="integral", jdbcType=JdbcType.INTEGER),
            @Result(column="MAIL_CHECKED", property="mailChecked", jdbcType=JdbcType.BIT),
            @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="MOBILE_CHECKED", property="mobileChecked", jdbcType=JdbcType.BIT),
            @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="POST_CODE", property="postCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ_ACCESS_TOKEN", property="qqAccessToken", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ_FIGURE_URL", property="qqFigureUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ_NICK_NAME", property="qqNickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ_OPEN_ID", property="qqOpenId", jdbcType=JdbcType.VARCHAR),
            @Result(column="REAL_NAME", property="realName", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEX", property="sex", jdbcType=JdbcType.BIT),
            @Result(column="TELEPHONE", property="telephone", jdbcType=JdbcType.VARCHAR),
            @Result(column="TOTAL_INTEGRAL", property="totalIntegral", jdbcType=JdbcType.INTEGER),
            @Result(column="RANK", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="EMAIL_CHECKED", property="emailChecked", jdbcType=JdbcType.BIT),
            @Result(column="RONG_CLOUD_TOKEN", property="rongCloudToken", jdbcType=JdbcType.VARCHAR),
            @Result(column="AVATAR_URL", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="RECOMMEND_CUSTOMER_ID", property="recommendCustomerId", jdbcType=JdbcType.INTEGER),
            @Result(column="NICK_NAME", property="nickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRAISE", property="praise", jdbcType=JdbcType.INTEGER),
            @Result(column="PRAISE_NUM", property="praiseNum", jdbcType=JdbcType.INTEGER),
            @Result(column="CARD_TYPE", property="cardType", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_CARD_NO", property="customerCardNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CARD_POSITIVE_IMG", property="cardPositiveImg", jdbcType=JdbcType.VARCHAR),
            @Result(column="CARD_NEGATIVE_IMG", property="cardNegativeImg", jdbcType=JdbcType.VARCHAR),
            @Result(column="BANK_CARD_IMG", property="bankCardImg", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_LEVEL", property="customerLevel", jdbcType=JdbcType.INTEGER),
            @Result(column="LEVEL_CODE", property="levelCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="INVITATION_CODE", property="invitationCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="POLICY", property="policy", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_COUNTRY", property="customerCountry", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_CARD_PEROID", property="customerCardPeroid", jdbcType=JdbcType.DATE),
            @Result(column="OCCUPATIONAL_CATEGORY", property="occupationalCategory", jdbcType=JdbcType.VARCHAR),
            @Result(column="PAYMENT_PASSWORD", property="paymentPassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="AUTH_FLAG", property="authFlag", jdbcType=JdbcType.INTEGER)
    })
    Customer selectByPrimaryKey(Integer customerId);

    @Select({
            "select",
            "CUSTOMER_ID, ACCOUNT_NAME, ADDRESS, BIRTHDAY, CARD_NO, CITY, CREATE_TIME, CUSTOMER_TYPE, ",
            "DISTRICT, EMAIL, ENABLED, EXCHANGE_INTEGRAL, INTEGRAL, MAIL_CHECKED, MOBILE, ",
            "MOBILE_CHECKED, PASSWORD, POST_CODE, PROVINCE, QQ_ACCESS_TOKEN, QQ_FIGURE_URL, ",
            "QQ_NICK_NAME, QQ_OPEN_ID, REAL_NAME, REMARK, SEX, TELEPHONE, TOTAL_INTEGRAL, ",
            "RANK, EMAIL_CHECKED, RONG_CLOUD_TOKEN, AVATAR_URL, RECOMMEND_CUSTOMER_ID, NICK_NAME, ",
            "PRAISE, PRAISE_NUM, CARD_TYPE, CUSTOMER_CARD_NO, CARD_POSITIVE_IMG, CARD_NEGATIVE_IMG, ",
            "BANK_CARD_IMG, CUSTOMER_LEVEL, LEVEL_CODE, INVITATION_CODE, POLICY, CUSTOMER_COUNTRY, ",
            "CUSTOMER_CARD_PEROID, OCCUPATIONAL_CATEGORY, PAYMENT_PASSWORD, AUTH_FLAG",
            "from customer"
    })
    @Results({
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ACCOUNT_NAME", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADDRESS", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="BIRTHDAY", property="birthday", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="CARD_NO", property="cardNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="CUSTOMER_TYPE", property="customerType", jdbcType=JdbcType.INTEGER),
            @Result(column="DISTRICT", property="district", jdbcType=JdbcType.VARCHAR),
            @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="EXCHANGE_INTEGRAL", property="exchangeIntegral", jdbcType=JdbcType.INTEGER),
            @Result(column="INTEGRAL", property="integral", jdbcType=JdbcType.INTEGER),
            @Result(column="MAIL_CHECKED", property="mailChecked", jdbcType=JdbcType.BIT),
            @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="MOBILE_CHECKED", property="mobileChecked", jdbcType=JdbcType.BIT),
            @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="POST_CODE", property="postCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ_ACCESS_TOKEN", property="qqAccessToken", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ_FIGURE_URL", property="qqFigureUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ_NICK_NAME", property="qqNickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ_OPEN_ID", property="qqOpenId", jdbcType=JdbcType.VARCHAR),
            @Result(column="REAL_NAME", property="realName", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEX", property="sex", jdbcType=JdbcType.BIT),
            @Result(column="TELEPHONE", property="telephone", jdbcType=JdbcType.VARCHAR),
            @Result(column="TOTAL_INTEGRAL", property="totalIntegral", jdbcType=JdbcType.INTEGER),
            @Result(column="RANK", property="rank", jdbcType=JdbcType.INTEGER),
            @Result(column="EMAIL_CHECKED", property="emailChecked", jdbcType=JdbcType.BIT),
            @Result(column="RONG_CLOUD_TOKEN", property="rongCloudToken", jdbcType=JdbcType.VARCHAR),
            @Result(column="AVATAR_URL", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="RECOMMEND_CUSTOMER_ID", property="recommendCustomerId", jdbcType=JdbcType.INTEGER),
            @Result(column="NICK_NAME", property="nickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRAISE", property="praise", jdbcType=JdbcType.INTEGER),
            @Result(column="PRAISE_NUM", property="praiseNum", jdbcType=JdbcType.INTEGER),
            @Result(column="CARD_TYPE", property="cardType", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_CARD_NO", property="customerCardNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CARD_POSITIVE_IMG", property="cardPositiveImg", jdbcType=JdbcType.VARCHAR),
            @Result(column="CARD_NEGATIVE_IMG", property="cardNegativeImg", jdbcType=JdbcType.VARCHAR),
            @Result(column="BANK_CARD_IMG", property="bankCardImg", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_LEVEL", property="customerLevel", jdbcType=JdbcType.INTEGER),
            @Result(column="LEVEL_CODE", property="levelCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="INVITATION_CODE", property="invitationCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="POLICY", property="policy", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_COUNTRY", property="customerCountry", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_CARD_PEROID", property="customerCardPeroid", jdbcType=JdbcType.DATE),
            @Result(column="OCCUPATIONAL_CATEGORY", property="occupationalCategory", jdbcType=JdbcType.VARCHAR),
            @Result(column="PAYMENT_PASSWORD", property="paymentPassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="AUTH_FLAG", property="authFlag", jdbcType=JdbcType.INTEGER)
    })
    List<Customer> selectAll();

    @Update({
            "update customer",
            "set ACCOUNT_NAME = #{accountName,jdbcType=VARCHAR},",
            "ADDRESS = #{address,jdbcType=VARCHAR},",
            "BIRTHDAY = #{birthday,jdbcType=TIMESTAMP},",
            "CARD_NO = #{cardNo,jdbcType=VARCHAR},",
            "CITY = #{city,jdbcType=VARCHAR},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "CUSTOMER_TYPE = #{customerType,jdbcType=INTEGER},",
            "DISTRICT = #{district,jdbcType=VARCHAR},",
            "EMAIL = #{email,jdbcType=VARCHAR},",
            "ENABLED = #{enabled,jdbcType=BIT},",
            "EXCHANGE_INTEGRAL = #{exchangeIntegral,jdbcType=INTEGER},",
            "INTEGRAL = #{integral,jdbcType=INTEGER},",
            "MAIL_CHECKED = #{mailChecked,jdbcType=BIT},",
            "MOBILE = #{mobile,jdbcType=VARCHAR},",
            "MOBILE_CHECKED = #{mobileChecked,jdbcType=BIT},",
            "PASSWORD = #{password,jdbcType=VARCHAR},",
            "POST_CODE = #{postCode,jdbcType=VARCHAR},",
            "PROVINCE = #{province,jdbcType=VARCHAR},",
            "QQ_ACCESS_TOKEN = #{qqAccessToken,jdbcType=VARCHAR},",
            "QQ_FIGURE_URL = #{qqFigureUrl,jdbcType=VARCHAR},",
            "QQ_NICK_NAME = #{qqNickName,jdbcType=VARCHAR},",
            "QQ_OPEN_ID = #{qqOpenId,jdbcType=VARCHAR},",
            "REAL_NAME = #{realName,jdbcType=VARCHAR},",
            "REMARK = #{remark,jdbcType=VARCHAR},",
            "SEX = #{sex,jdbcType=BIT},",
            "TELEPHONE = #{telephone,jdbcType=VARCHAR},",
            "TOTAL_INTEGRAL = #{totalIntegral,jdbcType=INTEGER},",
            "RANK = #{rank,jdbcType=INTEGER},",
            "EMAIL_CHECKED = #{emailChecked,jdbcType=BIT},",
            "RONG_CLOUD_TOKEN = #{rongCloudToken,jdbcType=VARCHAR},",
            "AVATAR_URL = #{avatarUrl,jdbcType=VARCHAR},",
            "RECOMMEND_CUSTOMER_ID = #{recommendCustomerId,jdbcType=INTEGER},",
            "NICK_NAME = #{nickName,jdbcType=VARCHAR},",
            "PRAISE = #{praise,jdbcType=INTEGER},",
            "PRAISE_NUM = #{praiseNum,jdbcType=INTEGER},",
            "CARD_TYPE = #{cardType,jdbcType=VARCHAR},",
            "CUSTOMER_CARD_NO = #{customerCardNo,jdbcType=VARCHAR},",
            "CARD_POSITIVE_IMG = #{cardPositiveImg,jdbcType=VARCHAR},",
            "CARD_NEGATIVE_IMG = #{cardNegativeImg,jdbcType=VARCHAR},",
            "BANK_CARD_IMG = #{bankCardImg,jdbcType=VARCHAR},",
            "CUSTOMER_LEVEL = #{customerLevel,jdbcType=INTEGER},",
            "LEVEL_CODE = #{levelCode,jdbcType=VARCHAR},",
            "INVITATION_CODE = #{invitationCode,jdbcType=VARCHAR},",
            "POLICY = #{policy,jdbcType=INTEGER},",
            "CUSTOMER_COUNTRY = #{customerCountry,jdbcType=VARCHAR},",
            "CUSTOMER_CARD_PEROID = #{customerCardPeroid,jdbcType=DATE},",
            "OCCUPATIONAL_CATEGORY = #{occupationalCategory,jdbcType=VARCHAR},",
            "PAYMENT_PASSWORD = #{paymentPassword,jdbcType=VARCHAR}",
            "AUTH_FLAG = #{authFlag,jdbcType=INTEGER}",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Customer record);

    /**
     * 根据用户id修改交易密码
     * @param customerId
     * @param paymentPassword
     * @return
     */
    @Update({
            "update customer",
            "set PAYMENT_PASSWORD = #{paymentPassword,jdbcType=VARCHAR}",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    int updatePaymentPasswordByCustomerId(@Param("customerId")int customerId, @Param("paymentPassword")String paymentPassword);

    /**
     * @title: 根据用户id修改认证状态
     * @param: [customerId, authFlag]
     * @return: int
     * @auther: eleven
     * @date: 2018/8/9 14:26
     */
    @Update({
            "update customer",
            "set AUTH_FLAG = #{authFlag,jdbcType=INTEGER}",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    int updateAuthFlagByCustomerId(@Param("customerId")Integer customerId, @Param("authFlag")Integer authFlag);
}
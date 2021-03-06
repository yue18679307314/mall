package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Advertisement;
import java.util.List;

import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper
public interface AdvertisementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbg.generated Wed Jul 18 10:38:52 CST 2018
     */
    @Delete({
            "delete from advertisement",
            "where ADVERT_ID = #{advertId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer advertId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbg.generated Wed Jul 18 10:38:52 CST 2018
     */
    @Insert({
            "insert into advertisement (ADVERT_ID, ADVERT_CODE, ",
            "ADVERT_TITLE, ADVERT_URL, ",
            "ADVERTISEMENT_PLACE, ADVERTISEMENT_TYPE, ",
            "ADVERTISEMENT_URLTYPE, CLIENT_TYPES, ",
            "CONTENT, CREATE_TIME, ",
            "ENABLED, PIC_PATH, REMARK, ",
            "TEMPLATE_PATH, VIDEO_PATH)",
            "values (#{advertId,jdbcType=INTEGER}, #{advertCode,jdbcType=VARCHAR}, ",
            "#{advertTitle,jdbcType=VARCHAR}, #{advertUrl,jdbcType=VARCHAR}, ",
            "#{advertisementPlace,jdbcType=INTEGER}, #{advertisementType,jdbcType=BIT}, ",
            "#{advertisementUrltype,jdbcType=INTEGER}, #{clientTypes,jdbcType=VARCHAR}, ",
            "#{content,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{enabled,jdbcType=BIT}, #{picPath,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
            "#{templatePath,jdbcType=VARCHAR}, #{videoPath,jdbcType=VARCHAR})"
    })
    int insert(Advertisement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbg.generated Wed Jul 18 10:38:52 CST 2018
     */
    @Select({
            "select",
            "ADVERT_ID, ADVERT_CODE, ADVERT_TITLE, ADVERT_URL, ADVERTISEMENT_PLACE, ADVERTISEMENT_TYPE, ",
            "ADVERTISEMENT_URLTYPE, CLIENT_TYPES, CONTENT, CREATE_TIME, ENABLED, PIC_PATH, ",
            "REMARK, TEMPLATE_PATH, VIDEO_PATH",
            "from advertisement",
            "where ADVERT_ID = #{advertId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="ADVERT_ID", property="advertId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ADVERT_CODE", property="advertCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_TITLE", property="advertTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_URL", property="advertUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERTISEMENT_PLACE", property="advertisementPlace", jdbcType=JdbcType.INTEGER),
            @Result(column="ADVERTISEMENT_TYPE", property="advertisementType", jdbcType=JdbcType.BIT),
            @Result(column="ADVERTISEMENT_URLTYPE", property="advertisementUrltype", jdbcType=JdbcType.INTEGER),
            @Result(column="CLIENT_TYPES", property="clientTypes", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="PIC_PATH", property="picPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="TEMPLATE_PATH", property="templatePath", jdbcType=JdbcType.VARCHAR),
            @Result(column="VIDEO_PATH", property="videoPath", jdbcType=JdbcType.VARCHAR)
    })
    Advertisement selectByPrimaryKey(Integer advertId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbg.generated Wed Jul 18 10:38:52 CST 2018
     */
    @Select({
            "select",
            "ADVERT_ID, ADVERT_CODE, ADVERT_TITLE, ADVERT_URL, ADVERTISEMENT_PLACE, ADVERTISEMENT_TYPE, ",
            "ADVERTISEMENT_URLTYPE, CLIENT_TYPES, CONTENT, CREATE_TIME, ENABLED, PIC_PATH, ",
            "REMARK, TEMPLATE_PATH, VIDEO_PATH",
            "from advertisement"
    })
    @Results({
            @Result(column="ADVERT_ID", property="advertId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ADVERT_CODE", property="advertCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_TITLE", property="advertTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_URL", property="advertUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERTISEMENT_PLACE", property="advertisementPlace", jdbcType=JdbcType.INTEGER),
            @Result(column="ADVERTISEMENT_TYPE", property="advertisementType", jdbcType=JdbcType.BIT),
            @Result(column="ADVERTISEMENT_URLTYPE", property="advertisementUrltype", jdbcType=JdbcType.INTEGER),
            @Result(column="CLIENT_TYPES", property="clientTypes", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="PIC_PATH", property="picPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="TEMPLATE_PATH", property="templatePath", jdbcType=JdbcType.VARCHAR),
            @Result(column="VIDEO_PATH", property="videoPath", jdbcType=JdbcType.VARCHAR)
    })
    List<Advertisement> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbg.generated Wed Jul 18 10:38:52 CST 2018
     */
    @Update({
            "update advertisement",
            "set ADVERT_CODE = #{advertCode,jdbcType=VARCHAR},",
            "ADVERT_TITLE = #{advertTitle,jdbcType=VARCHAR},",
            "ADVERT_URL = #{advertUrl,jdbcType=VARCHAR},",
            "ADVERTISEMENT_PLACE = #{advertisementPlace,jdbcType=INTEGER},",
            "ADVERTISEMENT_TYPE = #{advertisementType,jdbcType=BIT},",
            "ADVERTISEMENT_URLTYPE = #{advertisementUrltype,jdbcType=INTEGER},",
            "CLIENT_TYPES = #{clientTypes,jdbcType=VARCHAR},",
            "CONTENT = #{content,jdbcType=VARCHAR},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "ENABLED = #{enabled,jdbcType=BIT},",
            "PIC_PATH = #{picPath,jdbcType=VARCHAR},",
            "REMARK = #{remark,jdbcType=VARCHAR},",
            "TEMPLATE_PATH = #{templatePath,jdbcType=VARCHAR},",
            "VIDEO_PATH = #{videoPath,jdbcType=VARCHAR}",
            "where ADVERT_ID = #{advertId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Advertisement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advertisement
     *
     * @mbg.generated Wed Jul 18 10:38:52 CST 2018
     */
    @Select({
            "select",
            "ADVERT_ID, ADVERT_CODE, ADVERT_TITLE, ADVERT_URL, ADVERTISEMENT_PLACE, ADVERTISEMENT_TYPE, ",
            "ADVERTISEMENT_URLTYPE, CLIENT_TYPES, CONTENT, CREATE_TIME, ENABLED, PIC_PATH, ",
            "REMARK, TEMPLATE_PATH, VIDEO_PATH",
            "from advertisement",
            "where ADVERTISEMENT_PLACE = #{advertisementPlace,jdbcType=INTEGER}",
            "and ENABLED = #{enabled,jdbcType=BIT}"
    })
    @Results({
            @Result(column="ADVERT_ID", property="advertId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ADVERT_CODE", property="advertCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_TITLE", property="advertTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_URL", property="advertUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERTISEMENT_PLACE", property="advertisementPlace", jdbcType=JdbcType.INTEGER),
            @Result(column="ADVERTISEMENT_TYPE", property="advertisementType", jdbcType=JdbcType.BIT),
            @Result(column="ADVERTISEMENT_URLTYPE", property="advertisementUrltype", jdbcType=JdbcType.INTEGER),
            @Result(column="CLIENT_TYPES", property="clientTypes", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="PIC_PATH", property="picPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="TEMPLATE_PATH", property="templatePath", jdbcType=JdbcType.VARCHAR),
            @Result(column="VIDEO_PATH", property="videoPath", jdbcType=JdbcType.VARCHAR)
    })
    List<Advertisement> selectByPlace(@Param("advertisementPlace")String advertisementPlace,@Param("enabled")Boolean enabled);

    @Select({
            "select",
            "ADVERT_ID, ADVERT_CODE, ADVERT_TITLE, ADVERT_URL, ADVERTISEMENT_PLACE, ADVERTISEMENT_TYPE, ",
            "ADVERTISEMENT_URLTYPE, CLIENT_TYPES, CONTENT, CREATE_TIME, ENABLED, PIC_PATH, ",
            "REMARK, TEMPLATE_PATH, VIDEO_PATH",
            "from advertisement",
            "where ENABLED = #{enabled,jdbcType=BIT}"
    })
    @Results({
            @Result(column="ADVERT_ID", property="advertId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ADVERT_CODE", property="advertCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_TITLE", property="advertTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_URL", property="advertUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERTISEMENT_PLACE", property="advertisementPlace", jdbcType=JdbcType.INTEGER),
            @Result(column="ADVERTISEMENT_TYPE", property="advertisementType", jdbcType=JdbcType.BIT),
            @Result(column="ADVERTISEMENT_URLTYPE", property="advertisementUrltype", jdbcType=JdbcType.INTEGER),
            @Result(column="CLIENT_TYPES", property="clientTypes", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="PIC_PATH", property="picPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="TEMPLATE_PATH", property="templatePath", jdbcType=JdbcType.VARCHAR),
            @Result(column="VIDEO_PATH", property="videoPath", jdbcType=JdbcType.VARCHAR)
    })
    List<Advertisement> select(@Param("enabled")Boolean enabled);

    @Select({
            "select",
            "ADVERT_ID, ADVERT_CODE, ADVERT_TITLE, ADVERT_URL, ADVERTISEMENT_PLACE, ADVERTISEMENT_TYPE, ",
            "ADVERTISEMENT_URLTYPE, CLIENT_TYPES, CONTENT, CREATE_TIME, ENABLED, PIC_PATH, ",
            "REMARK, TEMPLATE_PATH, VIDEO_PATH",
            "from advertisement",
            "where ADVERTISEMENT_PLACE = #{advertisementPlace,jdbcType=INTEGER}",
            "and ENABLED = #{enabled,jdbcType=BIT}"
    })
    @Results({
            @Result(column="ADVERT_ID", property="advertId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ADVERT_CODE", property="advertCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_TITLE", property="advertTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERT_URL", property="advertUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="ADVERTISEMENT_PLACE", property="advertisementPlace", jdbcType=JdbcType.INTEGER),
            @Result(column="ADVERTISEMENT_TYPE", property="advertisementType", jdbcType=JdbcType.BIT),
            @Result(column="ADVERTISEMENT_URLTYPE", property="advertisementUrltype", jdbcType=JdbcType.INTEGER),
            @Result(column="CLIENT_TYPES", property="clientTypes", jdbcType=JdbcType.VARCHAR),
            @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="PIC_PATH", property="picPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="TEMPLATE_PATH", property="templatePath", jdbcType=JdbcType.VARCHAR),
            @Result(column="VIDEO_PATH", property="videoPath", jdbcType=JdbcType.VARCHAR)
    })
    List<Advertisement> selectByAdvertisementPlace(@Param("advertisementPlace")AdvertisementPlace advertisementPlace,@Param("enabled")Boolean enabled);

}
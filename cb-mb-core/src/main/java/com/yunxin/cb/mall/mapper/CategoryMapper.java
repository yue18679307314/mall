package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Category;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface CategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Wed Jul 18 10:47:25 CST 2018
     */
    @Delete({
        "delete from category",
        "where CATEGORY_ID = #{categoryId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer categoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Wed Jul 18 10:47:25 CST 2018
     */
    @Insert({
        "insert into category (CATEGORY_ID, CATEGORY_KEY, ",
        "CATEGORY_NAME, CATEGORY_NO, ",
        "CREATE_TIME, DESCRIPTION, ",
        "ENABLED, ICON_PATH, LEVEL, ",
        "RECOMMEND, REMARK, SEO_DESCRIPTION, ",
        "SEO_KEY, SEO_TITLE, ",
        "SORT_ORDER, PARENT_CATEGORY_ID, ",
        "LOWEST_PRICE, HIGHEST_PRICE)",
        "values (#{categoryId,jdbcType=INTEGER}, #{categoryKey,jdbcType=VARCHAR}, ",
        "#{categoryName,jdbcType=VARCHAR}, #{categoryNo,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{description,jdbcType=VARCHAR}, ",
        "#{enabled,jdbcType=BIT}, #{iconPath,jdbcType=VARCHAR}, #{level,jdbcType=INTEGER}, ",
        "#{recommend,jdbcType=BIT}, #{remark,jdbcType=VARCHAR}, #{seoDescription,jdbcType=VARCHAR}, ",
        "#{seoKey,jdbcType=VARCHAR}, #{seoTitle,jdbcType=VARCHAR}, ",
        "#{sortOrder,jdbcType=INTEGER}, #{parentCategoryId,jdbcType=INTEGER}, ",
        "#{lowestPrice,jdbcType=DECIMAL}, #{highestPrice,jdbcType=DECIMAL})"
    })
    int insert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Wed Jul 18 10:47:25 CST 2018
     */
    @Select({
        "select",
        "CATEGORY_ID, CATEGORY_KEY, CATEGORY_NAME, CATEGORY_NO, CREATE_TIME, DESCRIPTION, ",
        "ENABLED, ICON_PATH, LEVEL, RECOMMEND, REMARK, SEO_DESCRIPTION, SEO_KEY, SEO_TITLE, ",
        "SORT_ORDER, PARENT_CATEGORY_ID, LOWEST_PRICE, HIGHEST_PRICE",
        "from category",
        "where CATEGORY_ID = #{categoryId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CATEGORY_KEY", property="categoryKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="CATEGORY_NAME", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CATEGORY_NO", property="categoryNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="ICON_PATH", property="iconPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="LEVEL", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="RECOMMEND", property="recommend", jdbcType=JdbcType.BIT),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEO_DESCRIPTION", property="seoDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEO_KEY", property="seoKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEO_TITLE", property="seoTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="PARENT_CATEGORY_ID", property="parentCategoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="LOWEST_PRICE", property="lowestPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="HIGHEST_PRICE", property="highestPrice", jdbcType=JdbcType.DECIMAL)
    })
    Category selectByPrimaryKey(Integer categoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Wed Jul 18 10:47:25 CST 2018
     */
    @Select({
        "select",
        "CATEGORY_ID, CATEGORY_KEY, CATEGORY_NAME, CATEGORY_NO, CREATE_TIME, DESCRIPTION, ",
        "ENABLED, ICON_PATH, LEVEL, RECOMMEND, REMARK, SEO_DESCRIPTION, SEO_KEY, SEO_TITLE, ",
        "SORT_ORDER, PARENT_CATEGORY_ID, LOWEST_PRICE, HIGHEST_PRICE",
        "from category"
    })
    @Results({
        @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CATEGORY_KEY", property="categoryKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="CATEGORY_NAME", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CATEGORY_NO", property="categoryNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="ICON_PATH", property="iconPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="LEVEL", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="RECOMMEND", property="recommend", jdbcType=JdbcType.BIT),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEO_DESCRIPTION", property="seoDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEO_KEY", property="seoKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEO_TITLE", property="seoTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="PARENT_CATEGORY_ID", property="parentCategoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="LOWEST_PRICE", property="lowestPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="HIGHEST_PRICE", property="highestPrice", jdbcType=JdbcType.DECIMAL)
    })
    List<Category> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Wed Jul 18 10:47:25 CST 2018
     */
    @Update({
        "update category",
        "set CATEGORY_KEY = #{categoryKey,jdbcType=VARCHAR},",
        "CATEGORY_NAME = #{categoryName,jdbcType=VARCHAR},",
        "CATEGORY_NO = #{categoryNo,jdbcType=VARCHAR},",
        "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
        "DESCRIPTION = #{description,jdbcType=VARCHAR},",
        "ENABLED = #{enabled,jdbcType=BIT},",
        "ICON_PATH = #{iconPath,jdbcType=VARCHAR},",
        "LEVEL = #{level,jdbcType=INTEGER},",
        "RECOMMEND = #{recommend,jdbcType=BIT},",
        "REMARK = #{remark,jdbcType=VARCHAR},",
        "SEO_DESCRIPTION = #{seoDescription,jdbcType=VARCHAR},",
        "SEO_KEY = #{seoKey,jdbcType=VARCHAR},",
        "SEO_TITLE = #{seoTitle,jdbcType=VARCHAR},",
        "SORT_ORDER = #{sortOrder,jdbcType=INTEGER},",
        "PARENT_CATEGORY_ID = #{parentCategoryId,jdbcType=INTEGER},",
        "LOWEST_PRICE = #{lowestPrice,jdbcType=DECIMAL},",
        "HIGHEST_PRICE = #{highestPrice,jdbcType=DECIMAL}",
        "where CATEGORY_ID = #{categoryId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated Wed Jul 18 10:47:25 CST 2018
     */
    @Select({
            "select",
            "CATEGORY_ID, CATEGORY_KEY, CATEGORY_NAME, CATEGORY_NO, CREATE_TIME, DESCRIPTION, ",
            "ENABLED, ICON_PATH, LEVEL, RECOMMEND, REMARK, SEO_DESCRIPTION, SEO_KEY, SEO_TITLE, ",
            "SORT_ORDER, PARENT_CATEGORY_ID, LOWEST_PRICE, HIGHEST_PRICE",
            "from category",
            "where CATEGORY_ID = #{categoryId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CATEGORY_KEY", property="categoryKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="CATEGORY_NAME", property="categoryName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CATEGORY_NO", property="categoryNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="ICON_PATH", property="iconPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="LEVEL", property="level", jdbcType=JdbcType.INTEGER),
            @Result(column="RECOMMEND", property="recommend", jdbcType=JdbcType.BIT),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_DESCRIPTION", property="seoDescription", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_KEY", property="seoKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_TITLE", property="seoTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER),
            @Result(column="PARENT_CATEGORY_ID", property="parentCategoryId", jdbcType=JdbcType.INTEGER),
            @Result(column="LOWEST_PRICE", property="lowestPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="HIGHEST_PRICE", property="highestPrice", jdbcType=JdbcType.DECIMAL)
    })
    List<Category> selectByParentCategoryId(Integer parentCategoryId);
}
package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.OrderLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OrderLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_log
     *
     * @mbg.generated Wed Jul 18 10:37:28 CST 2018
     */
    @Delete({
        "delete from order_log",
        "where ORDERS_LOG_ID = #{ordersLogId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer ordersLogId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_log
     *
     * @mbg.generated Wed Jul 18 10:37:28 CST 2018
     */
    @Insert({
        "insert into order_log (ORDERS_LOG_ID, HANDLER, ",
        "ORDER_CODE, REMARK, ",
        "TIME)",
        "values (#{ordersLogId,jdbcType=INTEGER}, #{handler,jdbcType=VARCHAR}, ",
        "#{orderCode,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{time,jdbcType=TIMESTAMP})"
    })
    int insert(OrderLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_log
     *
     * @mbg.generated Wed Jul 18 10:37:28 CST 2018
     */
    @Select({
        "select",
        "ORDERS_LOG_ID, HANDLER, ORDER_CODE, REMARK, TIME",
        "from order_log",
        "where ORDERS_LOG_ID = #{ordersLogId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ORDERS_LOG_ID", property="ordersLogId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="HANDLER", property="handler", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORDER_CODE", property="orderCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="TIME", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    OrderLog selectByPrimaryKey(Integer ordersLogId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_log
     *
     * @mbg.generated Wed Jul 18 10:37:28 CST 2018
     */
    @Select({
        "select",
        "ORDERS_LOG_ID, HANDLER, ORDER_CODE, REMARK, TIME",
        "from order_log"
    })
    @Results({
        @Result(column="ORDERS_LOG_ID", property="ordersLogId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="HANDLER", property="handler", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORDER_CODE", property="orderCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="TIME", property="time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OrderLog> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_log
     *
     * @mbg.generated Wed Jul 18 10:37:28 CST 2018
     */
    @Update({
        "update order_log",
        "set HANDLER = #{handler,jdbcType=VARCHAR},",
          "ORDER_CODE = #{orderCode,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "TIME = #{time,jdbcType=TIMESTAMP}",
        "where ORDERS_LOG_ID = #{ordersLogId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrderLog record);
}
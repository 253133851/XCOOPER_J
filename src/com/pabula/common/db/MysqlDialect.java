package com.pabula.common.db;

import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.fw.exception.DataAccessException;

import java.sql.*;

/**
 * SQL 方言	Oracle 9i
 *
 * @author Dekn
 *         www.cms4j.com	Nov 21, 2006 1:46:16 AM
 */
public class MysqlDialect extends Dialect {

    public MysqlDialect() {
        super();

        //父类中，日期格式编码，采用了oracle的编码方式
        DATEFORMAT_CODE_YYYY = "yyyy";
        DATEFORMAT_CODE_YY = "yy";
        DATEFORMAT_CODE_MM = "mm";
        DATEFORMAT_CODE_DD = "dd";
        DATEFORMAT_CODE_HH = "HH";
        DATEFORMAT_CODE_HH24 = "hh24";
        DATEFORMAT_CODE_MI = "mi";
        DATEFORMAT_CODE_SS = "ss";

        //mysql的日期格式化
        FROMAT = "%Y-%m-%d %H:%i:%s";
        //当前日期方法
        FUNCTION_NOW = getFUNCTION_NOW();
        //FUNCTION_NOW = "now()";		//mysql的方式

        //关键字定义
        SQL_KEY_ALIAS = " as ";
    }

    public static Timestamp TimestampFormat(Timestamp timestamp) {
        if (null != timestamp && !("").equals(timestamp)) {
            return Timestamp.valueOf(DateUtil.format(timestamp, "yyyy-MM-dd hh:mm:ss"));
        } else
            return null;
    }

    /**
     * 取得分页后的SQL
     *
     * @param sql
     * @param hasOffset 是否为取前N条
     * @param offset    开始
     * @param limit     结束
     * @return
     */
    public String getLimitString(String sql, boolean hasOffset, int offset, int limit) {
        if (offset <= 0 && limit <= 0) {
            return sql;
        }

        return new StringBuffer(sql.length() + 20)
                .append(sql)
                .append(hasOffset ? " limit " + offset + ", " + limit : " limit " + limit)
                .toString();
    }


    /**
     * 获取取得一个时间片段的SQL
     *
     * @param time   如 2006-11-18
     * @param format 如 yyyy-mm-dd
     * @return 如果是oracle语言，则返回  to_date('2006-11-18','yyyy-mm-dd');
     */
    public String getTimeFragment(String time, String format) {
        return "'" + time + "'";
    }


    /**
     * 获取日期比较表达式(MYSQL的dateFormat暂时只能用完整的描述，比如yyyy-mm　yyyy-mm-dd 不能用yy-mm)
     *
     * @param columnName   比较的字段
     * @param compareOper  比较运算符
     * @param compareValue 比较值
     * @param dateFormat   日期格式	暂时只能用完整的描述，比如yyyy-mm　yyyy-mm-dd 不能用yy-mm
     * @return
     */
    public String getDateCompareString(String columnName, String compareOper, String compareValue, String dateFormat) {
//		AND  left(t1.ADD_DATE,7) <=  '2006-11'
//		and left(t1.add_date,7) > '2006-10'

        return new StringBuffer(columnName.length() + 50).
                append(" left(").
                append(columnName).
                append(",").
                append(dateFormat.length()).
                append(")").
                append(compareOper).
                append("'").
                append(compareValue).
                append("'").
                toString();
    }


    /**
     * LEFT 函数
     *
     * @param column
     * @param left
     * @return
     */
    public String functionLeft(String column, int left) {
        return new StringBuffer(column.length() + 20).append(" left(").append(column).append(",").append(left).append(")").toString();
    }


    public String functionToInt(String columnName) {
        return "cast(" + columnName + " as signed integer)";
    }

    /**
     * 随机取记录
     *
     * @return
     * @author skayee
     */
    public String functionRandomByOrder() {
        return "rand()";
    }

    /**
     * 根据SQL删除记录
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:38:42
     */
    public static void deleteColl(String sql) throws DataAccessException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得任务检查项集合", e);
        } finally {
            ResourceManager.close(stmt);
            ResourceManager.close(conn);
        }
    }

}

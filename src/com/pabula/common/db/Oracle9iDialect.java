package com.pabula.common.db;

import com.pabula.common.util.DateUtil;


/**
 * SQL 方言	Oracle 9i
 * @author Dekn
 * www.cms4j.com	Nov 21, 2006 1:46:16 AM
 */
public class Oracle9iDialect extends Dialect {

	public Oracle9iDialect(){
		super();
		
		//父类中，日期格式编码，采用了oracle的编码方式
		DATEFORMAT_CODE_YYYY = "yyyy";
		DATEFORMAT_CODE_YY 	= "yy";
		DATEFORMAT_CODE_MM 	= "mm";
		DATEFORMAT_CODE_DD 	= "dd";
		DATEFORMAT_CODE_HH 	= "HH";
		DATEFORMAT_CODE_HH24 = "hh24";
		DATEFORMAT_CODE_MI 	= "mi";
		DATEFORMAT_CODE_SS 	= "ss";
		
		//当前日期方法 20120207注释
		
		//关键字定义
		SQL_KEY_ALIAS = " ";
		
		//oracle 日期格式化默认格式
		FROMAT = "yyyy-mm-dd hh24:mi:ss";
		
		//FUNCTION_NOW = "sysdate";		
		FUNCTION_NOW = getFUNCTION_NOW();
	}
	
	/**
	 * 
	 */
	public String getFUNCTION_NOW(){
		String date = DateUtil.getCurrentDay("yyyy-MM-dd HH-mm-ss");
		return getTimeFragment(date,FROMAT);
	}
	
	/**
	 * 取得分页后的SQL
	 * @param sql
	 * @param hasOffset	是否为取前N条
	 * @param offset	开始
	 * @param limit	结束
	 * @return
	 */
	public String getLimitString(String sql, boolean hasOffset,int offset,int limit) {
		if(offset <= 0 && limit <= 0){
			return sql;
		}
		
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		if (hasOffset) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		}
		else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect.append(" ) row_ where rownum <= " + (limit + offset) + ") where rownum_ > " + offset);
		}
		else {
			pagingSelect.append(" ) where rownum <= " + limit);
		}

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		
		return pagingSelect.toString();
	}
	
	
	/**
	 * 取得一个时间的片段
	 * @param time	如 2006-11-18
	 * @param foramt	如 yyyy-mm-dd
	 * @return 如果是oracle语言，则返回  to_date('2006-11-18','yyyy-mm-dd');
	 */
	public String getTimeFragment(String time,String format){
		return " to_date('" + time + "','" + format + "') ";
	}
	
	
	/**
	 * LEFT 函数
	 * @param column
	 * @param left
	 * @return
	 */
	public String functionLeft(String column,int left){
		return new StringBuffer(column.length() + 20).append(" Substr(").append(column).append(",").append(left).append(")").toString();
	}
	
	
	
	/**
	 * 获取日期比较表达式
	 * @param columnName	比较的字段
	 * @param compareOper	比较运算符
	 * @param compareValue	比较值
	 * @param dateFormat	日期格式
	 * @return
	 */
	public String getDateCompareString(String columnName,String compareOper,String compareValue,String dateFormat){
//		AND  to_char(t1.ADD_DATE,'yyyy-mm-dd') <=  '2006-12-05'  
//		AND  to_char(t1.ADD_DATE,'yyyy-mm-dd') >  '2006-12-04' 
		return new StringBuffer(columnName.length() + 50).
				append(" to_char(").
				append(columnName).
				append(",'").
				append(dateFormat).
				append("')").
				append(compareOper).
				append("'").
				append(compareValue).
				append("'").
				toString();
	}
	
	/**
	 * tochar函数
	 * @param column
	 * @return
	 * @author dekn   2007-11-13 下午09:38:52
	 */
	public String functionTochar(String column){
		return "to_char(" + column + ")";
	}
	
	
	/**
	 * toInt函数
	 */
	public String functionToInt(String columnName){
		return "to_number(" + columnName + ")";
	}
	
	/**
	 * 随机取记录
	 * @author skayee
	 * @return 
	 */
	public String functionRandomByOrder(){
		return "dbms_random.value";
	}
	
}

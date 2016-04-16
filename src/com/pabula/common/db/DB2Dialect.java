package com.pabula.common.db;

import com.pabula.common.util.DateUtil;


/**
 * db2方言类
 * @author chihiro
 * 2008-10-9
 */
public class DB2Dialect extends Dialect{
	
	public DB2Dialect(){
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
		
		//当前日期方法
		FUNCTION_NOW = "current timestamp";		//db2时间戳函数
		
		//db2的日期格式化
		FROMAT = "yyyy-mm-dd hh24:mi:ss";
		
		//关键字定义
		SQL_KEY_ALIAS = " as ";		
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
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		
		pagingSelect.append("select * from (select row_number()over() as row_num,tmp.* from (");
		
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect.append(" ) tmp ) temp where temp.row_num <= " + (limit + offset) + " and temp.row_num > " + offset);
		}else {
			pagingSelect.append(" ) tmp ) temp where temp.row_num <= " + limit);
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
		return "'" + time + "'";
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
		return new StringBuffer(columnName.length() + 50).
					append(" substr(cast(").
					append(columnName).
					append(" as varchar(30)),1,").
					append(dateFormat.length()).
					append(")").
					append(compareOper).
					append("'").
					append(compareValue).
					append("'").
					toString();
	}
	
	
	public String functionToInt(String columnName){
		return "cast(" + columnName + " as integer)";
	}
	
	/**
	 * 转为为字符型
	 */
	public String functionTochar(String column){
		return "char(" + column + ")";
	}
	
	/**
	 * 获取DB2数据库的时间戳函数
	 */
	public String getFUNCTION_NOW(){
		String date = DateUtil.getCurrentDay("yyyy-MM-dd HH-mm-ss");
		return getTimeFragment(date,FROMAT);
	}
	
	/**
	 * 随机取记录
	 * @author skayee
	 * @return 
	 */
	public String functionRandomByOrder(){
		return "rand()";
	}
}

package com.pabula.common.db;

import com.pabula.common.util.DateUtil;
import com.pabula.fw.exception.DataAccessException;

import java.util.HashMap;


/**
 * SQL ����
 * @author Dekn
 * www.cms4j.com	Nov 21, 2006 1:45:09 AM
 */
public class Dialect {
	

	//�����У����ڸ�ʽ���룬������oracle�ı��뷽ʽ
	public String DATEFORMAT_CODE_YYYY;
	public String DATEFORMAT_CODE_YY;
	public String DATEFORMAT_CODE_MM;
	public String DATEFORMAT_CODE_DD;
	public String DATEFORMAT_CODE_HH;
	public String DATEFORMAT_CODE_HH24;
	public String DATEFORMAT_CODE_MI;
	public String DATEFORMAT_CODE_SS;
	
	//��ǰ���ڷ���
	public String FUNCTION_NOW;
	
	
	//�ؼ��ֶ���
	public String SQL_KEY_ALIAS;
	
	//���ڸ�ʽ�� add by lx 20120207
	public String FROMAT ;
	
	
	//����SQLģ��
	public String updateSQL = 			"UPDATE $TABLE SET $UPDATE_COLUMN $WHERE";
	
	//ɾ��SQLģ��
	public String deleteSQL = 			"DELETE FROM $TABLE $WHERE";
	
	//�������SQLģ��
	public String insertSQL = 			"INSERT INTO $TABLE ($INSERT_COLUMN_NAME) values ($INSERT_COLUMN_VALUE)";
	
	//ѡ�����SQL����׼�ģ�����Ҫ��ҳ�ģ�
	public String selectSQL = 			"SELECT $SELECT_COLUMN FROM $TABLE $LEFT_JOIN $WHERE $GROUP $HAVING $ORDER $DESC";
	
	//ѡ�����SQL����Ҫ��ҳ�ģ�  mysql��oracleʱ������selectSQL��ͬ��ֻ����Ϊsqlserver2005ʱ���Ż�����ͬ
	public String selectSQLForLimit = 	"SELECT $SELECT_COLUMN FROM $TABLE $LEFT_JOIN $WHERE $GROUP $HAVING $ORDER $DESC";
	
	//��ȡ���SQLģ��
	//String selectSQL = "SELECT $SELECT_COLUMN FROM $TABLE $WHERE $ORDER $DESC $LIMIT";
	//String selectSQL = "SELECT $SELECT_COLUMN FROM $TABLE $LEFT_JOIN $WHERE $ORDER $DESC $LIMIT";

	public static HashMap appConfigMap = new HashMap();
	
	public Dialect(){
		//�����У����ڸ�ʽ���룬������oracle�ı��뷽ʽ
		DATEFORMAT_CODE_YYYY = "yyyy";
		DATEFORMAT_CODE_YY 	= "yy";
		DATEFORMAT_CODE_MM 	= "mm";
		DATEFORMAT_CODE_DD 	= "dd";
		DATEFORMAT_CODE_HH 	= "HH";
		DATEFORMAT_CODE_HH24 = "hh24";
		DATEFORMAT_CODE_MI 	= "mi";
		DATEFORMAT_CODE_SS 	= "ss";
		
		//mysql�����ڸ�ʽ��
		FROMAT = "%Y-%m-%d %H:%i:%s";
		//��ǰ���ڷ���
		FUNCTION_NOW = getFUNCTION_NOW();
		//FUNCTION_NOW = "now()";		//mysql�ķ�ʽ
		
		//�ؼ��ֶ���
		SQL_KEY_ALIAS = " as ";
		
	}
	
	//abstract public DialectSuper getDialect() throws DataAccessException;
	/**
	 * add by lx 20120207
	 * ���mysql ���͵���ݿ����ں���
	 */
	public  String getFUNCTION_NOW(){
		String date = DateUtil.getCurrentDay("yyyy-MM-dd HH:mm:ss");
		return getTimeFragment(date,FROMAT);
	}
	
	public static Dialect getDialect() throws DataAccessException {
    	
    	//������
    	//String dialectClassName = (String)appConfigMap.get("DATABASE_DIALECT_CLASS");
		String dialectClassName = "com.pabula.common.db.MysqlDialect";
    	
    	//��ʼ������
		return initDialect( dialectClassName );
	}
//	
	
	public static Dialect getDialect(String dialectClassName) throws DataAccessException {
    	//��ʼ������
		return initDialect( dialectClassName );
	}
	
	
	/**
	 * ��ʼ������
	 * @param dialectClassName	������ȫ��
	 * @return
	 * @throws DataAccessException
	 */
	public static Dialect initDialect(String dialectClassName) throws DataAccessException {
//		if ( dialectClassName == null ) {
//			throw new DataAccessException("���ṩ��ݿⷽ�Ե����");
//		}
//		try {
//			return ( Dialect ) Class.forName(dialectClassName).newInstance();
//		}
//		catch ( ClassNotFoundException cnfe ) {
//			throw new DataAccessException( "δ�ҵ�������: " + dialectClassName );
//		}
//		catch ( Exception e ) {
//			throw new DataAccessException( "�޷���ʼ��������", e );
//		}
		
		return new MysqlDialect();
	}
	
	
	
	
	public String getLimitString(String query, int offset, int limit) {
		return getLimitString( query, offset > 0,offset,limit );
	}
	
	
	/**
	 * �˷�����Ҫ����ķ�����ʵ��
	 * @param sql
	 * @param hasOffset
	 * @param offset
	 * @param limit
	 * @return
	 */
	protected String getLimitString(String sql, boolean hasOffset,int offset,int limit) {
		throw new UnsupportedOperationException( "CMS4J�� ����ݿⷽ�ԣ��ݲ�֧�ַ�ҳ  paged queries not supported" );
	}
	
	
	
	/**
	 * SQL��LIKE�ķ���ʵ��
	 * @param columnName
	 * @param likevalue
	 * @return
	 */
	public String getLikeString(String columnName,String likevalue){
		StringBuffer sql = new StringBuffer( columnName.length()+100 );
		
		return sql.append(columnName).append(" LIKE '").append(likevalue).append("'").toString();
	}
	
	
	/**
	 * ��ȡ���ڱȽϱ��ʽ
	 * @param columnName	�Ƚϵ��ֶ�
	 * @param compareOper	�Ƚ������
	 * @param compareValue	�Ƚ�ֵ
	 * @param dateFormat	���ڸ�ʽ
	 * @return
	 */
	public String getDateCompareString(String columnName,String compareOper,String compareValue,String dateFormat){
		throw new UnsupportedOperationException( "CMS4J�� ����ݿⷽ�ԣ��ݲ�֧�� getDateCompareString �������Ի�ȡ���ڱȽϱ��ʽ" );
	}
	
	
	/**
	 * ��ȡȡ��һ��ʱ��Ƭ�ε�SQL
	 * @param time	�� 2006-11-18
	 * @param foramt	�� yyyy-mm-dd
	 * @return �����oracle���ԣ��򷵻�  to_date('2006-11-18','yyyy-mm-dd');
	 */
	public String getTimeFragment(String time,String format){
		return "'" + time + "'";
	}
	
	
	
	/**
	 * LEFT ����
	 * @param column
	 * @param left
	 * @return
	 */
	public String functionLeft(String column,int left){
		throw new UnsupportedOperationException( "CMS4J�� ����ݿⷽ�ԣ��ݲ�֧�� Left ����" );
	}
	
	/**
	 * LENGTH ����
	 * @param column
	 * @return
	 */
	public String functionLength(String column){
		return "length(" + column + ")";
	}
	
	/**
	 * tochar����
	 * @param column
	 * @return
	 * @author dekn   2007-11-13 ����09:38:52
	 */
	public String functionTochar(String column){
		return "" + column + "";
	}
	
	
	/**
	 * toInt����
	 * @param column
	 * @return
	 */
	public String functionToInt(String column){
		return "" + column + "";
	}
	
	/**
	 * max����
	 * @param column	�ֶ����
	 * @param as		����
	 * @return 			max(COLUMN) as AS
	 */
	public String functionMax(String column,String as){
		return "max(" + column + ") " + SQL_KEY_ALIAS + as;
	}
	
	/**
	 * ���ȡ��¼
	 * @author skayee
	 * @return 
	 */
	public String functionRandomByOrder(){
		return "rand()";
	}
	
}

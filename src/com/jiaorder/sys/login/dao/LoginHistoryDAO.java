package com.jiaorder.sys.login.dao;

import com.jiaorder.sys.login.vo.LoginHistoryVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 登录历史DAO
 * @author pabula
 * 2015-07-12 00:57:50
 */
public class LoginHistoryDAO {

	/**
	 * 添加登录历史
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public void addLoginHistory(LoginHistoryVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOGIN_HISTORY");

			sh.setInsertForInt("ID",VO.getID());//ID
			sh.setInsertForInt("MID",VO.getMID());//会员ID
			sh.setInsertForString("LOGIN_ID",VO.getLOGIN_ID());//登录帐号
			sh.setInsertForDatetime("LAST_LOGIN_DATETIME", DateUtil.format(VO.getLAST_LOGIN_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//登录时间
			sh.setInsertForString("LAST_LOGIN_IP",VO.getLAST_LOGIN_IP());//登录IP
			sh.setInsertForString("LAST_LOGIN_SOURCE",VO.getLAST_LOGIN_SOURCE());//登录渠道

			sh.insert(ResourceManager.getConnection(),"添加登录历史");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改登录历史
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public void modifyLoginHistory(LoginHistoryVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOGIN_HISTORY");

			sh.setColumnForInt("MID",VO.getMID());//会员ID
			sh.setColumnForString("LOGIN_ID",VO.getLOGIN_ID());//登录帐号
			sh.setColumnForDatetime("LAST_LOGIN_DATETIME", DateUtil.format(VO.getLAST_LOGIN_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//登录时间
			sh.setColumnForString("LAST_LOGIN_IP",VO.getLAST_LOGIN_IP());//登录IP
			sh.setColumnForString("LAST_LOGIN_SOURCE",VO.getLAST_LOGIN_SOURCE());//登录渠道

			sh.setWhereForInt("ID", " = ", VO.getID());//ID

			sh.update(ResourceManager.getConnection(),"修改登录历史");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除登录历史
	 * @param ID
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public void delLoginHistory(int ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOGIN_HISTORY");

			sh.setWhereForInt("ID", " = ", ID);//ID

			sh.delete(ResourceManager.getConnection(),"删除登录历史");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除登录历史
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public void delLoginHistory(Collection coll)throws DataAccessException{
		Iterator it=coll.iterator();
		Connection conn=null;
		Statement stmt=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();

			while(it.hasNext()){
				String ids = it.next().toString();
				String[] id = ids.split(",");

				SqlHelper sh = new SqlHelper();

				//设置表名
				sh.setTable("LOGIN_HISTORY");

				//设置Where的条件
				sh.setWhereForInt("ID", " = ", StrUtil.getNotNullIntValue(id[0]));//ID

				String sql = sh.getSQL(sh.getDeleteSQL());

				//添加到批处理中
				stmt.addBatch(sql);
			}

			//执行批处理
			stmt.executeBatch();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally{
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
	}

	/**
	 * 根据SQL获取登录历史集合
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public Collection getLoginHistoryColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				LoginHistoryVO VO = new LoginHistoryVO();

				VO.setID(rs.getInt("ID")); 	//ID
				VO.setMID(rs.getInt("MID")); 	//会员ID
				VO.setLOGIN_ID(rs.getString("LOGIN_ID")); 	//登录帐号
				VO.setLAST_LOGIN_DATETIME(rs.getTimestamp("LAST_LOGIN_DATETIME")); 	//登录时间
				VO.setLAST_LOGIN_IP(rs.getString("LAST_LOGIN_IP")); 	//登录IP
				VO.setLAST_LOGIN_SOURCE(rs.getString("LAST_LOGIN_SOURCE")); 	//登录渠道

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得登录历史集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param ID
	 * @throws DataAccessException
	 * @author pabula 2015-07-12 00:57:50
	 */
	public LoginHistoryVO getLoginHistoryByID(int ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("LOGIN_HISTORY");

		sh.setWhereForInt("ID", " = ", ID);//ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getLoginHistoryColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (LoginHistoryVO)it.next();
		}

		return null;
	}
}
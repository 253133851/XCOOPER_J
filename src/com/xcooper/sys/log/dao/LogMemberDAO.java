package com.xcooper.sys.log.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.xcooper.sys.log.vo.LogMemberVO;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.db.SqlHelper;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.StrUtil;

/**
 * 用户通知DAO
 * @author zdk
 * 2016-05-03 11:59:41
 */
public class LogMemberDAO {

	/**
	 * 添加用户通知
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public void addLogMember(LogMemberVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG_MEMBER");

			sh.setInsertForInt("LOG_MEMBER_ID",VO.getLOG_MEMBER_ID());//LOG_MEMBER_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("LOG_ID",VO.getLOG_ID());//LOG_ID
			sh.setInsertForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setInsertForInt("IS_READ",VO.getIS_READ());//IS_READ
			sh.setInsertForString("REMARK1",VO.getREMARK1());//REMARK1
			sh.setInsertForString("REMARK2",VO.getREMARK2());//REMARK2
			sh.setInsertForString("REMARK3",VO.getREMARK3());//REMARK3
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加用户通知");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改用户通知
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public void modifyLogMember(LogMemberVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG_MEMBER");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("LOG_ID",VO.getLOG_ID());//LOG_ID
			sh.setColumnForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setColumnForInt("IS_READ",VO.getIS_READ());//IS_READ
			sh.setColumnForString("REMARK1",VO.getREMARK1());//REMARK1
			sh.setColumnForString("REMARK2",VO.getREMARK2());//REMARK2
			sh.setColumnForString("REMARK3",VO.getREMARK3());//REMARK3
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME

			sh.setWhereForInt("LOG_MEMBER_ID", " = ", VO.getLOG_MEMBER_ID());//LOG_MEMBER_ID

			sh.update(ResourceManager.getConnection(),"修改用户通知");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除用户通知
	 * @param LOG_MEMBER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public void delLogMember(int LOG_MEMBER_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG_MEMBER");

			sh.setWhereForInt("LOG_MEMBER_ID", " = ", LOG_MEMBER_ID);//LOG_MEMBER_ID

			sh.delete(ResourceManager.getConnection(),"删除用户通知");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除用户通知
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public void delLogMember(Collection coll)throws DataAccessException{
		Iterator it=coll.iterator();
		Connection conn=null;
		Statement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();

			while(it.hasNext()){
				String ids = it.next().toString();
				String[] id = ids.split(",");

				SqlHelper sh = new SqlHelper();

				//设置表名
				sh.setTable("LOG_MEMBER");

				//设置Where的条件
				sh.setWhereForInt("LOG_MEMBER_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//LOG_MEMBER_ID

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
	 * 根据SQL获取用户通知集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public Collection getLogMemberColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				LogMemberVO VO = new LogMemberVO();

				VO.setLOG_MEMBER_ID(rs.getInt("LOG_MEMBER_ID")); 	//LOG_MEMBER_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setLOG_ID(rs.getInt("LOG_ID")); 	//LOG_ID
				VO.setMEMBER_ID(rs.getInt("MEMBER_ID")); 	//MEMBER_ID
				VO.setIS_READ(rs.getInt("IS_READ")); 	//IS_READ
				VO.setREMARK1(rs.getString("REMARK1")); 	//REMARK1
				VO.setREMARK2(rs.getString("REMARK2")); 	//REMARK2
				VO.setREMARK3(rs.getString("REMARK3")); 	//REMARK3
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得用户通知集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param LOG_MEMBER_ID
	 * @throws DataAccessException
	 * @author zdk 2016-05-03 11:59:41
	 */
	public LogMemberVO getLogMemberByID(int LOG_MEMBER_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("LOG_MEMBER");

		sh.setWhereForInt("LOG_MEMBER_ID", " = ", LOG_MEMBER_ID);//LOG_MEMBER_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getLogMemberColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (LogMemberVO)it.next();
		}

		return null;
	}
}
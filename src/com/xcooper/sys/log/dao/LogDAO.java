package com.xcooper.sys.log.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.log.vo.LogVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 日志DAO
 * @author zdk
 * 2016-03-28 19:40:04
 */
public class LogDAO {

	/**
	 * 添加日志
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public void addLog(LogVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG");

			sh.setInsertForInt("LOG_ID",VO.getLOG_ID());//LOG_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("OPERA_ID",VO.getOPERA_ID());//操作人
			sh.setInsertForInt("LOG_TYPE_ID",VO.getLOG_TYPE_ID());//操作类型
			sh.setInsertForInt("TARGET_ID",VO.getTARGET_ID());//操作目标id
			sh.setInsertForString("TARGET",VO.getTARGET());//操作目标
			sh.setInsertForString("OPERA",VO.getOPERA());//操作内容
			sh.setInsertForString("OPERA_TYPE",VO.getOPERA_TYPE());//操作类型
			sh.setInsertForString("LOG_TYPE",VO.getLOG_TYPE());//日志类型
			sh.setInsertForString("REMARK1",VO.getREMARK1());//REMARK1
			sh.setInsertForString("REMARK2",VO.getREMARK2());//REMARK2
			sh.setInsertForString("REMARK3",VO.getREMARK3());//REMARK3
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加日志");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改日志
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public void modifyLog(LogVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("OPERA_ID",VO.getOPERA_ID());//操作人
			sh.setColumnForInt("LOG_TYPE_ID",VO.getLOG_TYPE_ID());//操作类型
			sh.setColumnForInt("TARGET_ID",VO.getTARGET_ID());//操作目标id
			sh.setColumnForString("TARGET",VO.getTARGET());//操作目标
			sh.setColumnForString("OPERA",VO.getOPERA());//操作内容
			sh.setColumnForString("OPERA_TYPE",VO.getOPERA_TYPE());//操作内容
			sh.setColumnForString("LOG_TYPE",VO.getLOG_TYPE());//操作内容
			sh.setColumnForString("REMARK1",VO.getREMARK1());//REMARK1
			sh.setColumnForString("REMARK2",VO.getREMARK2());//REMARK2
			sh.setColumnForString("REMARK3",VO.getREMARK3());//REMARK3
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME

			sh.setWhereForInt("LOG_ID", " = ", VO.getLOG_ID());//LOG_ID

			sh.update(ResourceManager.getConnection(),"修改日志");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除日志
	 * @param LOG_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public void delLog(int LOG_ID)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG");

			sh.setWhereForInt("LOG_ID", " = ", LOG_ID);//LOG_ID

			sh.delete(ResourceManager.getConnection(),"删除日志");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除日志
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public void delLog(Collection coll)throws DataAccessException {
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
				sh.setTable("LOG");

				//设置Where的条件
				sh.setWhereForInt("LOG_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//LOG_ID

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
	 * 根据SQL获取日志集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public Collection getLogColl(String sql)throws DataAccessException {

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				LogVO VO = new LogVO();

				VO.setLOG_ID(rs.getInt("LOG_ID")); 	//LOG_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setOPERA_ID(rs.getInt("OPERA_ID")); 	//操作人
				VO.setLOG_TYPE_ID(rs.getInt("LOG_TYPE_ID")); 	//操作类型
				VO.setTARGET_ID(rs.getInt("TARGET_ID")); 	//操作目标id
				VO.setTARGET(rs.getString("TARGET")); 	//操作目标
				VO.setOPERA(rs.getString("OPERA")); 	//操作内容
				VO.setOPERA(rs.getString("OPERA_TYPE")); 	//操作类型
				VO.setOPERA(rs.getString("LOG_TYPE")); 	//日志类型
				VO.setREMARK1(rs.getString("REMARK1")); 	//REMARK1
				VO.setREMARK2(rs.getString("REMARK2")); 	//REMARK2
				VO.setREMARK3(rs.getString("REMARK3")); 	//REMARK3
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得日志集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param LOG_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:04
	 */
	public LogVO getLogByID(int LOG_ID)throws DataAccessException {

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("LOG");

		sh.setWhereForInt("LOG_ID", " = ", LOG_ID);//LOG_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getLogColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (LogVO)it.next();
		}

		return null;
	}
}
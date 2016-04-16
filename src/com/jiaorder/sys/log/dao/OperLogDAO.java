package com.jiaorder.sys.log.dao;

import com.jiaorder.sys.log.vo.OperLogVO;
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
import java.util.List;

/**
 * 操作日志DAO
 * @author Pabula
 * 2016-03-17 13:37:25
 */
public class OperLogDAO {

	/**
	 * 添加操作日志
	 * @param VO
	 * @throws DataAccessException
	 * @author Pabula 2016-03-17 13:37:25
	 */
	public void addOperLog(OperLogVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SYS_OPER_LOG");

			//sh.setInsertForInt("ID",VO.getID());//ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("UID",VO.getUID());//UID
			sh.setInsertForString("TARGET_ID",VO.getTARGET_ID());//操作目标ID
			sh.setInsertForString("OPER_COMPANY",VO.getOPER_COMPANY());//操作公司
			sh.setInsertForString("OPER_USER",VO.getOPER_USER());//操作人
			sh.setInsertForDatetime("OPER_DATETIME", DateUtil.format(VO.getOPER_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//OPER_DATETIME
			sh.setInsertForString("TYPE",VO.getTYPE());//TYPE
			sh.setInsertForString("OPER_LOG",VO.getOPER_LOG());//OPER_LOG
			sh.setInsertForString("OPER_IP",VO.getOPER_IP());//OPER_IP

			sh.insert(ResourceManager.getConnection(),"添加操作日志");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改操作日志
	 * @param VO
	 * @throws DataAccessException
	 * @author Pabula 2016-03-17 13:37:25
	 */
	public void modifyOperLog(OperLogVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SYS_OPER_LOG");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("UID",VO.getUID());//UID
			sh.setColumnForString("TARGET_ID",VO.getTARGET_ID());//操作目标ID
			sh.setColumnForString("OPER_COMPANY",VO.getOPER_COMPANY());//操作公司
			sh.setColumnForString("OPER_USER",VO.getOPER_USER());//操作人
			sh.setColumnForDatetime("OPER_DATETIME", DateUtil.format(VO.getOPER_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//OPER_DATETIME
			sh.setColumnForString("TYPE",VO.getTYPE());//TYPE
			sh.setColumnForString("OPER_LOG",VO.getOPER_LOG());//OPER_LOG
			sh.setColumnForString("OPER_IP",VO.getOPER_IP());//OPER_IP

			sh.setWhereForInt("ID", " = ", VO.getID());//ID

			sh.update(ResourceManager.getConnection(),"修改操作日志");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除操作日志
	 * @param ID
	 * @throws DataAccessException
	 * @author Pabula 2016-03-17 13:37:25
	 */
	public void delOperLog(int ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SYS_OPER_LOG");

			sh.setWhereForInt("ID", " = ", ID);//ID

			sh.delete(ResourceManager.getConnection(),"删除操作日志");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除操作日志
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author Pabula 2016-03-17 13:37:25
	 */
	public void delOperLog(Collection coll)throws DataAccessException{
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
				sh.setTable("SYS_OPER_LOG");

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
	 * 根据SQL获取操作日志集合
	 * @param sql
	 * @throws DataAccessException
	 * @author Pabula 2016-03-17 13:37:25
	 */
	public List<OperLogVO> getOperLogColl(String sql)throws DataAccessException{
		List resultList = new ArrayList<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				OperLogVO VO = new OperLogVO();

				VO.setID(rs.getInt("ID")); 	//ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setUID(rs.getInt("UID")); 	//UID
				VO.setTARGET_ID(rs.getString("TARGET_ID")); 	//操作目标ID
				VO.setOPER_COMPANY(rs.getString("OPER_COMPANY")); 	//操作公司
				VO.setOPER_USER(rs.getString("OPER_USER")); 	//操作人
				VO.setOPER_DATETIME(rs.getTimestamp("OPER_DATETIME")); 	//OPER_DATETIME
				VO.setTYPE(rs.getString("TYPE")); 	//TYPE
				VO.setOPER_LOG(rs.getString("OPER_LOG")); 	//OPER_LOG
				VO.setOPER_IP(rs.getString("OPER_IP")); 	//OPER_IP

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得操作日志集合", e);
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
	 * @author Pabula 2016-03-17 13:37:25
	 */
	public OperLogVO getOperLogByID(int ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("SYS_OPER_LOG");

		sh.setWhereForInt("ID", " = ", ID);//ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getOperLogColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (OperLogVO)it.next();
		}

		return null;
	}
}
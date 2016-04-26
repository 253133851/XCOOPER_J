package com.xcooper.sys.log.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.log.vo.LogTypeVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 日志类型DAO
 * @author zdk
 * 2016-03-28 19:40:50
 */
public class LogTypeDAO {

	/**
	 * 添加日志类型
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public void addLogType(LogTypeVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG_TYPE");

			sh.setInsertForInt("LOG_TYPE_ID",VO.getLOG_TYPE_ID());//LOG_TYPE_ID
			sh.setInsertForString("TYPE_NAME",VO.getTYPE_NAME());//TYPE_NAME
			sh.setInsertForString("REMARK",VO.getREMARK());//REMARK

			sh.insert(ResourceManager.getConnection(),"添加日志类型");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改日志类型
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public void modifyLogType(LogTypeVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG_TYPE");

			sh.setColumnForString("TYPE_NAME",VO.getTYPE_NAME());//TYPE_NAME
			sh.setColumnForString("REMARK",VO.getREMARK());//REMARK

			sh.setWhereForInt("LOG_TYPE_ID", " = ", VO.getLOG_TYPE_ID());//LOG_TYPE_ID

			sh.update(ResourceManager.getConnection(),"修改日志类型");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除日志类型
	 * @param LOG_TYPE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public void delLogType(int LOG_TYPE_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("LOG_TYPE");

			sh.setWhereForInt("LOG_TYPE_ID", " = ", LOG_TYPE_ID);//LOG_TYPE_ID

			sh.delete(ResourceManager.getConnection(),"删除日志类型");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除日志类型
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public void delLogType(Collection coll)throws DataAccessException{
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
				sh.setTable("LOG_TYPE");

				//设置Where的条件
				sh.setWhereForInt("LOG_TYPE_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//LOG_TYPE_ID

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
	 * 根据SQL获取日志类型集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public Collection getLogTypeColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				LogTypeVO VO = new LogTypeVO();

				VO.setLOG_TYPE_ID(rs.getInt("LOG_TYPE_ID")); 	//LOG_TYPE_ID
				VO.setTYPE_NAME(rs.getString("TYPE_NAME")); 	//TYPE_NAME
				VO.setREMARK(rs.getString("REMARK")); 	//REMARK

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得日志类型集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param LOG_TYPE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:40:50
	 */
	public LogTypeVO getLogTypeByID(int LOG_TYPE_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("LOG_TYPE");

		sh.setWhereForInt("LOG_TYPE_ID", " = ", LOG_TYPE_ID);//LOG_TYPE_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getLogTypeColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (LogTypeVO)it.next();
		}

		return null;
	}
}
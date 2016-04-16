package com.xcooper.sys.user.service.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.user.service.vo.ServiceVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 服务DAO
 * @author zdk
 * 2016-03-28 19:22:33
 */
public class ServiceDAO {

	/**
	 * 添加服务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public void addService(ServiceVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE");

			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("TEAM_ID",VO.getTEAM_ID());//TEAM_ID
			sh.setInsertForInt("STATE",VO.getSTATE());//状态
			sh.setInsertForDatetime("REG_DATETIME", DateUtil.format(VO.getREG_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//注册时间
			sh.setInsertForInt("IS_DEFAULT",VO.getIS_DEFAULT());//是否为默认服务
			sh.setInsertForInt("ADD_UID",VO.getADD_UID());//创建人 UID ADD_UID

			sh.insert(ResourceManager.getConnection(),"添加服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改服务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public void modifyService(ServiceVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE");

			sh.setColumnForInt("TEAM_ID",VO.getTEAM_ID());//TEAM_ID
			sh.setColumnForInt("STATE",VO.getSTATE());//状态
			sh.setColumnForDatetime("REG_DATETIME", DateUtil.format(VO.getREG_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//注册时间
			sh.setColumnForInt("IS_DEFAULT",VO.getIS_DEFAULT());//是否为默认服务
			sh.setColumnForInt("ADD_UID",VO.getADD_UID());//创建人 UID ADD_UID

			sh.setWhereForInt("SERVICE_ID", " = ", VO.getSERVICE_ID());//SERVICE_ID

			sh.update(ResourceManager.getConnection(),"修改服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除服务
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public void delService(int SERVICE_ID)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE");

			sh.setWhereForInt("SERVICE_ID", " = ", SERVICE_ID);//SERVICE_ID

			sh.delete(ResourceManager.getConnection(),"删除服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除服务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public void delService(Collection coll)throws DataAccessException {
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
				sh.setTable("SERVICE");

				//设置Where的条件
				sh.setWhereForInt("SERVICE_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//SERVICE_ID

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
	 * 根据SQL获取服务集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public Collection getServiceColl(String sql)throws DataAccessException {

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ServiceVO VO = new ServiceVO();

				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setTEAM_ID(rs.getInt("TEAM_ID")); 	//TEAM_ID
				VO.setSTATE(rs.getInt("STATE")); 	//状态
				VO.setREG_DATETIME(rs.getTimestamp("REG_DATETIME")); 	//注册时间
				VO.setIS_DEFAULT(rs.getInt("IS_DEFAULT")); 	//是否为默认服务
				VO.setADD_UID(rs.getInt("ADD_UID")); 	//创建人 UID ADD_UID

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得服务集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:22:33
	 */
	public ServiceVO getServiceByID(int SERVICE_ID)throws DataAccessException {

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("SERVICE");

		sh.setWhereForInt("SERVICE_ID", " = ", SERVICE_ID);//SERVICE_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getServiceColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ServiceVO)it.next();
		}

		return null;
	}
}
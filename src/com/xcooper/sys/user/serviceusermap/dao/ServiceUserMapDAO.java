package com.xcooper.sys.user.serviceusermap.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.user.serviceusermap.vo.ServiceUserMapVO;

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
 * 2016-03-28 19:23:04
 */
public class ServiceUserMapDAO {

	/**
	 * 添加服务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public void addServiceUserMap(ServiceUserMapVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE_USER_MAP");

			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//服务ID
			sh.setInsertForInt("UID",VO.getUID());//UID

			sh.insert(ResourceManager.getConnection(),"添加服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改服务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public void modifyServiceUserMap(ServiceUserMapVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE_USER_MAP");

			sh.setColumnForInt("UID",VO.getUID());//UID

			sh.setWhereForInt("SERVICE_ID", " = ", VO.getSERVICE_ID());//服务ID

			sh.update(ResourceManager.getConnection(),"修改服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除服务
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public void delServiceUserMap(int SERVICE_ID)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE_USER_MAP");

			sh.setWhereForInt("SERVICE_ID", " = ", SERVICE_ID);//服务ID

			sh.delete(ResourceManager.getConnection(),"删除服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除服务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:23:04
	 */
	public void delServiceUserMap(Collection coll)throws DataAccessException {
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
				sh.setTable("SERVICE_USER_MAP");

				//设置Where的条件
				sh.setWhereForInt("SERVICE_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//服务ID

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
	 * @author zdk 2016-03-28 19:23:04
	 */
	public Collection getServiceUserMapColl(String sql)throws DataAccessException {

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ServiceUserMapVO VO = new ServiceUserMapVO();

				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//服务ID
				VO.setUID(rs.getInt("UID")); 	//UID

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
	 * @author zdk 2016-03-28 19:23:04
	 */
	public ServiceUserMapVO getServiceUserMapByID(int SERVICE_ID)throws DataAccessException {

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("SERVICE_USER_MAP");

		sh.setWhereForInt("SERVICE_ID", " = ", SERVICE_ID);//服务ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getServiceUserMapColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ServiceUserMapVO)it.next();
		}

		return null;
	}
}
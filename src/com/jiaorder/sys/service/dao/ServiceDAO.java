package com.jiaorder.sys.service.dao;

import com.jiaorder.sys.service.vo.ServiceVO;
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
 * 服务DAO
 * @author pabula
 * 2016-03-11 00:50:35
 */
public class ServiceDAO {

	/**
	 * 添加服务
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:50:35
	 */
	public void addService(ServiceVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE");

			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("COMPANY_ID",VO.getCOMPANY_ID());//COMPANY_ID
			sh.setInsertForInt("STATE",VO.getSTATE());//状态
			sh.setInsertForDatetime("REG_DATETIME", DateUtil.format(DateUtil.getNowTime(), "yyyy-MM-dd HH:mm:ss"), true);//注册时间
			sh.setInsertForString("VER",VO.getVER());//版本
			sh.setInsertForDatetime("BEGIN_DATETIME", DateUtil.format(VO.getBEGIN_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//版本开始时间
			sh.setInsertForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//版本结束时间
			sh.setInsertForInt("IS_DEF",VO.getIS_DEF());//是否为默认服务
			sh.setInsertForInt("ADD_UID",VO.getADD_UID());//创建人 UID ADD_UID
			sh.setInsertForString("SYS_PARAM",VO.getSYS_PARAM());
			sh.setInsertForString("SYS_PARAM_REMARK",VO.getSYS_PARAM_REMARK());


			sh.insert(ResourceManager.getConnection(),"添加服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改服务
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:50:35
	 */
	public void modifyService(ServiceVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE");

			sh.setColumnForInt("COMPANY_ID",VO.getCOMPANY_ID());//COMPANY_ID
			sh.setColumnForInt("STATE",VO.getSTATE());//状态
//			sh.setColumnForDatetime("REG_DATETIME", DateUtil.format(DateUtil.getNowTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//注册时间
			sh.setColumnForString("VER",VO.getVER());//版本
			sh.setColumnForDatetime("BEGIN_DATETIME", DateUtil.format(VO.getBEGIN_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//版本开始时间
			sh.setColumnForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//版本结束时间
			sh.setColumnForInt("IS_DEF",VO.getIS_DEF());//是否为默认服务
			sh.setColumnForInt("ADD_UID",VO.getADD_UID());//创建人 UID ADD_UID
			sh.setColumnForString("SYS_PARAM",VO.getSYS_PARAM());
			sh.setColumnForString("SYS_PARAM_REMARK",VO.getSYS_PARAM_REMARK());

			sh.setWhereForInt("SERVICE_ID", " = ", VO.getSERVICE_ID());//SERVICE_ID

			sh.update(ResourceManager.getConnection(),"修改服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}


	/**
	 * 修改系统参数(当为空时,则不修改)
	 * @param param
	 * @param paramRemark
	 * @param serviceID
	 * @throws DataAccessException
     */
	public void modifyServiceParam(String param,String paramRemark,int serviceID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SERVICE");

			if(StrUtil.isNotNull(param)){
				sh.setColumnForString("SYS_PARAM",param);
			}

			if(StrUtil.isNotNull(paramRemark)){
				sh.setColumnForString("SYS_PARAM_REMARK",paramRemark);
			}

			sh.setWhereForInt("SERVICE_ID", " = ", serviceID);//SERVICE_ID

			sh.update(ResourceManager.getConnection(),"修改服务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除服务
	 * @param SERVICE_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:50:35
	 */
	public void delService(int SERVICE_ID)throws DataAccessException{

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
	 * @author pabula 2016-03-11 00:50:35
	 */
	public void delService(Collection coll)throws DataAccessException{
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
	 * @author pabula 2016-03-11 00:50:35
	 */
	public Collection getServiceColl(String sql)throws DataAccessException{

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
				VO.setCOMPANY_ID(rs.getInt("COMPANY_ID")); 	//COMPANY_ID
				VO.setSTATE(rs.getInt("STATE")); 	//状态
				VO.setREG_DATETIME(rs.getTimestamp("REG_DATETIME")); 	//注册时间
				VO.setVER(rs.getString("VER")); 	//版本
				VO.setBEGIN_DATETIME(rs.getTimestamp("BEGIN_DATETIME")); 	//版本开始时间
				VO.setEND_DATETIME(rs.getTimestamp("END_DATETIME")); 	//版本结束时间
				VO.setIS_DEF(rs.getInt("IS_DEF")); 	//是否为默认服务
				VO.setADD_UID(rs.getInt("ADD_UID")); 	//创建人 UID ADD_UID
				VO.setSYS_PARAM(rs.getString("SYS_PARAM"));
				VO.setSYS_PARAM_REMARK(rs.getString("SYS_PARAM_REMARK"));

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
	 * @author pabula 2016-03-11 00:50:35
	 */
	public ServiceVO getServiceByID(int SERVICE_ID)throws DataAccessException{

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
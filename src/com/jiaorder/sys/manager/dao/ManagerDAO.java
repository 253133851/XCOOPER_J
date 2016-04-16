package com.jiaorder.sys.manager.dao;

import com.jiaorder.sys.manager.vo.ManagerVO;
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
 * 管理员DAO
 * @author pabula
 * 2016-03-11 00:55:08
 */
public class ManagerDAO {

	/**
	 * 添加管理员
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public void addManager(ManagerVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("COMPANY_MANAGER");

			sh.setInsertForInt("MANAGER_ID",VO.getMANAGER_ID());//MANAGER_ID
			sh.setInsertForInt("UID",VO.getUID());//用户ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForString("ROLE_ID_LIST",VO.getROLE_ID_LIST());//所属角色
			sh.setInsertForString("AREA_ID_LIST",VO.getAREA_ID_LIST());//所属地区
//			sh.setInsertForString("USER_NAME",VO.getUSER_NAME());//姓名
//			sh.setInsertForString("USER_CDOE",VO.getUSER_CDOE());//编码
//			sh.setInsertForString("JOB",VO.getJOB());//职位
//			sh.setInsertForString("MOBILE",VO.getMOBILE());//手机
//			sh.setInsertForString("EMAIL",VO.getEMAIL());//邮箱
//			sh.setInsertForString("QQ",VO.getQQ());//QQ
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(DateUtil.getNowTime(), "yyyy-MM-dd HH:mm:ss"), true);//
			sh.setInsertForDatetime("MODIFY_DATETIME", DateUtil.format(DateUtil.getNowTime(), "yyyy-MM-dd HH:mm:ss"), true);//


			sh.insert(ResourceManager.getConnection(),"添加管理员");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改管理员
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public void modifyManager(ManagerVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("COMPANY_MANAGER");

			sh.setColumnForInt("UID",VO.getUID());//用户ID
			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForString("ROLE_ID_LIST",VO.getROLE_ID_LIST());//所属角色
			sh.setColumnForString("AREA_ID_LIST",VO.getAREA_ID_LIST());//所属地区
//			sh.setColumnForString("USER_NAME",VO.getUSER_NAME());//姓名
//			sh.setColumnForString("USER_CDOE",VO.getUSER_CDOE());//编码
//			sh.setColumnForString("JOB",VO.getJOB());//职位
//			sh.setColumnForString("MOBILE",VO.getMOBILE());//手机
//			sh.setColumnForString("EMAIL",VO.getEMAIL());//邮箱
//			sh.setColumnForString("QQ",VO.getQQ());//QQ
			sh.setColumnForDatetime("MODIFY_DATETIME", DateUtil.format(DateUtil.getNowTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//

			sh.setWhereForInt("MANAGER_ID", " = ", VO.getMANAGER_ID());//MANAGER_ID

			sh.update(ResourceManager.getConnection(),"修改管理员");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除管理员
	 * @param MANAGER_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public void delManager(int MANAGER_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("COMPANY_MANAGER");

			sh.setWhereForInt("MANAGER_ID", " = ", MANAGER_ID);//MANAGER_ID

			sh.delete(ResourceManager.getConnection(),"删除管理员");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除管理员
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public void delManager(Collection coll)throws DataAccessException{
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
				sh.setTable("COMPANY_MANAGER");

				//设置Where的条件
				sh.setWhereForInt("MANAGER_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//MANAGER_ID

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
	 * 根据SQL获取管理员集合
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public Collection getManagerColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				ManagerVO VO = new ManagerVO();

				VO.setMANAGER_ID(rs.getInt("MANAGER_ID")); 	//MANAGER_ID
				VO.setUID(rs.getInt("UID")); 	//用户ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setROLE_ID_LIST(rs.getString("ROLE_ID_LIST")); 	//所属角色
				VO.setAREA_ID_LIST(rs.getString("AREA_ID_LIST")); 	//所属地区
//				VO.setUSER_NAME(rs.getString("USER_NAME")); 	//姓名
//				VO.setUSER_CDOE(rs.getString("USER_CDOE")); 	//编码
//				VO.setJOB(rs.getString("JOB")); 	//职位
//				VO.setMOBILE(rs.getString("MOBILE")); 	//手机
//				VO.setEMAIL(rs.getString("EMAIL")); 	//邮箱
//				VO.setQQ(rs.getString("QQ")); 	//QQ
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME("));    //
				VO.setMODIFY_DATETIME(rs.getTimestamp("MODIFY_DATETIME("));    //


				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得管理员集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param MANAGER_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:55:08
	 */
	public ManagerVO getManagerByID(int MANAGER_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("COMPANY_MANAGER");

		sh.setWhereForInt("MANAGER_ID", " = ", MANAGER_ID);//MANAGER_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getManagerColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (ManagerVO)it.next();
		}

		return null;
	}
}
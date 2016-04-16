package com.jiaorder.sys.role.dao;

import com.jiaorder.sys.role.vo.RoleVO;
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
 * 角色DAO
 * @author pabula
 * 2016-03-11 00:57:19
 */
public class RoleDAO {

	/**
	 * 添加角色
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public void addRole(RoleVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SYS_ROLE");

			sh.setInsertForString("ROLE_ID",VO.getROLE_ID());//ROLE_ID
			sh.setInsertForString("ROLE_NAME",VO.getROLE_NAME());//名称
			sh.setInsertForString("INTRO",VO.getINTRO());//描述
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("IS_SYS",VO.getIS_SYS());//是否系统
			sh.setInsertForString("ROLE_SCOPE",VO.getROLE_SCOPE());//角色应用范围
			sh.setInsertForInt("STATE",VO.getSTATE());//状态
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//添加日期
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//修改日期

			sh.insert(ResourceManager.getConnection(),"添加角色");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改角色
	 * @param VO
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public void modifyRole(RoleVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SYS_ROLE");

			sh.setColumnForString("ROLE_NAME",VO.getROLE_NAME());//名称
			sh.setColumnForString("INTRO",VO.getINTRO());//描述
			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("IS_SYS",VO.getIS_SYS());//是否系统
			sh.setColumnForString("ROLE_SCOPE",VO.getROLE_SCOPE());//角色应用范围
			sh.setColumnForInt("STATE",VO.getSTATE());//状态
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//添加日期
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//修改日期

			sh.setWhereForString("ROLE_ID", " = ", VO.getROLE_ID());//ROLE_ID

			sh.update(ResourceManager.getConnection(), "修改角色");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除角色
	 * @param ROLE_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public void delRole(String ROLE_ID)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("SYS_ROLE");

			sh.setWhereForString("ROLE_ID", " = ", ROLE_ID);//ROLE_ID

			sh.delete(ResourceManager.getConnection(),"删除角色");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除角色
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public void delRole(Collection coll)throws DataAccessException {
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
				sh.setTable("SYS_ROLE");

				//设置Where的条件
				sh.setWhereForString("ROLE_ID", " = ", StrUtil.getNotNullStringValue(id[0]));//ROLE_ID

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
	 * 根据SQL获取角色集合
	 * @param sql
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public Collection getRoleColl(String sql)throws DataAccessException {

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				RoleVO VO = new RoleVO();

				VO.setROLE_ID(rs.getString("ROLE_ID")); 	//ROLE_ID
				VO.setROLE_NAME(rs.getString("ROLE_NAME")); 	//名称
				VO.setINTRO(rs.getString("INTRO")); 	//描述
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setIS_SYS(rs.getInt("IS_SYS")); 	//是否系统
				VO.setROLE_SCOPE(rs.getString("ROLE_SCOPE")); 	//角色应用范围
				VO.setSTATE(rs.getInt("STATE")); 	//状态
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//添加日期
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//修改日期

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得角色集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param ROLE_ID
	 * @throws DataAccessException
	 * @author pabula 2016-03-11 00:57:19
	 */
	public RoleVO getRoleByID(String ROLE_ID)throws DataAccessException {

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("SYS_ROLE");

		sh.setWhereForString("ROLE_ID", " = ", ROLE_ID);//ROLE_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getRoleColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (RoleVO)it.next();
		}

		return null;
	}
}
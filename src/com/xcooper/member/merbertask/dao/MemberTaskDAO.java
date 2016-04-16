package com.xcooper.member.merbertask.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.member.merbertask.vo.MemberTaskVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 成员任务DAO
 * @author zdk
 * 2016-03-28 19:36:13
 */
public class MemberTaskDAO {

	/**
	 * 添加成员任务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public void addMemberTask(MemberTaskVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("MEMBER_TASK");

			sh.setInsertForInt("ID",VO.getID());//ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setInsertForInt("TASK_ID",VO.getTASK_ID());//TASK_ID
			sh.setInsertForInt("IS_FOCUS",VO.getIS_FOCUS());//是否关注
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加成员任务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改成员任务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public void modifyMemberTask(MemberTaskVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("MEMBER_TASK");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("MEMBER_ID",VO.getMEMBER_ID());//MEMBER_ID
			sh.setColumnForInt("TASK_ID",VO.getTASK_ID());//TASK_ID
			sh.setColumnForInt("IS_FOCUS",VO.getIS_FOCUS());//是否关注
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME

			sh.setWhereForInt("ID", " = ", VO.getID());//ID

			sh.update(ResourceManager.getConnection(),"修改成员任务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除成员任务
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public void delMemberTask(int ID)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("MEMBER_TASK");

			sh.setWhereForInt("ID", " = ", ID);//ID

			sh.delete(ResourceManager.getConnection(),"删除成员任务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除成员任务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public void delMemberTask(Collection coll)throws DataAccessException {
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
				sh.setTable("MEMBER_TASK");

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
	 * 根据SQL获取成员任务集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:36:13
	 */
	public Collection getMemberTaskColl(String sql)throws DataAccessException {

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				MemberTaskVO VO = new MemberTaskVO();

				VO.setID(rs.getInt("ID")); 	//ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setMEMBER_ID(rs.getInt("MEMBER_ID")); 	//MEMBER_ID
				VO.setTASK_ID(rs.getInt("TASK_ID")); 	//TASK_ID
				VO.setIS_FOCUS(rs.getInt("IS_FOCUS")); 	//是否关注
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//UPDATE_DATETIME

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得成员任务集合", e);
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
	 * @author zdk 2016-03-28 19:36:13
	 */
	public MemberTaskVO getMemberTaskByID(int ID)throws DataAccessException {

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("MEMBER_TASK");

		sh.setWhereForInt("ID", " = ", ID);//ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getMemberTaskColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (MemberTaskVO)it.next();
		}

		return null;
	}
}
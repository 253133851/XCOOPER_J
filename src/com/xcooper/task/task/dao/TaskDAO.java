package com.xcooper.task.task.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.task.task.vo.TaskVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 任务DAO
 * @author zdk
 * 2016-03-28 19:34:51
 */
public class TaskDAO {

	/**
	 * 添加任务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public void addTask(TaskVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TASK");

			sh.setInsertForInt("TASK_ID",VO.getTASK_ID());//TASK_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("PROJECT_ID",VO.getPROJECT_ID());//PROJECT_ID
			sh.setInsertForInt("LIST_ID",VO.getLIST_ID());//LIST_ID
			sh.setInsertForString("TASK_NAME",VO.getTASK_NAME());//TASK_NAME
			sh.setInsertForInt("CREATE_ID",VO.getCREATE_ID());//CREATE_ID
			sh.setInsertForInt("EXE_ID",VO.getEXE_ID());//执行人id
			sh.setInsertForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//END_DATETIME
			sh.setInsertForString("TASK_INFO",VO.getTASK_INFO());//TASK_INFO
			sh.setInsertForInt("IS_DEL",VO.getIS_DEL());//IS_DEL
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME
			sh.setInsertForDatetime("DEL_DATETIME", DateUtil.format(VO.getDEL_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//DEL_DATETIME

			sh.insert(ResourceManager.getConnection(),"添加任务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改任务
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public void modifyTask(TaskVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TASK");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("PROJECT_ID",VO.getPROJECT_ID());//PROJECT_ID
			sh.setColumnForInt("LIST_ID",VO.getLIST_ID());//LIST_ID
			sh.setColumnForString("TASK_NAME",VO.getTASK_NAME());//TASK_NAME
			sh.setColumnForInt("CREATE_ID",VO.getCREATE_ID());//CREATE_ID
			sh.setColumnForInt("EXE_ID",VO.getEXE_ID());//执行人id
			sh.setColumnForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//END_DATETIME
			sh.setColumnForString("TASK_INFO",VO.getTASK_INFO());//TASK_INFO
			sh.setColumnForInt("IS_DEL",VO.getIS_DEL());//IS_DEL
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME
			sh.setColumnForDatetime("DEL_DATETIME", DateUtil.format(VO.getDEL_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//DEL_DATETIME

			sh.setWhereForInt("TASK_ID", " = ", VO.getTASK_ID());//TASK_ID

			sh.update(ResourceManager.getConnection(),"修改任务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除任务
	 * @param TASK_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public void delTask(int TASK_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TASK");

			sh.setWhereForInt("TASK_ID", " = ", TASK_ID);//TASK_ID

			sh.delete(ResourceManager.getConnection(),"删除任务");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除任务
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public void delTask(Collection coll)throws DataAccessException{
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
				sh.setTable("TASK");

				//设置Where的条件
				sh.setWhereForInt("TASK_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//TASK_ID

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
	 * 根据SQL获取任务集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public Collection getTaskColl(String sql,boolean isFocus)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				TaskVO VO = new TaskVO();

				VO.setTASK_ID(rs.getInt("TASK_ID")); 	//TASK_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setPROJECT_ID(rs.getInt("PROJECT_ID")); 	//PROJECT_ID
				VO.setLIST_ID(rs.getInt("LIST_ID")); 	//LIST_ID
				VO.setTASK_NAME(rs.getString("TASK_NAME")); 	//TASK_NAME
				VO.setCREATE_ID(rs.getInt("CREATE_ID")); 	//CREATE_ID
				VO.setEXE_ID(rs.getInt("EXE_ID")); 	//执行人id
				VO.setEND_DATETIME(rs.getTimestamp("END_DATETIME")); 	//END_DATETIME
				VO.setTASK_INFO(rs.getString("TASK_INFO")); 	//TASK_INFO
				VO.setIS_DEL(rs.getInt("IS_DEL")); 	//IS_DEL
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//UPDATE_DATETIME
				VO.setDEL_DATETIME(rs.getTimestamp("DEL_DATETIME")); 	//DEL_DATETIME

				if(isFocus) {
					VO.setIs_focus(rs.getInt("IS_FOCUS"));
				}

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得任务集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param TASK_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:34:51
	 */
	public TaskVO getTaskByID(int TASK_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("TASK");

		sh.setWhereForInt("TASK_ID", " = ", TASK_ID);//TASK_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getTaskColl(sql,false);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (TaskVO)it.next();
		}

		return null;
	}
}
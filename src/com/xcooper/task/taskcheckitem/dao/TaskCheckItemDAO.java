package com.xcooper.task.taskcheckitem.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.task.taskcheckitem.vo.TaskCheckItemVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 任务检查项DAO
 * @author zdk
 * 2016-03-28 19:38:42
 */
public class TaskCheckItemDAO {

	/**
	 * 添加任务检查项
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public void addTaskCheckItem(TaskCheckItemVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TASK_CHECK_ITEM");

			sh.setInsertForInt("ID",VO.getID());//ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("TASK_ID",VO.getTASK_ID());//TASK_ID
			sh.setInsertForString("ITEM_NAME",VO.getITEM_NAME());//ITEM_NAME
			sh.setInsertForInt("IS_DONE",VO.getIS_DONE());//IS_DONE

			sh.insert(ResourceManager.getConnection(),"添加任务检查项");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改任务检查项
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public void modifyTaskCheckItem(TaskCheckItemVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TASK_CHECK_ITEM");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("TASK_ID",VO.getTASK_ID());//TASK_ID
			sh.setColumnForString("ITEM_NAME",VO.getITEM_NAME());//ITEM_NAME
			sh.setColumnForInt("IS_DONE",VO.getIS_DONE());//IS_DONE

			sh.setWhereForInt("ID", " = ", VO.getID());//ID

			sh.update(ResourceManager.getConnection(),"修改任务检查项");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除任务检查项
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public void delTaskCheckItem(int ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("TASK_CHECK_ITEM");

			sh.setWhereForInt("ID", " = ", ID);//ID

			sh.delete(ResourceManager.getConnection(),"删除任务检查项");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除任务检查项
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public void delTaskCheckItem(Collection coll)throws DataAccessException{
		Iterator it=coll.iterator();
		Connection conn=null;
		Statement stmt=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();

			while(it.hasNext()){
				String ids = it.next().toString();
				String[] id = ids.split(",");

				SqlHelper sh = new SqlHelper();

				//设置表名
				sh.setTable("TASK_CHECK_ITEM");

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
	 * 根据SQL获取任务检查项集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:38:42
	 */
	public Collection getTaskCheckItemColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				TaskCheckItemVO VO = new TaskCheckItemVO();

				VO.setID(rs.getInt("ID")); 	//ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setTASK_ID(rs.getInt("TASK_ID")); 	//TASK_ID
				VO.setITEM_NAME(rs.getString("ITEM_NAME")); 	//ITEM_NAME
				VO.setIS_DONE(rs.getInt("IS_DONE")); 	//IS_DONE

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得任务检查项集合", e);
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
	 * @author zdk 2016-03-28 19:38:42
	 */
	public TaskCheckItemVO getTaskCheckItemByID(int ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("TASK_CHECK_ITEM");

		sh.setWhereForInt("ID", " = ", ID);//ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getTaskCheckItemColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (TaskCheckItemVO)it.next();
		}

		return null;
	}
}
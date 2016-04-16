package com.xcooper.sys.file.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.file.vo.FileVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 项目文档DAO
 * @author zdk
 * 2016-03-28 19:31:58
 */
public class FileDAO {

	/**
	 * 添加项目文档
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public void addFile(FileVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("FILE");

			sh.setInsertForInt("ID",VO.getID());//ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("PROJECT_ID",VO.getPROJECT_ID());//PROJECT_ID
			sh.setInsertForInt("TYPE",VO.getTYPE());//类型
			sh.setInsertForString("FILE_NAME",VO.getFILE_NAME());//FILE_NAME
			sh.setInsertForString("FILE_TYPE",VO.getFILE_TYPE());//后缀
			sh.setInsertForString("FILE_SIZE",VO.getFILE_SIZE());//FILE_SIZE
			sh.setInsertForString("PARENT_CLASS_ID",VO.getPARENT_CLASS_ID());//父分类
			sh.setInsertForString("CLASS_PATH",VO.getCLASS_PATH());//分类路径
			sh.setInsertForString("URL",VO.getURL());//URL
			sh.setInsertForInt("CREATE_ID",VO.getCREATE_ID());//CREATE_ID
			sh.setInsertForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//ADD_DATETIME
			sh.setInsertForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//UPDATE_DATETIME
			sh.setInsertForInt("DOWN_NUM",VO.getDOWN_NUM());//下载次数

			sh.insert(ResourceManager.getConnection(),"添加项目文档");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改项目文档
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public void modifyFile(FileVO VO)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("FILE");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("PROJECT_ID",VO.getPROJECT_ID());//PROJECT_ID
			sh.setColumnForInt("TYPE",VO.getTYPE());//类型
			sh.setColumnForString("FILE_NAME",VO.getFILE_NAME());//FILE_NAME
			sh.setColumnForString("FILE_TYPE",VO.getFILE_TYPE());//后缀
			sh.setColumnForString("FILE_SIZE",VO.getFILE_SIZE());//FILE_SIZE
			sh.setColumnForString("PARENT_CLASS_ID",VO.getPARENT_CLASS_ID());//父分类
			sh.setColumnForString("CLASS_PATH",VO.getCLASS_PATH());//分类路径
			sh.setColumnForString("URL",VO.getURL());//URL
			sh.setColumnForInt("CREATE_ID",VO.getCREATE_ID());//CREATE_ID
			sh.setColumnForDatetime("ADD_DATETIME", DateUtil.format(VO.getADD_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//ADD_DATETIME
			sh.setColumnForDatetime("UPDATE_DATETIME", DateUtil.format(VO.getUPDATE_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//UPDATE_DATETIME
			sh.setColumnForInt("DOWN_NUM",VO.getDOWN_NUM());//下载次数

			sh.setWhereForInt("ID", " = ", VO.getID());//ID

			sh.update(ResourceManager.getConnection(),"修改项目文档");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除项目文档
	 * @param ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public void delFile(int ID)throws DataAccessException {

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("FILE");

			sh.setWhereForInt("ID", " = ", ID);//ID

			sh.delete(ResourceManager.getConnection(),"删除项目文档");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除项目文档
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public void delFile(Collection coll)throws DataAccessException {
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
				sh.setTable("FILE");

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
	 * 根据SQL获取项目文档集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:31:58
	 */
	public Collection getFileColl(String sql)throws DataAccessException {

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				FileVO VO = new FileVO();

				VO.setID(rs.getInt("ID")); 	//ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setPROJECT_ID(rs.getInt("PROJECT_ID")); 	//PROJECT_ID
				VO.setTYPE(rs.getInt("TYPE")); 	//类型
				VO.setFILE_NAME(rs.getString("FILE_NAME")); 	//FILE_NAME
				VO.setFILE_TYPE(rs.getString("FILE_TYPE")); 	//后缀
				VO.setFILE_SIZE(rs.getString("FILE_SIZE")); 	//FILE_SIZE
				VO.setPARENT_CLASS_ID(rs.getString("PARENT_CLASS_ID")); 	//父分类
				VO.setCLASS_PATH(rs.getString("CLASS_PATH")); 	//分类路径
				VO.setURL(rs.getString("URL")); 	//URL
				VO.setCREATE_ID(rs.getInt("CREATE_ID")); 	//CREATE_ID
				VO.setADD_DATETIME(rs.getTimestamp("ADD_DATETIME")); 	//ADD_DATETIME
				VO.setUPDATE_DATETIME(rs.getTimestamp("UPDATE_DATETIME")); 	//UPDATE_DATETIME
				VO.setDOWN_NUM(rs.getInt("DOWN_NUM")); 	//下载次数

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得项目文档集合", e);
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
	 * @author zdk 2016-03-28 19:31:58
	 */
	public FileVO getFileByID(int ID)throws DataAccessException {

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("FILE");

		sh.setWhereForInt("ID", " = ", ID);//ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getFileColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (FileVO)it.next();
		}

		return null;
	}
}
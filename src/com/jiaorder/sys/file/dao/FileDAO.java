package com.jiaorder.sys.file.dao;

import com.jiaorder.sys.file.vo.FileVO;
import com.pabula.common.db.SqlHelper;
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
import java.util.List;

/**
 * 附件表DAO
 * @author zdk
 * 2016-03-22 10:22:34
 */
public class FileDAO {

	/**
	 * 添加附件表
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-22 10:22:34
	 */
	public void addFile(FileVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();
			sh.setTable("FILE");
			sh.setInsertForInt("FILE_ID",VO.getFILE_ID());//FILE_ID
			sh.setInsertForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setInsertForInt("TARGET_ID",VO.getTARGET_ID());//目标id
			sh.setInsertForInt("SIZE",VO.getSIZE());
			sh.setInsertForString("FILE_TYPE",VO.getFILE_TYPE());
			sh.setInsertForString("TYPE",VO.getTYPE());//附件类型
			sh.setInsertForString("FILE_NAME",VO.getFILE_NAME());//附件名
			sh.setInsertForString("FILE_PATH",VO.getFILE_PATH());//附件路径
			sh.insert(ResourceManager.getConnection(),"添加附件表");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改附件表
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-22 10:22:34
	 */
	public void modifyFile(FileVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("FILE");

			sh.setColumnForInt("SERVICE_ID",VO.getSERVICE_ID());//SERVICE_ID
			sh.setColumnForInt("TARGET_ID",VO.getTARGET_ID());//目标id
			sh.setColumnForString("TYPE",VO.getTYPE());//附件类型
			sh.setColumnForString("FILE_NAME",VO.getFILE_NAME());//附件名
			sh.setColumnForString("FILE_PATH",VO.getFILE_PATH());//附件路径
			sh.setColumnForInt("SIZE",VO.getSIZE());
			sh.setColumnForString("FILE_TYPE",VO.getFILE_TYPE());
			sh.setWhereForInt("FILE_ID", " = ", VO.getFILE_ID());//FILE_ID

			sh.update(ResourceManager.getConnection(),"修改附件表");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除附件表
	 * @param FILE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-22 10:22:34
	 */
	public void delFile(int FILE_ID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("FILE");

			sh.setWhereForInt("FILE_ID", " = ", FILE_ID);//FILE_ID

			sh.delete(ResourceManager.getConnection(),"删除附件表");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除附件表
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-22 10:22:34
	 */
	public void delFile(Collection coll)throws DataAccessException{
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
				sh.setWhereForInt("FILE_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//FILE_ID

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
	 * 根据SQL获取附件表集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-22 10:22:34
	 */
	public List<FileVO> getFileColl(String sql)throws DataAccessException{

		List<FileVO> resultList = new ArrayList<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn= ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				FileVO VO = new FileVO();

				VO.setFILE_ID(rs.getInt("FILE_ID")); 	//FILE_ID
				VO.setSERVICE_ID(rs.getInt("SERVICE_ID")); 	//SERVICE_ID
				VO.setTARGET_ID(rs.getInt("TARGET_ID")); 	//目标id
				VO.setTYPE(rs.getString("TYPE")); 	//附件类型
				VO.setFILE_NAME(rs.getString("FILE_NAME")); 	//附件名
				VO.setFILE_PATH(rs.getString("FILE_PATH")); 	//附件路径
				VO.setSIZE(rs.getInt("SIZE"));
				VO.setFILE_TYPE(rs.getString("FILE_TYPE"));
				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得附件表集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param FILE_ID
	 * @throws DataAccessException
	 * @author zdk 2016-03-22 10:22:34
	 */
	public FileVO getFileByID(int FILE_ID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("FILE");

		sh.setWhereForInt("FILE_ID", " = ", FILE_ID);//FILE_ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getFileColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (FileVO)it.next();
		}

		return null;
	}
}
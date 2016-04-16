package com.xcooper.sys.user.user.dao;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.sys.user.user.vo.UserVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 用户DAO
 * @author zdk
 * 2016-03-28 19:20:05
 */
public class UserDAO {

	/**
	 * 添加用户
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public void addUser(UserVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("USER");

			sh.setInsertForInt("UID",VO.getUID());//用户ID
			sh.setInsertForInt("LOGIN_ID",VO.getLOGIN_ID());//登录ID
			sh.setInsertForString("PWD",VO.getPWD());//密码
			sh.setInsertForDatetime("REG_DATETIME", DateUtil.format(VO.getREG_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//注册日期
			sh.setInsertForInt("LOGINS",VO.getLOGINS());//登录次数
			sh.setInsertForDatetime("LAST_LOGIN_DATETIME", DateUtil.format(VO.getLAST_LOGIN_DATETIME(),"yyyy-MM-dd HH:mm:ss"), true);//最后登录时间
			sh.setInsertForString("LAST_LOGIN_IP",VO.getLAST_LOGIN_IP());//最后登录IP
			sh.setInsertForString("QUESTION",VO.getQUESTION());//密码问题
			sh.setInsertForString("ANSWER",VO.getANSWER());//问题答案
			sh.setInsertForInt("STATE",VO.getSTATE());//用户状态
			sh.setInsertForString("EMAIL",VO.getEMAIL());//EMAIL
			sh.setInsertForString("FACE_URL",VO.getFACE_URL());//头像
			sh.setInsertForString("USER_REMARK",VO.getUSER_REMARK());//个人说明/签名
			sh.setInsertForString("REG_VILIDATE_CODE",VO.getREG_VILIDATE_CODE());//注册验证码
			sh.setInsertForString("PHONE",VO.getPHONE());//手机号

			sh.insert(ResourceManager.getConnection(),"添加用户");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 修改用户
	 * @param VO
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public void modifyUser(UserVO VO)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("USER");

			sh.setColumnForInt("LOGIN_ID",VO.getLOGIN_ID());//登录ID
			sh.setColumnForString("PWD",VO.getPWD());//密码
			sh.setColumnForDatetime("REG_DATETIME", DateUtil.format(VO.getREG_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//注册日期
			sh.setColumnForInt("LOGINS",VO.getLOGINS());//登录次数
			sh.setColumnForDatetime("LAST_LOGIN_DATETIME", DateUtil.format(VO.getLAST_LOGIN_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//最后登录时间
			sh.setColumnForString("LAST_LOGIN_IP",VO.getLAST_LOGIN_IP());//最后登录IP
			sh.setColumnForString("QUESTION",VO.getQUESTION());//密码问题
			sh.setColumnForString("ANSWER",VO.getANSWER());//问题答案
			sh.setColumnForInt("STATE",VO.getSTATE());//用户状态
			sh.setColumnForString("EMAIL",VO.getEMAIL());//EMAIL
			sh.setColumnForString("FACE_URL",VO.getFACE_URL());//头像
			sh.setColumnForString("USER_REMARK",VO.getUSER_REMARK());//个人说明/签名
			sh.setColumnForString("REG_VILIDATE_CODE",VO.getREG_VILIDATE_CODE());//注册验证码
			sh.setColumnForString("PHONE",VO.getPHONE());//手机号

			sh.setWhereForInt("UID", " = ", VO.getUID());//用户ID

			sh.update(ResourceManager.getConnection(),"修改用户");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 删除用户
	 * @param UID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public void delUser(int UID)throws DataAccessException{

		try {
			SqlHelper sh = new SqlHelper();

			sh.setTable("USER");

			sh.setWhereForInt("UID", " = ", UID);//用户ID

			sh.delete(ResourceManager.getConnection(),"删除用户");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 批量删除用户
	 * @param coll 主键集合，多个主键之间用半角逗号隔开
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public void delUser(Collection coll)throws DataAccessException{
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
				sh.setTable("USER");

				//设置Where的条件
				sh.setWhereForInt("UID", " = ", StrUtil.getNotNullIntValue(id[0]));//用户ID

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
	 * 根据SQL获取用户集合
	 * @param sql
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public Collection getUserColl(String sql)throws DataAccessException{

		Collection resultList = new ArrayList();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=ResourceManager.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				UserVO VO = new UserVO();

				VO.setUID(rs.getInt("UID")); 	//用户ID
				VO.setLOGIN_ID(rs.getInt("LOGIN_ID")); 	//登录ID
				VO.setPWD(rs.getString("PWD")); 	//密码
				VO.setREG_DATETIME(rs.getTimestamp("REG_DATETIME")); 	//注册日期
				VO.setLOGINS(rs.getInt("LOGINS")); 	//登录次数
				VO.setLAST_LOGIN_DATETIME(rs.getTimestamp("LAST_LOGIN_DATETIME")); 	//最后登录时间
				VO.setLAST_LOGIN_IP(rs.getString("LAST_LOGIN_IP")); 	//最后登录IP
				VO.setQUESTION(rs.getString("QUESTION")); 	//密码问题
				VO.setANSWER(rs.getString("ANSWER")); 	//问题答案
				VO.setSTATE(rs.getInt("STATE")); 	//用户状态
				VO.setEMAIL(rs.getString("EMAIL")); 	//EMAIL
				VO.setFACE_URL(rs.getString("FACE_URL")); 	//头像
				VO.setUSER_REMARK(rs.getString("USER_REMARK")); 	//个人说明/签名
				VO.setREG_VILIDATE_CODE(rs.getString("REG_VILIDATE_CODE")); 	//注册验证码
				VO.setPHONE(rs.getString("PHONE")); 	//手机号

				resultList.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("DAO　Layer: 获得用户集合", e);
		}finally{
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			ResourceManager.close(conn);
		}
		return resultList;
	}

	/**
	 * 根据ID取其VO
	 * @param UID
	 * @throws DataAccessException
	 * @author zdk 2016-03-28 19:20:05
	 */
	public UserVO getUserByID(int UID)throws DataAccessException{

		SqlHelper sh = new SqlHelper();

		sh.setSelectColumn("*");

		sh.setTable("USER");

		sh.setWhereForInt("UID", " = ", UID);//用户ID

		String sql = sh.getSQL(sh.getSelectSQL());

		Collection coll = getUserColl(sql);
		Iterator it = coll.iterator();
		if(it.hasNext()){
			return (UserVO)it.next();
		}

		return null;
	}
}
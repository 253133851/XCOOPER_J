package com.jiaorder.sys.user.dao;

import com.jiaorder.sys.user.vo.UserVO;
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
import java.util.List;

/**
 * 用户DAO
 *
 * @author pabula
 *         2016-03-11 00:49:22
 */
public class UserDAO {

    /**
     * 添加用户
     *
     * @param VO
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public void addUser(UserVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("USER");

            sh.setInsertForInt("UID", VO.getUID());//用户ID
            sh.setInsertForString("LOGIN_ID", VO.getLOGIN_ID());//登录ID
            sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
            sh.setInsertForString("TYPE", VO.getTYPE());
            sh.setInsertForString("PWD", VO.getPWD());//密码
            sh.setInsertForDatetime("REG_DATE", DateUtil.format(DateUtil.getNowTime(), "yyyy-MM-dd HH:mm:ss"), true);//注册日期
            sh.setInsertForInt("LOGINS", VO.getLOGINS());//登录次数
            sh.setInsertForDatetime("LAST_LOGIN_DATE", DateUtil.format(VO.getLAST_LOGIN_DATE(), "yyyy-MM-dd HH:mm:ss"), true);//最后登录时间
            sh.setInsertForString("LAST_LOGIN_IP", VO.getLAST_LOGIN_IP());//最后登录IP
            sh.setInsertForString("QUESTION", VO.getQUESTION());//密码问题
            sh.setInsertForString("ANSWER", VO.getANSWER());//问题答案
            sh.setInsertForInt("STATE", VO.getSTATE());//用户状态
            sh.setInsertForInt("AUDIT_STATE", VO.getAUDIT_STATE());//审核状态
            sh.setInsertForString("USER_NAME", VO.getUSER_NAME());//
            sh.setInsertForString("EMAIL", VO.getEMAIL());//EMAIL
            sh.setInsertForString("FACE_URL", VO.getFACE_URL());//头像
            sh.setInsertForString("USER_REMARK", VO.getUSER_REMARK());//个人说明/签名
            sh.setInsertForString("REG_SOURCE", VO.getREG_SOURCE());//注册来源
            sh.setInsertForString("REG_VILIDATE_CODE", VO.getREG_VILIDATE_CODE());//注册验证码
            sh.setInsertForString("ADD_USER_PATH", VO.getADD_USER_PATH());//添加人路径
            sh.setInsertForString("MOBILE", VO.getMOBILE());//手机号
            sh.setInsertForString("JOB", VO.getJOB());
            sh.setInsertForString("QQ", VO.getQQ());
            sh.setInsertForString("USER_CODE", VO.getUSER_CODE());


            sh.insert(ResourceManager.getConnection(), "添加用户");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 修改用户
     *
     * @param VO
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public void modifyUser(UserVO VO) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("USER");

            sh.setColumnForString("LOGIN_ID", VO.getLOGIN_ID());//登录ID
            sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
            sh.setColumnForString("TYPE", VO.getTYPE());
            sh.setColumnForString("PWD", VO.getPWD());//密码
//			sh.setColumnForDatetime("REG_DATE", DateUtil.format(VO.getREG_DATE(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//注册日期
            sh.setColumnForInt("LOGINS", VO.getLOGINS());//登录次数
            sh.setColumnForDatetime("LAST_LOGIN_DATE", DateUtil.format(VO.getLAST_LOGIN_DATE(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//最后登录时间
            sh.setColumnForString("LAST_LOGIN_IP", VO.getLAST_LOGIN_IP());//最后登录IP
            sh.setColumnForString("QUESTION", VO.getQUESTION());//密码问题
            sh.setColumnForString("ANSWER", VO.getANSWER());//问题答案
            sh.setColumnForInt("STATE", VO.getSTATE());//用户状态
            sh.setColumnForInt("AUDIT_STATE", VO.getAUDIT_STATE());//审核状态
            sh.setColumnForString("USER_NAME", VO.getUSER_NAME());//
            sh.setColumnForString("EMAIL", VO.getEMAIL());//EMAIL
            sh.setColumnForString("FACE_URL", VO.getFACE_URL());//头像
            sh.setColumnForString("USER_REMARK", VO.getUSER_REMARK());//个人说明/签名
            sh.setColumnForString("REG_SOURCE", VO.getREG_SOURCE());//注册来源
            sh.setColumnForString("REG_VILIDATE_CODE", VO.getREG_VILIDATE_CODE());//注册验证码
            sh.setColumnForString("ADD_USER_PATH", VO.getADD_USER_PATH());//添加人路径
            sh.setColumnForString("MOBILE", VO.getMOBILE());//手机号
            sh.setColumnForString("QQ", VO.getQQ());
            sh.setColumnForString("JOB", VO.getJOB());
            sh.setColumnForString("USER_CODE", VO.getUSER_CODE());

            sh.setWhereForInt("UID", " = ", VO.getUID());//用户ID

            sh.update(ResourceManager.getConnection(), "修改用户");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 修改用户密码
     *
     * @param mobile
     * @param pwd
     * @throws DataAccessException
     */
    public void modifyUserPWD(String mobile, String pwd) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("USER");

            sh.setColumnForString("PWD", pwd);//密码

            sh.setWhereForString("MOBILE", " = ", mobile);//用户手机

            sh.update(ResourceManager.getConnection(), "修改用户密码");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }


    /**
     * 修改用户密码
     *
     * @param uid
     * @param pwd
     * @throws DataAccessException
     */
    public void modifyUserPWD(int uid, String pwd) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("USER");

            sh.setColumnForString("PWD", pwd);//密码

            sh.setWhereForInt("UID", " = ", uid);

            sh.update(ResourceManager.getConnection(), "修改用户密码");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }


    /**
     * 删除用户
     *
     * @param UID
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public void delUser(int UID) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("USER");

            sh.setWhereForInt("UID", " = ", UID);//用户ID

            sh.delete(ResourceManager.getConnection(), "删除用户");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 批量删除用户
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public void delUser(Collection coll) throws DataAccessException {
        Iterator it = coll.iterator();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();

            while (it.hasNext()) {
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
        } finally {
            ResourceManager.close(stmt);
            ResourceManager.close(conn);
        }
    }

    /**
     * 根据SQL获取用户集合
     *
     * @param sql
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public Collection getUserColl(String sql) throws DataAccessException {

        Collection resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                UserVO VO = new UserVO();

                VO.setUID(rs.getInt("UID"));    //用户ID
                VO.setLOGIN_ID(rs.getString("LOGIN_ID"));    //登录ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
                VO.setTYPE(rs.getString("TYPE"));
                VO.setPWD(rs.getString("PWD"));    //密码
                VO.setREG_DATE(rs.getTimestamp("REG_DATE"));    //注册日期
                VO.setLOGINS(rs.getInt("LOGINS"));    //登录次数
                VO.setLAST_LOGIN_DATE(rs.getTimestamp("LAST_LOGIN_DATE"));    //最后登录时间
                VO.setLAST_LOGIN_IP(rs.getString("LAST_LOGIN_IP"));    //最后登录IP
                VO.setQUESTION(rs.getString("QUESTION"));    //密码问题
                VO.setANSWER(rs.getString("ANSWER"));    //问题答案
                VO.setSTATE(rs.getInt("STATE"));    //用户状态
                VO.setAUDIT_STATE(rs.getInt("AUDIT_STATE"));    //审核状态
                VO.setUSER_NAME(rs.getString("USER_NAME"));    //
                VO.setEMAIL(rs.getString("EMAIL"));    //EMAIL
                VO.setFACE_URL(rs.getString("FACE_URL"));    //头像
                VO.setUSER_REMARK(rs.getString("USER_REMARK"));    //个人说明/签名
                VO.setREG_SOURCE(rs.getString("REG_SOURCE"));    //注册来源
                VO.setREG_VILIDATE_CODE(rs.getString("REG_VILIDATE_CODE"));    //注册验证码
                VO.setADD_USER_PATH(rs.getString("ADD_USER_PATH"));    //添加人路径
                VO.setMOBILE(rs.getString("MOBILE"));    //手机号
                VO.setQQ(rs.getString("QQ"));
                VO.setJOB(rs.getString("JOB"));
                VO.setUSER_CODE(rs.getString("USER_CODE"));

                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得用户集合", e);
        } finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            ResourceManager.close(conn);
        }
        return resultList;
    }

    /**
     * 根据ID取其VO
     *
     * @param UID
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public UserVO getUserByID(int UID) throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("USER");

        sh.setWhereForInt("UID", " = ", UID);//用户ID

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getUserColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (UserVO) it.next();
        }

        return null;
    }

    /**
     * 根据过滤条件取user
     *
     * @throws DataAccessException
     * @author pabula 2016-03-11 00:49:22
     */
    public List<UserVO> getUserByFilter(int serviceId, String filter) throws DataAccessException {
        List<UserVO> list = new ArrayList<>();
        SqlHelper sh = new SqlHelper();
        sh.setSelectColumn("*");
        sh.setTable("USER");
        sh.setWhereForInt("SERVICE_ID", " = ", serviceId);//用户ID
        if (!filter.equals("ALL")) {
            if (filter.equals("ALL_MEMBER")) {
                sh.setOrGroupForString("type", " = ", new String[]{"member", "creater"}, true);//用户ID
            } else if (filter.equals("ALL_USER")) {
                sh.setOrGroupForString("type", " = ", new String[]{"manager", "creater"}, true);//用户ID
            }
        }
        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getUserColl(sql);
        Iterator it = coll.iterator();
        while (it.hasNext()) {
            list.add((UserVO) it.next());
        }

        return list;
    }


    /**
     * 根据用户手机号获得用户
     *
     * @param phone
     * @return
     * @throws DataAccessException
     */

    public UserVO getUserByPhone(String phone) throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("USER");

        sh.setWhereForString("MOBILE", " = ", phone);//手机号

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getUserColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (UserVO) it.next();
        }

        return null;
    }

    /**
     * 根据LOGIN_ID取得用户
     *
     * @param loginID
     * @return
     * @throws DataAccessException
     */
    public UserVO getUserForLoginID(String loginID) throws DataAccessException {

        SqlHelper sh = new SqlHelper();

        sh.setSelectColumn("*");

        sh.setTable("USER");

        sh.setWhereForString("LOGIN_ID", " = ", loginID);//登录名

        String sql = sh.getSQL(sh.getSelectSQL());

        Collection coll = getUserColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (UserVO) it.next();
        }

        return null;
    }

    /**
     * 批量修改用户开通禁用状态
     */
    public boolean updateMemberState(int serviceId, String uids, int state) {
        try {
            SqlHelper sh = new SqlHelper();
            sh.setTable("USER");
            sh.setColumnForInt("STATE", state);//用户状态
            sh.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sh.setOrGroupForInt("UID", "=", uids, true);//id集合
            sh.update(ResourceManager.getConnection(), "修改用户state");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
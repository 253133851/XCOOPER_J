package com.jiaorder.sys.member.dao;

import com.jiaorder.sys.member.vo.MemberVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.DateUtil;
import com.pabula.common.util.ResourceManager;
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
 * 会员DAO
 *
 * @author pabula
 *         2016-03-11 00:52:50
 */
public class MemberDAO {

    /**
     * 添加会员
     *
     * @param VO
     * @author pabula 2016-03-11 00:52:50
     */
    public boolean addMember(MemberVO VO) {

        try {
            SqlHelper sh = new SqlHelper();
            sh.setTable("MEMBER");
            sh.setInsertForInt("MEMBER_ID", VO.getMEMBER_ID());//MEMBER_ID
            sh.setInsertForInt("UID", VO.getUID());//用户ID
            sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setInsertForString("USER_NAME", VO.getUSER_NAME());//姓名
            sh.setInsertForInt("MEMBER_LEVEL_ID", VO.getMEMBER_LEVEL_ID());//会员级别
            sh.setInsertForString("ROLE_ID_LIST", VO.getROLE_ID_LIST());//所属角色
            sh.setInsertForString("CLASS_ID_LIST", VO.getCLASS_ID_LIST());//所属地区
            sh.setInsertForString("USER_CDOE", VO.getUSER_CDOE());//编码
            sh.setInsertForString("JOB", VO.getJOB());//职位
            sh.setInsertForString("MOBILE", VO.getMOBILE());//手机
            sh.setInsertForString("EMAIL", VO.getEMAIL());//邮箱
            sh.setInsertForString("QQ", VO.getQQ());//QQ
            sh.setInsertForString("TEL", VO.getTEL());//固定电话
            sh.setInsertForString("FAX", VO.getFAX());//传真
            sh.setInsertForDatetime("BEGIN_DATETIME", DateUtil.format(VO.getBEGIN_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//开始时间
            sh.setInsertForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(), "yyyy-MM-dd HH:mm:ss"), true);//结束时间
            sh.setInsertForString("REMARK", VO.getREMARK());//备注
            sh.setInsertForString("ADDR_GUO", VO.getADDR_GUO());//所在地区_国家
            sh.setInsertForString("ADDR_SHENG", VO.getADDR_SHENG());//所在地区_省
            sh.setInsertForString("ADDR_SHI", VO.getADDR_SHI());//所在地区_市
            sh.setInsertForString("ADDR_XIAN", VO.getADDR_XIAN());//所在地区_县
            sh.setInsertForString("ADDR_QU", VO.getADDR_QU());//所在地区_区
            sh.setInsertForString("ADDR", VO.getADDR());//地址_详细地址
            sh.setInsertForString("ADDR_ZIP_CODE", VO.getADDR_ZIP_CODE());//地址_邮编
            sh.setInsertForString("ADDR_EXPRESS_CODE", VO.getADDR_EXPRESS_CODE());//地址_物流编码
            sh.setInsertForString("FINANCE_ACCOUNT_NAME", VO.getFINANCE_ACCOUNT_NAME());//财务_开户名称
            sh.setInsertForString("FINANCE_ACCOUNT_BANK", VO.getFINANCE_ACCOUNT_BANK());//财务_开户银行
            sh.setInsertForString("FINANCE_ACCOUNT_ID", VO.getFINANCE_ACCOUNT_ID());//财务_银行帐号
            sh.setInsertForString("FINANCE_INVOICE_TITLE", VO.getFINANCE_INVOICE_TITLE());//财务_发票抬头
            sh.setInsertForString("FINANCE_INVOICE_ID", VO.getFINANCE_INVOICE_ID());//财务_纳税人识别号
            sh.setInsertForString("FINANCE_INVOICE_ALIPAY", VO.getFINANCE_INVOICE_ALIPAY());//财务_支付宝
            sh.insert(ResourceManager.getConnection(), "添加会员");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改会员
     *
     * @param VO
     * @author pabula 2016-03-11 00:52:50
     */
    public boolean modifyMember(int serviceId, MemberVO VO) {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("MEMBER");
            sh.setColumnForInt("UID", VO.getUID());//用户ID
            sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
            sh.setColumnForString("USER_NAME", VO.getUSER_NAME());//姓名
            sh.setColumnForInt("MEMBER_LEVEL_ID", VO.getMEMBER_LEVEL_ID());//会员级别
            sh.setColumnForString("ROLE_ID_LIST", VO.getROLE_ID_LIST());//所属角色
            sh.setColumnForString("CLASS_ID_LIST", VO.getCLASS_ID_LIST());//所属地区
            sh.setColumnForString("USER_CDOE", VO.getUSER_CDOE());//编码
            sh.setColumnForString("JOB", VO.getJOB());//职位
            sh.setColumnForString("MOBILE", VO.getMOBILE());//手机
            sh.setColumnForString("EMAIL", VO.getEMAIL());//邮箱
            sh.setColumnForString("QQ", VO.getQQ());//QQ
            sh.setColumnForString("TEL", VO.getTEL());//固定电话
            sh.setColumnForString("FAX", VO.getFAX());//传真
            sh.setColumnForDatetime("BEGIN_DATETIME", DateUtil.format(VO.getBEGIN_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//开始时间
            sh.setColumnForDatetime("END_DATETIME", DateUtil.format(VO.getEND_DATETIME(), "yyyy-MM-dd HH:mm:ss"), "yyyy-mm-dd hh24:mi:ss", true);//结束时间
            sh.setColumnForString("REMARK", VO.getREMARK());//备注
            sh.setColumnForString("ADDR_GUO", VO.getADDR_GUO());//所在地区_国家
            sh.setColumnForString("ADDR_SHENG", VO.getADDR_SHENG());//所在地区_省
            sh.setColumnForString("ADDR_SHI", VO.getADDR_SHI());//所在地区_市
            sh.setColumnForString("ADDR_XIAN", VO.getADDR_XIAN());//所在地区_县
            sh.setColumnForString("ADDR_QU", VO.getADDR_QU());//所在地区_区
            sh.setColumnForString("ADDR", VO.getADDR());//地址_详细地址
            sh.setColumnForString("ADDR_ZIP_CODE", VO.getADDR_ZIP_CODE());//地址_邮编
            sh.setColumnForString("ADDR_EXPRESS_CODE", VO.getADDR_EXPRESS_CODE());//地址_物流编码
            sh.setColumnForString("FINANCE_ACCOUNT_NAME", VO.getFINANCE_ACCOUNT_NAME());//财务_开户名称
            sh.setColumnForString("FINANCE_ACCOUNT_BANK", VO.getFINANCE_ACCOUNT_BANK());//财务_开户银行
            sh.setColumnForString("FINANCE_ACCOUNT_ID", VO.getFINANCE_ACCOUNT_ID());//财务_银行帐号
            sh.setColumnForString("FINANCE_INVOICE_TITLE", VO.getFINANCE_INVOICE_TITLE());//财务_发票抬头
            sh.setColumnForString("FINANCE_INVOICE_ID", VO.getFINANCE_INVOICE_ID());//财务_纳税人识别号
            sh.setColumnForString("FINANCE_INVOICE_ALIPAY", VO.getFINANCE_INVOICE_ALIPAY());//财务_支付宝
            sh.setWhereForInt("MEMBER_ID", " = ", VO.getMEMBER_ID());//MEMBER_ID
            sh.setWhereForInt("SERVICE_ID", " = ", serviceId);//MEMBER_ID
            sh.update(ResourceManager.getConnection(), "修改会员");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除会员
     *
     * @param MEMBER_ID
     * @author pabula 2016-03-11 00:52:50
     */
    public boolean delMember(int serviceId, int MEMBER_ID) {

        try {
            SqlHelper sh = new SqlHelper();
            sh.setTable("MEMBER");
            sh.setWhereForInt("MEMBER_ID", " = ", MEMBER_ID);//MEMBER_ID
            sh.setWhereForInt("SERVICE_ID", " = ", serviceId);//MEMBER_ID
            sh.delete(ResourceManager.getConnection(), "删除会员");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 批量删除会员
     *
     * @param ids 主键集合，多个主键之间用半角逗号隔开
     * @author pabula 2016-03-11 00:52:50
     */
    public boolean delMember(int serviceId, String ids) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ResourceManager.getConnection();
            statement = connection.createStatement();
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("member");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setOrGroupForInt("MEMBER_ID", "=", ids, true);
            sqlHelper.delete(connection, "删除分类");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            ResourceManager.close(connection);
            ResourceManager.close(statement);
        }
        return false;
    }

    /**
     * 查询所有会员
     */
    public List<MemberVO> queryMember(int serviceId, String order, MemberVO filterMember) {
        List<MemberVO> MemberVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("member");
            sqlHelper.setLeftJoin("user", "member.UID =user.UID and member.SERVICE_ID=user.SERVICE_ID", true);
            sqlHelper.setLeftJoin("member_level", "member_level.MEMBER_LEVEL_ID =member.MEMBER_LEVEL_ID and member.SERVICE_ID=member_level.SERVICE_ID", true);
            sqlHelper.setWhereForInt("member.SERVICE_ID", " = ", serviceId);
            if (filterMember != null) {
                sqlHelper = filterSqlHelper(sqlHelper, filterMember, true);
            }
            sqlHelper.setORDER(order);//排序
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            MemberVOs = getMemberColl(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return MemberVOs;
    }

    /**
     * 过滤条件
     *
     * @param sqlHelper
     * @param filterMember
     * @return
     * @throws DataAccessException
     */
    public SqlHelper filterSqlHelper(SqlHelper sqlHelper, MemberVO filterMember, boolean withLimit) throws DataAccessException {
        if (filterMember.getSTATE() != -100&&filterMember.getSTATE() != 3) {
            sqlHelper.setWhereForInt("STATE", " = ", filterMember.getSTATE());
        }
        if (filterMember.getMEMBER_LEVEL_ID() != -100) {
            sqlHelper.setWhereForInt("member.MEMBER_LEVEL_ID", " = ", filterMember.getMEMBER_LEVEL_ID());
        }
        if(null!=filterMember.getUSER_NAME()&&!("").equals(filterMember.getUSER_NAME())){
            sqlHelper.setWhereForString("member.USER_NAME", " like ", "%"+filterMember.getUSER_NAME()+"%");
        }
        if (withLimit) {
            sqlHelper.setLIMIT((filterMember.getPageIndex() - 1) * filterMember.getPageSize(), filterMember.getPageSize());
        }
        return sqlHelper;
    }


    /**
     * 查询符合目标条件下的总条数
     */
    public int queryMember_count(int serviceId, MemberVO filterMember) {
        int size = 0;
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("count(member.UID) as SIZE ");
            sqlHelper.setTable("member");
            sqlHelper.setLeftJoin("user", "member.UID =user.UID and member.SERVICE_ID=user.SERVICE_ID", true);
            sqlHelper.setLeftJoin("member_level", "member_level.MEMBER_LEVEL_ID =member.MEMBER_LEVEL_ID and member.SERVICE_ID=member_level.SERVICE_ID", true);
            sqlHelper.setWhereForInt("member.SERVICE_ID", " = ", serviceId);
            if (filterMember != null) {
                sqlHelper = filterSqlHelper(sqlHelper, filterMember, false);
            }
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                conn = ResourceManager.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    size = rs.getInt("SIZE");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ResourceManager.close(rs);
                ResourceManager.close(stmt);
                ResourceManager.close(conn);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 根据SQL获取会员集合
     *
     * @param sql
     * @author pabula 2016-03-11 00:52:50
     */
    public List<MemberVO> getMemberColl(String sql) {

        List<MemberVO> resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MemberVO VO = new MemberVO();
                VO.setMEMBER_ID(rs.getInt("MEMBER_ID"));    //MEMBER_ID
                VO.setUID(rs.getInt("UID"));    //用户ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));    //SERVICE_ID
                VO.setUSER_NAME(rs.getString("USER_NAME"));    //姓名
                VO.setMEMBER_LEVEL_ID(rs.getInt("MEMBER_LEVEL_ID"));    //会员级别
                VO.setROLE_ID_LIST(rs.getString("ROLE_ID_LIST"));    //所属角色
                VO.setCLASS_ID_LIST(rs.getString("CLASS_ID_LIST"));    //所属地区
                VO.setUSER_CDOE(rs.getString("USER_CDOE"));    //编码
                VO.setJOB(rs.getString("JOB"));    //职位
                VO.setMOBILE(rs.getString("MOBILE"));    //手机
                VO.setEMAIL(rs.getString("EMAIL"));    //邮箱
                VO.setQQ(rs.getString("QQ"));    //QQ
                VO.setTEL(rs.getString("TEL"));    //固定电话
                VO.setFAX(rs.getString("FAX"));    //传真
                VO.setBEGIN_DATETIME(rs.getTimestamp("BEGIN_DATETIME"));    //开始时间
                VO.setEND_DATETIME(rs.getTimestamp("END_DATETIME"));    //结束时间
                VO.setREMARK(rs.getString("REMARK"));    //备注
                VO.setADDR_GUO(rs.getString("ADDR_GUO"));    //所在地区_国家
                VO.setADDR_SHENG(rs.getString("ADDR_SHENG"));    //所在地区_省
                VO.setADDR_SHI(rs.getString("ADDR_SHI"));    //所在地区_市
                VO.setADDR_XIAN(rs.getString("ADDR_XIAN"));    //所在地区_县
                VO.setADDR_QU(rs.getString("ADDR_QU"));    //所在地区_区
                VO.setADDR(rs.getString("ADDR"));          //地址_详细地址
                VO.setADDR_ZIP_CODE(rs.getString("ADDR_ZIP_CODE"));    //地址_邮编
                VO.setADDR_EXPRESS_CODE(rs.getString("ADDR_EXPRESS_CODE"));    //地址_物流编码
                VO.setFINANCE_ACCOUNT_NAME(rs.getString("FINANCE_ACCOUNT_NAME"));    //财务_开户名称
                VO.setFINANCE_ACCOUNT_BANK(rs.getString("FINANCE_ACCOUNT_BANK"));    //财务_开户银行
                VO.setFINANCE_ACCOUNT_ID(rs.getString("FINANCE_ACCOUNT_ID"));    //财务_银行帐号
                VO.setFINANCE_INVOICE_TITLE(rs.getString("FINANCE_INVOICE_TITLE"));    //财务_发票抬头
                VO.setFINANCE_INVOICE_ID(rs.getString("FINANCE_INVOICE_ID"));    //财务_纳税人识别号
                VO.setFINANCE_INVOICE_ALIPAY(rs.getString("FINANCE_INVOICE_ALIPAY"));    //财务_支付宝

                VO.setLOGIN_ID(rs.getString("LOGIN_ID"));    //登录ID
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
                VO.setLEVEL_NAME(rs.getString("LEVEL_NAME"));

                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
     * @param MEMBER_ID
     * @author pabula 2016-03-11 00:52:50
     */
    public MemberVO getMemberByID(int serviceId, int MEMBER_ID) {

        try {
            SqlHelper sh = new SqlHelper();
            sh.setSelectColumn("*");
            sh.setTable("MEMBER");
            sh.setWhereForInt("MEMBER_ID", " = ", MEMBER_ID);//MEMBER_ID
            sh.setWhereForInt("SERVICE_ID", " = ", serviceId);//MEMBER_ID
            String sql = sh.getSQL(sh.getSelectSQL());
            Collection coll = getMemberColl(sql);
            Iterator it = coll.iterator();
            if (it.hasNext()) {
                return (MemberVO) it.next();
            }
            return new MemberVO();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new MemberVO();
    }
}
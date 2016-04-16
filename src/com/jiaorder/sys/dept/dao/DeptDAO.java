package com.jiaorder.sys.dept.dao;

import com.jiaorder.shop.productclass.vo.ProductClassVO;
import com.jiaorder.sys.dept.vo.DeptVO;
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
 * 部门DAO
 *
 * @author cwq
 *         2016-03-28 10:38:31
 */
public class DeptDAO {

    /**
     * 添加部门
     *
     * @param VO
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public boolean addDept(DeptVO VO) throws DataAccessException {
        try {
            if (checkRepeat(VO)) {
                SqlHelper sh = new SqlHelper();
                sh.setTable("COMPANY_DEPT");
                sh.setInsertForInt("DEPT_ID", VO.getDEPT_ID());//DEPT_ID
                sh.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
                sh.setInsertForString("DEPT_NAME", VO.getDEPT_NAME());//名称 名称 NAME
                sh.setInsertForInt("PARENT_DEPT_ID", VO.getPARENT_DEPT_ID());//父部门
                sh.setInsertForInt("DEPTH", VO.getDEPTH());//深度
                sh.setInsertForString("ROOT_ID", VO.getROOT_ID());//根部门编号
                sh.setInsertForInt("IS_DEL", VO.getIS_DEL());//是否被删除
                sh.setInsertForString("PATH", VO.getPATH());//部门路径
                sh.setInsertForInt("STATE", VO.getSTATE());//状态
                sh.setInsertForInt("ORDER_NUM", VO.getORDER_NUM());//排序编号
                sh.insert(ResourceManager.getConnection(), "添加部门");
                return true;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return false;
    }


    /**
     * 判断重复数据 (同层级)
     *
     * @return
     */
    public boolean checkRepeat(DeptVO VO) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("COMPANY_DEPT");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", VO.getSERVICE_ID());
            sqlHelper.setWhereForInt("DEPT_ID", " != ", VO.getDEPT_ID());
            sqlHelper.setWhereForInt("PARENT_DEPT_ID", " = ", VO.getPARENT_DEPT_ID());//父分类名称
            sqlHelper.setWhereForString("DEPT_NAME", " = ", VO.getDEPT_NAME());//分类名称
            sqlHelper.setWhereForInt("IS_DEL", " != ", DeptVO.HAS_DELETED);//未被删除的
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<DeptVO> list = (List<DeptVO>) getDeptColl(sql);
            if (list.size() > 0) {
                return false;
            }
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改部门
     *
     * @param VO
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public void modifyDept(int serviceId, DeptVO VO) throws DataAccessException {
        try {
            if (checkRepeat(VO)) {
                SqlHelper sh = new SqlHelper();
                sh.setTable("COMPANY_DEPT");
                sh.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());//SERVICE_ID
                sh.setColumnForString("DEPT_NAME", VO.getDEPT_NAME());//名称 名称 NAME
                sh.setColumnForInt("PARENT_DEPT_ID", VO.getPARENT_DEPT_ID());//父部门
                sh.setColumnForInt("DEPTH", VO.getDEPTH());//深度
                sh.setColumnForString("ROOT_ID", VO.getROOT_ID());//根部门编号
                sh.setColumnForInt("IS_DEL", VO.getIS_DEL());//删除状态
                sh.setColumnForString("PATH", VO.getPATH());//部门路径
                sh.setColumnForInt("STATE", VO.getSTATE());//状态
                sh.setColumnForInt("ORDER_NUM", VO.getORDER_NUM());//排序编号
                sh.setWhereForInt("DEPT_ID", " = ", VO.getDEPT_ID());//DEPT_ID
                sh.setWhereForInt("SERVICE_ID", " = ", serviceId);//DEPT_ID
                sh.update(ResourceManager.getConnection(), "修改部门");
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除部门
     *
     * @param DEPT_ID
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public void delDept(int DEPT_ID) throws DataAccessException {

        try {
            SqlHelper sh = new SqlHelper();

            sh.setTable("COMPANY_DEPT");

            sh.setWhereForInt("DEPT_ID", " = ", DEPT_ID);//DEPT_ID

            sh.delete(ResourceManager.getConnection(), "删除部门");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 批量删除部门
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public void delDept(Collection coll) throws DataAccessException {
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
                sh.setTable("COMPANY_DEPT");
                //设置Where的条件
                sh.setWhereForInt("DEPT_ID", " = ", StrUtil.getNotNullIntValue(id[0]));//DEPT_ID
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
     * 根据SQL获取部门集合
     *
     * @param sql
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public Collection getDeptColl(String sql) throws DataAccessException {

        Collection resultList = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                DeptVO VO = new DeptVO();

                VO.setDEPT_ID(rs.getInt("DEPT_ID"));    //DEPT_ID
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));    //SERVICE_ID
                VO.setDEPT_NAME(rs.getString("DEPT_NAME"));    //名称 名称 NAME
                VO.setPARENT_DEPT_ID(rs.getInt("PARENT_DEPT_ID"));    //父部门
                VO.setDEPTH(rs.getInt("DEPTH"));    //深度
                VO.setROOT_ID(rs.getString("ROOT_ID"));    //根部门编号
                VO.setIS_DEL(rs.getInt("IS_DEL"));
                VO.setPATH(rs.getString("PATH"));    //部门路径
                VO.setSTATE(rs.getInt("STATE"));    //状态
                VO.setORDER_NUM(rs.getInt("ORDER_NUM"));    //排序编号

                resultList.add(VO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("DAO　Layer: 获得部门集合", e);
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
     * @param DEPT_ID
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public DeptVO getDeptByID(int serviceId, int DEPT_ID) throws DataAccessException {
        SqlHelper sh = new SqlHelper();
        sh.setSelectColumn("*");
        sh.setTable("COMPANY_DEPT");
        sh.setWhereForInt("DEPT_ID", " = ", DEPT_ID);//DEPT_ID
        sh.setWhereForInt("SERVICE_ID", " = ", serviceId);//DEPT_ID
        sh.setWhereForInt("IS_DEL", " != ", ProductClassVO.HAS_DELETED);//未被删除
        String sql = sh.getSQL(sh.getSelectSQL());
        Collection coll = getDeptColl(sql);
        Iterator it = coll.iterator();
        if (it.hasNext()) {
            return (DeptVO) it.next();
        }
        return null;
    }


    /**
     * 查询所有分类
     */
    public List<DeptVO> queryDept(int serviceId) {
        List<DeptVO> deptVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("COMPANY_DEPT");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("IS_DEL", " != ", DeptVO.HAS_DELETED);//未被删除
            sqlHelper.setORDER("ORDER_NUM");//按排序值倒序查询
            sqlHelper.setDESC("DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            deptVOs = (List<DeptVO>) getDeptColl(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return deptVOs;
    }

    /**
     * 查询该父类下所有子分类
     */
    public List<DeptVO> queryChlidProductClass(int serviceId, int PId) {
        List<DeptVO> deptVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("COMPANY_DEPT");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("PARENT_DEPT_ID", " = ", PId);
            sqlHelper.setWhereForInt("IS_DEL", " != ", DeptVO.HAS_DELETED);//未被删除
            sqlHelper.setORDER("ORDER_NUM");//按排序值倒序
            sqlHelper.setDESC("DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            deptVOs = (List<DeptVO>) getDeptColl(sql);
            if (deptVOs.size() > 0) {
                return deptVOs;
            }
            return new ArrayList<>();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
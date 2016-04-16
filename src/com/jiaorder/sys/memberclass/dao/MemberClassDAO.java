package com.jiaorder.sys.memberclass.dao;

import com.jiaorder.sys.memberclass.vo.MemberClassVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.fw.exception.DataAccessException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户分类(归属区)dao
 */
public class MemberClassDAO {

    /**
     * 新增分类
     *
     * @param VO
     */
    public boolean addMemberClass(MemberClassVO VO) {
        try {
            if (checkRepeatMemberClass(VO)) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("member_class");
                sqlHelper.setInsertForInt("MEMBER_CLASS_ID", VO.getMEMBER_CLASS_ID());
                sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setInsertForInt("CLASS_ORDER_NUM", VO.getCLASS_ORDER_NUM());//排序值
                sqlHelper.setInsertForInt("PARENT_CLASS_ID", VO.getPARENT_CLASS_ID());//父分类id
                sqlHelper.setInsertForInt("IS_DEFAULT", VO.getIS_DEFAULT());//是否为默认
                sqlHelper.setInsertForInt("IS_DEL", VO.getIS_DEL());//删除状态
                sqlHelper.setInsertForInt("STATE", VO.getSTATE());//状态
                sqlHelper.setInsertForString("CLASS_NAME", VO.getCLASS_NAME());//分类名
                sqlHelper.setInsertForString("CLASS_PATH", VO.getCLASS_PATH());//分类路径
                sqlHelper.insert(ResourceManager.getConnection(), "新增客户归属区");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 删除分类
     *
     * @param ids id之间用逗号分隔开
     */
    public void delMemberClass(String ids, int serviceId) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ResourceManager.getConnection();
            statement = connection.createStatement();
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("member_class");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setOrGroupForInt("MEMBER_CLASS_ID", "=", ids, true);
            sqlHelper.delete(connection, "删除分类");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        } finally {
            ResourceManager.close(connection);
            ResourceManager.close(statement);
        }
    }


    /**
     * 判断重复数据 (同层级)
     *
     * @return
     */
    public boolean checkRepeatMemberClass(MemberClassVO VO) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("member_class");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", VO.getSERVICE_ID());
            sqlHelper.setWhereForInt("MEMBER_CLASS_ID", " != ", VO.getMEMBER_CLASS_ID());//不是自己
            sqlHelper.setWhereForInt("PARENT_CLASS_ID", " = ", VO.getPARENT_CLASS_ID());//父分类id相同 同层级比较
            sqlHelper.setWhereForString("CLASS_NAME", " = ", VO.getCLASS_NAME());
            sqlHelper.setWhereForInt("IS_DEL", " != ", MemberClassVO.HAS_DELETED);//未被删除
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<MemberClassVO> list = getMemberClassColl(sql);
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
     * 更新分类
     *
     * @param VO
     */
    public boolean updateMemberClass(int serviceId, MemberClassVO VO) {
        try {
            if (checkRepeatMemberClass(VO)) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("member_class");
                sqlHelper.setColumnForInt("MEMBER_CLASS_ID", VO.getMEMBER_CLASS_ID());
                sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setColumnForInt("CLASS_ORDER_NUM", VO.getCLASS_ORDER_NUM());//排序值
                sqlHelper.setColumnForInt("PARENT_CLASS_ID", VO.getPARENT_CLASS_ID());//父分类id
                sqlHelper.setColumnForInt("IS_DEFAULT", VO.getIS_DEFAULT());//是否默认
                sqlHelper.setColumnForInt("IS_DEL", VO.getIS_DEL());//删除状态
                sqlHelper.setColumnForInt("STATE", VO.getSTATE());//状态
                sqlHelper.setColumnForString("CLASS_NAME", VO.getCLASS_NAME());//分类名
                sqlHelper.setColumnForString("CLASS_PATH", VO.getCLASS_PATH());//分类路径
                sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);//公司名
                sqlHelper.setWhereForInt("MEMBER_CLASS_ID", " = ", VO.getMEMBER_CLASS_ID());
                sqlHelper.update(ResourceManager.getConnection(), "修改商品分类");
                return true;
            }
            return false;
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有分类
     */
    public List<MemberClassVO> queryMemberClass(int serviceId) {
        List<MemberClassVO> MemberClassVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("member_class");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("IS_DEL", " != ", MemberClassVO.HAS_DELETED);//未被删除
            sqlHelper.setORDER("CLASS_ORDER_NUM");//排序值倒序
            sqlHelper.setDESC("DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            MemberClassVOs = getMemberClassColl(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return MemberClassVOs;
    }

    /**
     * id查询分类
     */
    public MemberClassVO queryMemberClassById(int serviceId, int MemberClassId) {
        List<MemberClassVO> MemberClassVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("member_class");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("MEMBER_CLASS_ID", " = ", MemberClassId);
            sqlHelper.setWhereForInt("IS_DEL", " != ", MemberClassVO.HAS_DELETED);//未被删除
            sqlHelper.setORDER("CLASS_ORDER_NUM");//排序值倒序
            sqlHelper.setDESC("DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            MemberClassVOs = getMemberClassColl(sql);
            if (MemberClassVOs.size() > 0) {
                return MemberClassVOs.get(0);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询该父类下所有子分类
     */
    public List<MemberClassVO> queryChlidMemberClass(int serviceId, int ParentClassId) {
        List<MemberClassVO> MemberClassVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("member_class");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("PARENT_CLASS_ID", " = ", ParentClassId);//父分类id
            sqlHelper.setWhereForInt("IS_DEL", " != ", MemberClassVO.HAS_DELETED);//未被删除
            sqlHelper.setORDER("CLASS_ORDER_NUM");//排序值倒序
            sqlHelper.setDESC("DESC");
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            MemberClassVOs = getMemberClassColl(sql);
            if (MemberClassVOs.size() > 0) {
                return MemberClassVOs;
            }
            return new ArrayList<>();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * 根据SQL获取商品分类集合
     *
     * @param sql
     */
    public List<MemberClassVO> getMemberClassColl(String sql) {
        List resultList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MemberClassVO VO = new MemberClassVO();
                VO.setMEMBER_CLASS_ID(rs.getInt("MEMBER_CLASS_ID"));
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
                VO.setCLASS_ORDER_NUM(rs.getInt("CLASS_ORDER_NUM"));//排序值
                VO.setPARENT_CLASS_ID(rs.getInt("PARENT_CLASS_ID"));//父分类id
                VO.setIS_DEFAULT(rs.getInt("IS_DEFAULT"));//是否为默认
                VO.setIS_DEL(rs.getInt("IS_DEL"));//删除状态
                VO.setSTATE(rs.getInt("STATE"));//状态
                VO.setCLASS_NAME(rs.getString("CLASS_NAME"));//分类名称
                VO.setCLASS_PATH(rs.getString("CLASS_PATH"));//分类路径
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

}

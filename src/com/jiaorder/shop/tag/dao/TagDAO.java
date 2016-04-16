package com.jiaorder.shop.tag.dao;

import com.jiaorder.shop.tag.vo.TagVO;
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
 * 标签dao
 */
public class TagDAO {

    /**
     * 新增标签
     *
     * @param VO
     */
    public boolean addTag(int serviceId, TagVO VO) {
        try {
            if (checkRepeatTag(serviceId, VO.getTAG())) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("SHOP_Tag");
                sqlHelper.setInsertForInt("Tag_ID", VO.getTAG_ID());
                sqlHelper.setInsertForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setInsertForString("Tag", VO.getTAG());//标签名
                sqlHelper.insert(ResourceManager.getConnection(), "新增标签");
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
     * 删除标签
     *
     * @param ids id之间用逗号分隔开
     */
    public boolean delTag(int serviceId, String ids) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ResourceManager.getConnection();
            statement = connection.createStatement();
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setTable("SHOP_Tag");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setOrGroupForInt("Tag_ID", "=", ids, true);
            sqlHelper.delete(connection, "删除标签");
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
     * 更新标签
     *
     * @param VO
     */
    public boolean updateTag(int serviceId, TagVO VO) {
        try {
            if (checkRepeatTag(serviceId, VO.getTAG())) {
                SqlHelper sqlHelper = new SqlHelper();
                sqlHelper.setTable("SHOP_Tag");
                sqlHelper.setColumnForInt("Tag_ID", VO.getTAG_ID());
                sqlHelper.setColumnForInt("SERVICE_ID", VO.getSERVICE_ID());
                sqlHelper.setColumnForString("tag", VO.getTAG());//标签
                sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);//公司id
                sqlHelper.setWhereForInt("Tag_ID", " = ", VO.getTAG_ID());//tagid
                sqlHelper.update(ResourceManager.getConnection(), "修改商品标签");
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
     * 判断重复数据
     *
     * @param serviceId
     * @param Tag
     * @return
     */
    public boolean checkRepeatTag(int serviceId, String Tag) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_Tag");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForString("Tag", " = ", Tag);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<TagVO> list = getTagColl(sql);
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
     * 用id查询标签
     */
    public TagVO queryTagById(int serviceId, int TagId) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_Tag");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            sqlHelper.setWhereForInt("Tag_ID", " = ", TagId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            List<TagVO> list = getTagColl(sql);
            if (list.size() > 0) {
                return list.get(0);
            }
            return new TagVO();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new TagVO();
    }

    /**
     * 查询所有标签
     */
    public List<TagVO> queryTag(int serviceId) {
        List<TagVO> TagVOs = new ArrayList<>();
        try {
            SqlHelper sqlHelper = new SqlHelper();
            sqlHelper.setSelectColumn("*");
            sqlHelper.setTable("SHOP_Tag");
            sqlHelper.setWhereForInt("SERVICE_ID", " = ", serviceId);
            String sql = sqlHelper.getSQL(sqlHelper.getSelectSQL());
            TagVOs = getTagColl(sql);
            if (TagVOs.size() > 0) {
                return TagVOs;
            }
            return new ArrayList<>();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 根据SQL获取标签集合
     *
     * @param sql
     */
    public List<TagVO> getTagColl(String sql) {
        List resultList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ResourceManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TagVO VO = new TagVO();
                VO.setTAG_ID(rs.getInt("Tag_ID"));
                VO.setSERVICE_ID(rs.getInt("SERVICE_ID"));
                VO.setTAG(rs.getString("Tag"));
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

package com.jiaorder.sys.dept.busi;

import com.jiaorder.sys.dept.dao.DeptDAO;
import com.jiaorder.sys.dept.vo.DeptVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * 部门Bean
 *
 * @author cwq
 *         2016-03-28 10:38:31
 */
public class DeptBean {

    Logger log = Logger.getLogger(DeptBean.class);
    DeptDAO dao;

    public DeptBean() {
        dao = new DeptDAO();
    }

    private static DeptBean bean = null;

    public static DeptBean newInstance() {
        if (bean == null) {
            synchronized (DeptBean.class) {
                if (bean == null) {
                    bean = new DeptBean();
                }
            }
        }
        return bean;
    }

    /**
     * 添加部门
     *
     * @param VO
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public boolean addDept(DeptVO VO) throws DataAccessException {
        //唯一ID
        if (VO.getDEPT_ID() < 1) {
            VO.setDEPT_ID(SeqNumHelper.getNewSeqNum("COMPANY_DEPT"));
        }
        //排序值默认为ID
        VO.setORDER_NUM(VO.getDEPT_ID());
        return dao.addDept(VO);
    }

    /**
     * 修改部门
     *
     * @param VO
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public void modifyDept(int serviceId,DeptVO VO) throws DataAccessException {
        dao.modifyDept(serviceId,VO);
    }

    /**
     * 删除部门
     *
     * @param DEPT_ID
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public void delDept(int DEPT_ID) throws DataAccessException {
        dao.delDept(DEPT_ID);
    }

    /**
     * 批量删除部门
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public void delDept(Collection coll) throws DataAccessException {
        dao.delDept(coll);
    }

    /**
     * 根据SQL获取部门集合
     *
     * @param sql
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public Collection getDeptColl(String sql) throws DataAccessException {
        return dao.getDeptColl(sql);
    }

    /**
     * 根据ID取其VO
     *
     * @param DEPT_ID
     * @throws DataAccessException
     * @author cwq 2016-03-28 10:38:31
     */
    public DeptVO getDeptByID(int serviceId, int DEPT_ID) throws DataAccessException {
        return dao.getDeptByID(serviceId, DEPT_ID);
    }

    /**
     * 更新部门
     * @return
     */
    public boolean updateDept(int serviceId, int deptId, int parentDeptId, String deptName) {
        DeptVO vo = null;
        try {
            vo = bean.getDeptByID(serviceId, deptId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        vo.setDEPT_NAME(deptName);
        vo.setPARENT_DEPT_ID(parentDeptId);
        try {
            dao.modifyDept(serviceId, vo);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除部门， 如果有子部门，不能删除
     *
     * @param serviceId
     * @return
     */
    public boolean delDeptAndChildsById(int serviceId, int Id) {
        if (dao.queryChlidProductClass(serviceId, Id).size() > 0) {
            return false;
        } else {
            //只改变状态 不真正删除
            DeptVO vo = null;
            try {
                vo = dao.getDeptByID(serviceId, Id);
                vo.setIS_DEL(DeptVO.HAS_DELETED);
                dao.modifyDept(serviceId, vo);
                return true;
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * 排序， 交换两个ProductClass 的 Class_Order_Num 字段的值，达到排序的效果
     * 没有验证两个是否在同一级别的，由前端控制
     *
     * @param serviceId
     * @param oneClssId
     * @param otherClassId
     * @return
     */
    public boolean changeDeptOrderNum(int serviceId, int oneClssId, int otherClassId) {
        DeptVO one = getDeptById(serviceId, oneClssId);
        DeptVO two = getDeptById(serviceId, otherClassId);
        if (one != null && two != null) {
            int sum = one.getORDER_NUM() + two.getORDER_NUM();
            String classIds = oneClssId + "," + otherClassId;
            try {
                SqlHelper sh = new SqlHelper();
                sh.setTable("COMPANY_DEPT");
                sh.setColumnForSQL("ORDER_NUM = " + sum + " - ORDER_NUM", true);
                sh.setWhereForInt("SERVICE_ID", "=", serviceId);
                sh.setOrGroupForInt("DEPT_ID", "=", classIds, true);
                sh.update(ResourceManager.getConnection(), "排序");
                return true;
            } catch (DataAccessException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 根据id得到部门vo
     *
     * @param serviceId
     * @return
     */
    public DeptVO getDeptById(int serviceId, int Id) {
        try {
            return dao.getDeptByID(serviceId, Id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return new DeptVO();
    }

    /**
     * 返回一个serviceID下面所有的部门
     *
     * @param servicId
     * @return 如果为空则返回空列表
     */
    public List<DeptVO> getDeptColl(int servicId) {
        List<DeptVO> list = dao.queryDept(servicId);
        return list;
    }

}
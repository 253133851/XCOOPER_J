package com.jiaorder.sys.memberclass.busi;

import com.jiaorder.shop.productclass.vo.ProductClassVO;
import com.jiaorder.sys.memberclass.dao.MemberClassDAO;
import com.jiaorder.sys.memberclass.vo.MemberClassVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;

import java.sql.SQLException;
import java.util.List;

/**
 * 客户分类(归属区)bean
 */
public class MemberClassBean {

    private MemberClassDAO dao = null;

    private MemberClassBean() {
        dao = new MemberClassDAO();
    }

    private static MemberClassBean bean = null;

    public static MemberClassBean newInstance() {
        if (bean == null) {
            synchronized (MemberClassBean.class) {
                if (bean == null) {
                    bean = new MemberClassBean();
                }
            }
        }
        return bean;
    }

    /**
     * 添加用户分类
     * @param vo
     * @return 执行的结果，执行成功 true，失败 false
     */
    public boolean addMemberClass(MemberClassVO vo) {
        try {
            //唯一用户分类id
            if (vo.getMEMBER_CLASS_ID() < 1) {
                vo.setMEMBER_CLASS_ID(SeqNumHelper.getNewSeqNum("member_class"));
            }
            vo.setCLASS_ORDER_NUM(vo.getMEMBER_CLASS_ID());

            return dao.addMemberClass(vo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过id得到用户分类
     * @param serviceId
     * @param classId
     * @return
     */
    public MemberClassVO getMemberClassByClassId(int serviceId, int classId) {
        return dao.queryMemberClassById(serviceId, classId);
    }

    /**
     * 更新用户分类
     * @param serviceId
     * @param classId
     * @param parentClassId
     * @param className
     * @return
     */
    public boolean updateMemberClass(int serviceId, int classId, int parentClassId, String className) {
        MemberClassVO vo = bean.getMemberClassByClassId(serviceId, classId);
        vo.setCLASS_NAME(className);
        vo.setPARENT_CLASS_ID(parentClassId);
        return dao.updateMemberClass(serviceId, vo);
    }

    /**
     * 返回一个serviceID下面所有的用户分类
     *
     * @param servicId
     * @return 如果为空则返回空列表
     */
    public List<MemberClassVO> getMemberClassColl(int servicId) {

        return  dao.queryMemberClass(servicId);
    }

    /**
     * 删除分类， 如果有子分类，不能删除
     *
     * @param serviceId
     * @param classId
     * @return
     */
    public boolean delMemberClassAndChildsByClassId(int serviceId, int classId) {
        //如果有子归属区就不能删除
        if (dao.queryChlidMemberClass(serviceId, classId).size() > 0) {
            return false;
        } else {
            MemberClassVO vo = dao.queryMemberClassById(serviceId, classId);
            vo.setIS_DEL(ProductClassVO.HAS_DELETED);//改变删除状态
            return dao.updateMemberClass(serviceId, vo);
        }
    }

    /**
     * 排序， 交换两个MemberClass 的 Class_Order_Num 字段的值，达到排序的效果
     * 没有验证两个是否在同一级别的，由前端控制
     *
     * @param serviceId
     * @param oneClssId
     * @param otherClassId
     * @return
     */
    public boolean changeMemberClassOrderNum(int serviceId, int oneClssId, int otherClassId) {
        MemberClassVO one = getMemberClassByClassId(serviceId, oneClssId);
        MemberClassVO two = getMemberClassByClassId(serviceId, otherClassId);
        if (one != null && two != null) {

            int sum = one.getCLASS_ORDER_NUM() + two.getCLASS_ORDER_NUM();

            String classIds = oneClssId + "," + otherClassId;
            try {
                SqlHelper sh = new SqlHelper();
                sh.setTable("member_class");
                sh.setColumnForSQL("CLASS_ORDER_NUM = " + sum + " - CLASS_ORDER_NUM", true);
                sh.setWhereForInt("SERVICE_ID", "=", serviceId);
                sh.setOrGroupForInt("MEMBER_CLASS_ID", "=", classIds, true);
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


}

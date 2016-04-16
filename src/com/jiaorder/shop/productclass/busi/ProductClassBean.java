package com.jiaorder.shop.productclass.busi;

import com.jiaorder.shop.productclass.dao.ProductClassDAO;
import com.jiaorder.shop.productclass.vo.ProductClassVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品分类bean
 */
public class ProductClassBean {

    private ProductClassDAO dao = null;

    private ProductClassBean() {
        dao = new ProductClassDAO();
    }

    private static ProductClassBean bean = null;

    public static ProductClassBean newInstance() {
        if (bean == null) {
            synchronized (ProductClassBean.class) {
                if (bean == null) {
                    bean = new ProductClassBean();
                }
            }
        }
        return bean;
    }

    /**
     * 添加商品分类
     *
     * @param vo
     * @return 执行的结果，执行成功 true，失败 false
     */
    public boolean addProductClass(ProductClassVO vo) {
        try {
            //唯一ID
            if (vo.getCLASS_ID() < 1) {
                vo.setCLASS_ID(SeqNumHelper.getNewSeqNum("shop_product_class"));
            }
            //排序值默认为ID
            vo.setCLASS_ORDER_NUM(vo.getCLASS_ID());
            return dao.addProductClass(vo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据id得到商品分类vo
     *
     * @param serviceId
     * @param classId
     * @return
     */
    public ProductClassVO getProductClassByClassId(int serviceId, int classId) {
        return dao.queryProductClassById(serviceId, classId);
    }

    /**
     * 更新商品分类
     *
     * @param serviceId
     * @param classId
     * @param parentClassId
     * @param className
     * @return
     */
    public boolean updateProductClass(int serviceId, int classId, int parentClassId, String className) {
        ProductClassVO vo = bean.getProductClassByClassId(serviceId, classId);
        vo.setCLASS_NAME(className);
        vo.setPARENT_CLASS_ID(parentClassId);
        return dao.updateProductClass(serviceId, vo);
    }

    /**
     * 返回一个serviceID下面所有的商品分类
     *
     * @param servicId
     * @return 如果为空则返回空列表
     */
    public List<ProductClassVO> getProductClassColl(int servicId) {
        List<ProductClassVO> list = dao.queryProductClass(servicId);
        return list;
    }

    /**
     * 删除分类， 如果有子分类，不能删除
     *
     * @param serviceId
     * @param classId
     * @return
     */
    public boolean delProductClassAndChildsByClassId(int serviceId, int classId) {
        if (dao.queryChlidProductClass(serviceId, classId).size() > 0) {
            return false;
        } else {
            //只改变状态 不真正删除
            ProductClassVO vo = dao.queryProductClassById(serviceId, classId);
            vo.setIS_DEL(ProductClassVO.HAS_DELETED);
            return dao.updateProductClass(serviceId, vo);
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
    public boolean changeProductClassOrderNum(int serviceId, int oneClssId, int otherClassId) {
        ProductClassVO one = getProductClassByClassId(serviceId, oneClssId);
        ProductClassVO two = getProductClassByClassId(serviceId, otherClassId);
        if (one != null && two != null) {
            int sum = one.getCLASS_ORDER_NUM() + two.getCLASS_ORDER_NUM();
            String classIds = oneClssId + "," + otherClassId;
            try {
                SqlHelper sh = new SqlHelper();
                sh.setTable("SHOP_PRODUCT_CLASS");
                sh.setColumnForSQL("CLASS_ORDER_NUM = " + sum + " - CLASS_ORDER_NUM", true);
                sh.setWhereForInt("SERVICE_ID", "=", serviceId);
                sh.setOrGroupForInt("CLASS_ID", "=", classIds, true);
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

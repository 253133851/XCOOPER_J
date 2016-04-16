package com.jiaorder.shop.brand.busi;

import com.jiaorder.shop.brand.dao.BrandDAO;
import com.jiaorder.shop.brand.vo.BrandVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;

import java.util.List;

/**
 * 品牌bean
 */
public class BrandBean {

    private BrandDAO dao = null;

    private static BrandBean bean = null;

    private BrandBean() {
        dao = new BrandDAO();
    }

    public static BrandBean newInstance() {
        if (bean == null) {
            synchronized (BrandBean.class) {
                if (bean == null) {
                    bean = new BrandBean();
                }
            }
        }
        return bean;
    }

    /**
     * 添加品牌
     *
     * @param vo
     * @return 执行的结果，执行成功 true，失败 false
     */
    public boolean addBrand(int serviceId, BrandVO vo) {
        try {
            if (vo.getBRAND_ID() < 1) {
                vo.setBRAND_ID(SeqNumHelper.getNewSeqNum("shop_Brand"));
            }
            return dao.addBrand(serviceId, vo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改品牌
     * @param servicId
     * @return 如果为空则返回空列表
     */
    public boolean modifyBrand(int servicId, int brandId, String brands) {
        BrandVO Brand = dao.queryBrandById(servicId, brandId);
        Brand.setBRAND(brands);
        return dao.updateBrand(servicId, Brand);
    }

    /**
     * 得到所有的品牌
     * @param serviceId
     * @return 如果为空则返回空列表
     */
    public List<BrandVO> getBrandColl(int serviceId) {
        return  dao.queryBrand(serviceId);
    }


    /**
     * 删除品牌
     * @param serviceId
     * @param classId
     * @return
     */
    public boolean delBrand(int serviceId, int classId) {
        return dao.delBrand(serviceId, classId + "");
    }


}

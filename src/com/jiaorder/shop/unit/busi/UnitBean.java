package com.jiaorder.shop.unit.busi;

import com.jiaorder.shop.unit.dao.UnitDAO;
import com.jiaorder.shop.unit.vo.UnitVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单位bean
 */
public class UnitBean {

  private UnitDAO dao = null;

  private UnitBean() {
    dao = new UnitDAO();
  }

  private static UnitBean bean = null;

  public static UnitBean newInstance() {
    if (bean == null) {
      synchronized (UnitBean.class) {
        if (bean == null) {
          bean = new UnitBean();
        }
      }
    }
    return bean;
  }

  /**
   * 添加单位
   *
   * @return 执行的结果，执行成功 true，失败 false
   */
  public boolean addUnit(int serviceId, UnitVO vo) {
    try {
      //唯一单位id
      if (vo.getUNIT_ID() < 1) {
        vo.setUNIT_ID(SeqNumHelper.getNewSeqNum("shop_unit"));
      }
      return dao.addUnit(serviceId, vo);
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * 返回一个serviceID下的所有单位
   *
   * @return 如果为空则返回空列表
   */
  public List<UnitVO> getUnitColl(int servicId) {

    return dao.queryUnit(servicId);
  }

  /**
   * 删除单位
   */
  public boolean delUnit(int serviceId, int classId) {

    return dao.delUnit(serviceId, classId + "");
  }

  /**
   * 把list转换成id 为key 的map
   */
  public Map<Integer, UnitVO> convertListToMap(List<UnitVO> list) {
    Map<Integer, UnitVO> unitMap = new HashMap<>();

    if (null == list) {
      return unitMap;
    }

    for (UnitVO unitVo : list) {
      unitMap.put(unitVo.getUNIT_ID(), unitVo);
    }

    return unitMap;
  }
}

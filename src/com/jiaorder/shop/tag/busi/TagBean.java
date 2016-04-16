package com.jiaorder.shop.tag.busi;

import com.jiaorder.shop.tag.dao.TagDAO;
import com.jiaorder.shop.tag.vo.TagVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;

import java.util.List;

/**
 * 标签bean
 */
public class TagBean {

    private TagDAO dao = null;

    private TagBean() {
        dao = new TagDAO();
    }

    private static TagBean bean = null;

    public static TagBean newInstance() {
        if (bean == null) {
            synchronized (TagBean.class) {
                if (bean == null) {
                    bean = new TagBean();
                }
            }
        }
        return bean;
    }

    /**
     * 添加标签
     *
     * @param vo
     * @return 执行的结果，执行成功 true，失败 false
     */
    public boolean addTag(int serviceId,TagVO vo) {
        try {
            //唯一标签
            if (vo.getTAG_ID() < 1) {
                vo.setTAG_ID(SeqNumHelper.getNewSeqNum("shop_Tag"));
            }
            return dao.addTag(serviceId,vo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 返回一个serviceID下的所有标签
     *
     * @param servicId
     * @return 如果为空则返回空列表
     */
    public boolean modifyTag(int servicId,int tagid,String tags) {
        TagVO tag = dao.queryTagById(servicId,tagid);
        tag.setTAG(tags);
        return dao.updateTag(servicId,tag);
    }

    /**
     * 返回一个serviceID下的所有标签
     *
     * @param servicId
     * @return 如果为空则返回空列表
     */
    public List<TagVO> getTagColl(int servicId) {
        return  dao.queryTag(servicId);
    }

  /**
   *
   * @param serviceId
   * @param tagId
   * @return
   */
    public TagVO getTagByTagId(int serviceId, int tagId) {
        return dao.queryTagById(serviceId, tagId);
    }


    /**
     * 删除单位
     *
     * @param serviceId
     * @param classId
     * @return
     */
    public boolean delTag(int serviceId, int classId) {
        return dao.delTag(serviceId, classId + "");
    }


}

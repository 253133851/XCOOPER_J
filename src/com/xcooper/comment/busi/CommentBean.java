package com.xcooper.comment.busi;

import com.pabula.common.util.DateUtil;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.fw.exception.DataAccessException;
import com.xcooper.comment.dao.CommentDAO;
import com.xcooper.comment.vo.CommentVO;
import org.apache.log4j.Logger;

import java.util.Collection;

/**
 * 讨论Bean
 *
 * @author zdk
 *         2016-03-28 19:33:56
 */
public class CommentBean {

    Logger log = Logger.getLogger(CommentBean.class);
    CommentDAO dao;

    public CommentBean() {
        dao = new CommentDAO();
    }

    /**
     * 添加讨论
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public void addComment(CommentVO VO) throws DataAccessException {
        VO.setADD_DATETIME(DateUtil.getCurrTime());
        System.out.println(DateUtil.getCurrTime());
        dao.addComment(VO);
    }

    /**
     * 修改讨论
     *
     * @param VO
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public void modifyComment(CommentVO VO) throws DataAccessException {
        VO.setUPDATE_DATETIME(DateUtil.getCurrTime());
        dao.modifyComment(VO);
    }

    /**
     * 删除讨论
     *
     * @param COMMENT_ID
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public void delComment(int COMMENT_ID) throws DataAccessException {
        dao.delComment(COMMENT_ID);
    }

    /**
     * 根据 AIM_ID 删除讨论
     *
     * @param AIM_ID
     * @throws DataAccessException
     * @author zdk 2016-04-30
     */
    public void delCommentByAimId(int AIM_ID,int TYPE) throws DataAccessException {
        dao.delCommentByAimId(AIM_ID,TYPE);
    }

    /**
     * 批量删除讨论
     *
     * @param coll 主键集合，多个主键之间用半角逗号隔开
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public void delComment(Collection coll) throws DataAccessException {
        dao.delComment(coll);
    }

    /**
     * 根据SQL获取讨论集合
     *
     * @param sql
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public Collection getCommentColl(String sql) throws DataAccessException {
        return dao.getCommentColl(sql);
    }

    /**
     * 根据ID取其VO
     *
     * @param COMMENT_ID
     * @throws DataAccessException
     * @author zdk 2016-03-28 19:33:56
     */
    public CommentVO getCommentByID(int COMMENT_ID) throws DataAccessException {
        return dao.getCommentByID(COMMENT_ID);
    }
}
package com.jiaorder.sys.file.busi;

import com.jiaorder.sys.file.dao.FileDAO;
import com.jiaorder.sys.file.vo.FileVO;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.ResourceManager;
import com.pabula.fw.exception.DataAccessException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * 附件表Bean
 *
 * @author zdk 2016-03-22 10:22:34
 */
public class FileBean {

  Logger log = Logger.getLogger(FileBean.class);
  FileDAO dao;

  private FileBean() {
    dao = new FileDAO();
  }

  private static FileBean bean;

  public static FileBean newInstance() {

    if (null == bean) {
      synchronized (FileBean.class) {
        if (null == bean) {
          bean = new FileBean();
        }
      }
    }
    return bean;
  }

  /**
   * 添加附件表
   *
   * @throws DataAccessException
   * @author zdk 2016-03-22 10:22:34
   */
  public void addFile(FileVO VO) throws DataAccessException {
    dao.addFile(VO);
  }

  /**
   * 批量添加
   * @param uploadFileList
   */
  public void addFile(List<FileVO> uploadFileList) throws DataAccessException{
    for (FileVO file: uploadFileList) {
      addFile(file);
    }
  }

  /**
   * 修改附件表
   *
   * @throws DataAccessException
   * @author zdk 2016-03-22 10:22:34
   */
  public void modifyFile(FileVO VO) throws DataAccessException {
    dao.modifyFile(VO);
  }

  /**
   * 删除附件表
   *
   * @throws DataAccessException
   * @author zdk 2016-03-22 10:22:34
   */
  public void delFile(int ORDER_FILE_ID) throws DataAccessException {
    dao.delFile(ORDER_FILE_ID);
  }

  /**
   * 批量删除附件表
   *
   * @param coll 主键集合，多个主键之间用半角逗号隔开
   * @throws DataAccessException
   * @author zdk 2016-03-22 10:22:34
   */
  public void delFile(Collection coll) throws DataAccessException {
    dao.delFile(coll);
  }

  /**
   * 根据SQL获取附件表集合
   *
   * @throws DataAccessException
   * @author zdk 2016-03-22 10:22:34
   */
  public List<FileVO> getFileColl(String sql) throws DataAccessException {
    return dao.getFileColl(sql);
  }

  /**
   * 根据ID取其VO
   *
   * @throws DataAccessException
   * @author zdk 2016-03-22 10:22:34
   */
  public FileVO getFileByID(int ORDER_FILE_ID) throws DataAccessException {
    return dao.getFileByID(ORDER_FILE_ID);
  }

  /**
   * 根据目标id，目标类型获取该目标所有的附件
   *
   * @throws DataAccessException
   */
  public List<FileVO> getFileCollByTargetIdAndType(int serviceId, int targetId, String type)
      throws DataAccessException {

    SqlHelper sh = new SqlHelper();

    sh.setTable("FILE");
    sh.setSelectColumn("*");
    sh.setWhereForInt("SERVICE_ID", "=", serviceId);
    sh.setWhereForInt("TARGET_ID", "=", targetId);

    sh.setWhereForString("TYPE", "=", type);

    String sql = sh.getSQL(sh.getSelectSQL());

    return getFileColl(sql);
  }

  /**
   * 删除目标id下面所有的附件
   *
   * @param serviceId
   * @param targetId
   * @param type
   * @throws DataAccessException
   */
  public void delFileByTargetIdAndType(int serviceId, int targetId, String type) throws DataAccessException {
    try {
      SqlHelper sh = new SqlHelper();

      sh.setTable("FILE");

      sh.setWhereForInt("SERVICE_ID", "=", serviceId);
      sh.setWhereForInt("TARGET_ID", "=", targetId);
      sh.setWhereForString("TYPE", "=", type);

      sh.delete(ResourceManager.getConnection(), "删除一个目标id下面的所有资源，用于更新资源");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException(e);
    }
  }


}
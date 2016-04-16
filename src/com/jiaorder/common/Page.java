package com.jiaorder.common;

import com.pabula.common.db.SqlHelper;
import com.pabula.common.util.JsonResultUtil;
import com.pabula.common.util.ResourceManager;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sunsai on 2016/2/23.
 */
public class Page<T> {


  /**
   * 调用 空构造,需要手工 setPageSize 和 setCurrentPage
   */
  public Page(){

  }


  /**
   * 初始化分页控件,接受全新的为分页专门服务的 SqlHelper
   * @param request 请求
   * @param items 已经查询出来的结果集
   * @param sh  SqlHelper查询对象,专门负责分页的查询
   * @throws DataAccessException
     */
  public Page (HttpServletRequest request,List<T> items,SqlHelper sh) throws DataAccessException {
    init(request,items,sh);
  }


  /**
   * 初始化分页控件,接收一个新的select column信息
   * @param request 请求
   * @param items 已经查询出来的结果集
   * @param sh SqlHelper查询对象
   * @param selectColumSql  新的SELECT,通常为 "COUNT(*) AS COUNT"
   * @throws DataAccessException
     */
  public Page (HttpServletRequest request, List<T> items, SqlHelper sh, String selectColumSql) throws DataAccessException {
      //移除原limit参数
      sh.removePAGE();

      //设置新的SELECT,通常为 "COUNT(*) AS COUNT"
      sh.setSelectColumn(selectColumSql);

      init(request,items,sh);
  }


    /**
     * 初始化分页数据
     * @param request
     * @param items
     * @param sh
     * @throws DataAccessException
     */
  private void init(HttpServletRequest request,List<T> items,SqlHelper sh) throws DataAccessException {
    //数据库里符合条件的数据总数(初始为当前结果集的数量)
    int allRowCount = items.size();

    if(allRowCount>0){  //如果要分页,并且当前结果集的数量大于0

      try {
        allRowCount = sh.selectAndGetIntValue(ResourceManager.getConnection(),"COUNT","获得总数量");
      } catch (SQLException e) {
        e.printStackTrace();
        throw new DataAccessException(e);
      } catch (DataAccessException e) {
        e.printStackTrace();
        throw new DataAccessException(e);
      }
    }


    setPageSize(StrUtil.getNotNullIntValue(request.getParameter("pageSize"), 10));  //分页大小
    setCurrentPage(StrUtil.getNotNullIntValue(request.getParameter("pageIndex"), 1));  //当前页码
    setItems(items);  //当前数据集
    setTotalCount(allRowCount); //总数据量
  }


  /**
   * 返回PAGE的JSON数据
   * @return
     */
  public String json(){
    return JsonResultUtil.instance().addData(this).json();
  }


  private int currentPage;//必须
  private int totalCount;//必须
  private int pageSize = 10;//
  private List<T> items;//必须
  private String queryString;
  private String queryURL;


  /**
   *
   */
  private int firstPage = 1;
  private boolean hasNextPage;
  private boolean hasPreviousPage;
  private int lastPage;
  private int nextPage;
  private int offset;

  private int previousPage;
  private int totalPage;

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getFirstPage() {
    return firstPage;
  }


  public boolean isHasNextPage() {
    return currentPage < getLastPage();
  }

  public void setHasNextPage(boolean hasNextPage) {
    this.hasNextPage = hasNextPage;
  }

  public boolean isHasPreviousPage() {
    return currentPage > firstPage ;
  }



  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  public int getLastPage() {
    return getTotalPage();
  }


  public int getNextPage() {
    return currentPage + 1;
  }


  public int getOffset() {
    return offset;
  }


  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize < 1 ? 10 : pageSize;
  }

  public int getPreviousPage() {
    return  currentPage  < 2 ? 1 : currentPage - 1;
  }


  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  public String getQueryURL() {
    return queryURL;
  }

  public void setQueryURL(String queryURL) {
    this.queryURL = queryURL;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getTotalPage() {
    int tmp = getTotalCount() / getPageSize();
    return tmp * getPageSize() == getTotalCount() ? tmp : tmp + 1;
  }
}

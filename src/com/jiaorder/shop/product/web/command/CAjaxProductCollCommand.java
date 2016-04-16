package com.jiaorder.shop.product.web.command;

import com.jiaorder.common.Page;
import com.jiaorder.shop.product.busi.ProductBean;
import com.jiaorder.shop.product.vo.ProductJson;
import com.jiaorder.shop.tag.busi.TagBean;
import com.jiaorder.shop.tag.vo.TagVO;
import com.jiaorder.sys.login.UserHelper;
import com.pabula.common.db.SqlHelper;
import com.pabula.common.logger.Logger;
import com.pabula.common.util.StrUtil;
import com.pabula.common.util.ValidateUtil;
import com.pabula.fw.exception.DataAccessException;
import com.pabula.fw.utility.Command;
import com.pabula.fw.utility.RequestHelper;
import com.pabula.fw.utility.VO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sunsai on 2016/3/7.
 */
public class CAjaxProductCollCommand implements Command {
  @Override public String execute(RequestHelper helper, HttpServletRequest request)
      throws DataAccessException {

    int serviceId = UserHelper.getServiceID(request);

    /**
     * 拼装SqlHelper
     */
    SqlHelper sh = createSqlHelperByRequest(serviceId, request);

    String sql = sh.getSQL(sh.getSelectSQL());

    Logger.d("sql : " + sql);

    List<ProductJson> resultList =
        ProductBean.newInstance().getProductJsonCollBySql(serviceId, sql);

    return new Page<>(request, resultList, sh, "count(DISTINCT SHOP_PRODUCT.PRD_ID) COUNT").json();
  }

  @Override public void validate(HttpServletRequest request, VO vo, ValidateUtil validate) {

  }

  private SqlHelper createSqlHelperByRequest(int serviceId, HttpServletRequest request)
      throws DataAccessException {

    int classId = StrUtil.getNotNullIntValue(request.getParameter("classId"), -1);
    int isSale = StrUtil.getNotNullIntValue(request.getParameter("isSale"), ProductBean.SALE_STATE_ALL);
    int tagId = StrUtil.getNotNullIntValue(request.getParameter("tagId"), 0);

    String search = request.getParameter("search");

    String orderState = request.getParameter("orderState");

    Logger.d("orderState = " + orderState);

    SqlHelper sh = new SqlHelper();

    sh.setTable("SHOP_PRODUCT");
    sh.setSelectColumn("DISTINCT SHOP_PRODUCT.*");
    sh.setWhereForInt("SHOP_PRODUCT.SERVICE_ID", "=", serviceId);

    if (classId > 0) {
      sh.setWhereForInt("SHOP_PRODUCT.CLASS_ID", "=", classId);
    }

    if (isSale != ProductBean.SALE_STATE_ALL) {
      sh.setWhereForInt("SHOP_PRODUCT.IS_SALE", "=", isSale);
    }

    if (tagId != 0) {

      TagVO tag = TagBean.newInstance().getTagByTagId(serviceId, tagId);
      if (null != tag) {
        String[] tagFilter = new String[5];
        tagFilter[0] = "SHOP_PRODUCT.TAG1 = \"" + tag.getTAG() + "\"";
        tagFilter[1] = "SHOP_PRODUCT.TAG2 = \"" + tag.getTAG() + "\"";
        tagFilter[2] = "SHOP_PRODUCT.TAG3 = \"" + tag.getTAG() + "\"";
        tagFilter[3] = "SHOP_PRODUCT.TAG4 = \"" + tag.getTAG() + "\"";
        tagFilter[4] = "SHOP_PRODUCT.TAG5 = \"" + tag.getTAG() + "\"";
        sh.setOrGroupForString(tagFilter, true);
      }
    }

    sh.setWhereForInt("SHOP_PRODUCT.IS_DEL", "=", ProductBean.NOT_DELETE);

    if (StrUtil.isNotNull(search)) {

      /**
       * 从前台传过来的查询使用了urlencode编码，因此这里需要转换一下
       */
      search = StrUtil.decodeURI(search, "utf8");

      //左连接
      sh.setLeftJoin("SHOP_PRODUCT_SKU",
          "SHOP_PRODUCT.SERVICE_ID = SHOP_PRODUCT_SKU.SERVICE_ID AND SHOP_PRODUCT.PRD_ID = SHOP_PRODUCT_SKU.PRD_ID",
          true);
      String[] searchFilter = new String[4];
      searchFilter[0] = "SHOP_PRODUCT.PRD_NAME like \"%" + search + "%\"";
      searchFilter[1] = "SHOP_PRODUCT.PRD_OTHER_NAME like \"%" + search + "%\"";
      searchFilter[2] = "SHOP_PRODUCT.PRD_SPU like \"%" + search + "%\"";
      searchFilter[3] = "SHOP_PRODUCT_SKU.BAR_CODE like \"%" + search + "%\"";
      sh.setOrGroupForString(searchFilter, true);
    }

    sh.setORDER(orderState);

    sh.setPAGE(request);

    return sh;
  }
}

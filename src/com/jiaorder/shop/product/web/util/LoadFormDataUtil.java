package com.jiaorder.shop.product.web.util;

import com.jiaorder.shop.price.vo.ProductPriceMemberLevelVO;
import com.jiaorder.shop.product.vo.ProductVO;
import com.jiaorder.sys.file.FileTye;
import com.jiaorder.sys.file.vo.FileVO;
import com.pabula.common.util.SeqNumHelper;
import com.pabula.common.util.StrUtil;
import com.pabula.fw.exception.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunsai on 2016/3/22 - 17:59.
 */
public class LoadFormDataUtil {

  public static List<ProductPriceMemberLevelVO> getProductPriceMemberLevelList(int serviceId,
      HttpServletRequest request) {
    List<ProductPriceMemberLevelVO> result = new ArrayList<>();

    int memberLevelPriceListLength =
        StrUtil.getNotNullIntValue(request.getParameter("memberLevelPriceListLength"));

    for (int i = 0; i < memberLevelPriceListLength; i++) {
      ProductPriceMemberLevelVO a = new ProductPriceMemberLevelVO();

      a.setCAN_BUY(StrUtil.getNotNullIntValue(
          request.getParameter("memberLevelPriceList[" + i + "][canBuy]")));
      a.setMEMBER_LEVEL_ID(StrUtil.getNotNullIntValue(
          request.getParameter("memberLevelPriceList[" + i + "][memberLevelId]")));
      a.setMIN_NUM(StrUtil.getNotNullIntValue(
          request.getParameter("memberLevelPriceList[" + i + "][minNum]")));
      a.setPRICE(StrUtil.convertToIntMoney(
          request.getParameter("memberLevelPriceList[" + i + "][price]")));
      a.setSERVICE_ID(serviceId);

      result.add(a);
    }

    return result;
  }

  public static List<FileVO> getFileListByRequest(int serviceId, int prdId, HttpServletRequest request) throws
      DataAccessException{
    int length = StrUtil.getNotNullIntValue(request.getParameter("fileUrlLength"), 0);

    List<FileVO> fileList = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      FileVO file = new FileVO();

      file.setFILE_NAME(request.getParameter("fileUrls[" + i + "][original]"));
      file.setFILE_PATH(request.getParameter("fileUrls[" + i + "][url]"));
      file.setSIZE(StrUtil.getNotNullIntValue(request.getParameter("fileUrls[" + i + "][size]")));
      file.setFILE_TYPE(request.getParameter("fileUrls[" + i + "][type]"));
      file.setSERVICE_ID(serviceId);
      file.setTARGET_ID(prdId);
      file.setTYPE(FileTye.PRODUCT);
      file.setFILE_ID(SeqNumHelper.getNewSeqNum("FILE"));

      fileList.add(file);
    }

    return fileList;
  }

  /**
   * 设置商品的 标签字段的值 同样屋里
   */
  public static void setProductTagAttrs(ProductVO product, String[] tagNames) {
    final int productTagCount = 5;
    if (null == tagNames) {
      tagNames = new String[0];
    }
    for (int i = 0; i < productTagCount; i++) {
      String tagName = "";
      if (i < tagNames.length) {
        tagName = tagNames[i];
      }
      switch (i) {
        case 0:
          product.setTAG1(tagName);
          break;
        case 1:
          product.setTAG2(tagName);
          break;
        case 2:
          product.setTAG3(tagName);
          break;
        case 3:
          product.setTAG4(tagName);
          break;
        case 4:
          product.setTAG5(tagName);
          break;
        default:
          break;
      }
    }
  }

  public static void setProductAdImgUrlAttr(ProductVO product, String[] uploadImageUrls) {

    if (null == uploadImageUrls) {
      uploadImageUrls = new String[0];
    }

    final int productAdImgCount = 10;
    for (int i = 0; i < productAdImgCount; i++) {

      String url = "";
      if (i < uploadImageUrls.length) {
        url = uploadImageUrls[i];
      }

      switch (i) {
        case 0: product.setAD_IMG1(url);break;
        case 1:
          product.setAD_IMG2(url);
          break;
        case 2:
          product.setAD_IMG3(url);
          break;
        case 3:
          product.setAD_IMG4(url);
          break;
        case 4:
          product.setAD_IMG5(url);
          break;
        case 5:
          product.setAD_IMG6(url);
          break;
        case 6:
          product.setAD_IMG7(url);
          break;
        case 7:
          product.setAD_IMG8(url);
          break;
        case 8:
          product.setAD_IMG9(url);
          break;
        case 9:
          product.setAD_IMG10(url);
          break;
        default:
          break;
      }
    }
  }

  public static void setProductGenerAttrForAddOrModifyByRequest(ProductVO product,
      HttpServletRequest request) {

    product.setCLASS_ID(StrUtil.getNotNullIntValue(request.getParameter("classId"), 0));
    product.setUNIT_ID(StrUtil.getNotNullIntValue(request.getParameter("unitId"), 0));
    product.setBRAND_ID(StrUtil.getNotNullIntValue(request.getParameter("brandId"), 0));
    product.setPRD_NAME(request.getParameter("prdName"));
    product.setPRD_OTHER_NAME(request.getParameter("prdName"));
    product.setORDER_NUM(StrUtil.getNotNullIntValue(request.getParameter("orderNum"), 0));
    product.setKEYWORD(request.getParameter("keyword"));
    product.setIS_SALE(StrUtil.getNotNullIntValue(request.getParameter("isSale"), 1));

    //设置图片属性
    setProductAdImgUrlAttr(product, request.getParameterValues("imageUrls[]"));

    //设置标签属性
    setProductTagAttrs(product, request.getParameterValues("tagNames[]"));
  }
}

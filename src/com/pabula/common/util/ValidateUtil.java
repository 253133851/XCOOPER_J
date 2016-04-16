/*
 * 创建于 2004-11-7 22:03:12
 * JCMS
 */
package com.pabula.common.util;
import java.util.ArrayList;

/**
 * @author Dekn
 * 
 * 数据检验类，并且可以构造错误消息LIST
 * 
 * JCMS ( Content Manager System for java )
 */
public class ValidateUtil {
    
    ArrayList errorList;
    
    public ValidateUtil(){
        errorList = new ArrayList();
        this.clear();
    }
    
    /**
     * 是否为NULL
     * @param data　要检查的数据
     * @return
     */
    public boolean isNull(String data){
        boolean isNull = false;
        if(data == null){
            isNull = true;
        }
        return isNull;
    }
    
    /**
     * 是否为空
     * @param data
     * @return
     */
    public boolean isEmpty(String data){
        boolean isNull = false;
        if(data.equals("")){
            isNull = true;
        }
        return isNull;
    }
    
    /**
     * 是否为空或NULL
     * @param data
     * @return
     */
    public boolean isEmptyOrNull(String data){
        boolean isNull = false;
        if(data == null || data.trim().equals("")){
            isNull = true;
        }
        return isNull;
    }
    
    
    /**
     * 检验是否为NULL，并且提供错误信息
     * @param data　要检查的数据
     * @param errMsg　如果为空时，提示的错误信息
     * @return
     */
    public String validateIsNull(String data,String errMsg){
        boolean isNull = this.isNull(data);
        if(isNull){
            if (errMsg != null || !errMsg.equals("")){
                this.errorList.add(errMsg);
            }
        }
        return data; 
    }
    
    /**
     * 检验是否为空，并且提供错误信息
     * @param data　要检查的数据
     * @param errMsg　如果为空时，提示的错误信息
     * @return
     */    
    public String validateIsEmpty(String data,String errMsg){
        boolean isEmpty = this.isEmpty(data);
        if(isEmpty){
            if (errMsg != null || !errMsg.equals("")){
                this.errorList.add(errMsg);
            }
        }
        return data; 
    }
    
    
    /**
     * 检验是否为NULL或空，并且提供错误信息
     * @param data　要检查的数据
     * @param errMsg　如果为空时，提示的错误信息
     * @return
     */   
    public String validateIsEmptyOrNull(String data,String errMsg){
        boolean isEmptyOrNull = this.isEmptyOrNull(data);
        if(isEmptyOrNull){
            if (errMsg != null || !errMsg.equals("")){
                this.errorList.add(errMsg);
            }
        }
        return data; 
    }
    
    /**
     * 检验数据是不是数字
     * @param data 要检验的数据
     * @param errMsg 如果不是数字，提供的错误信息
     * @return
     */
    public int validateIsNumber(String data,String errMsg){
        int number = 0;
        
        try {
            number = Integer.parseInt(data); 	
            //if (number < 0){
            //    this.errorList.add(errMsg);
           // }
        }catch (NumberFormatException e){
            this.errorList.add(errMsg);
        }

        return number;
    }
    
    /**
     * 检验数据是不是大于指定的数
     * @param data 要检验的数据
     * @param errMsg 如果大于数字，提供的错误信息
     * @return
     */
    public int validateMore(String data,int more,String errMsg){
        int number = 0;
        
        try {
            number = Integer.parseInt(data); 	
            if (number > more){
                this.errorList.add(errMsg);
            }
        }catch (NumberFormatException e){            
        }

        return number;
    }
    
    /**
     * 检验数据是不是大于指定的数
     * @param data 要检验的数据
     * @param errMsg 如果大于数字，提供的错误信息
     * @return
     */
    public int validateLess(String data,int less,String errMsg){
        int number = 0;
        
        try {
            number = Integer.parseInt(data); 	
            if (number < less){
                this.errorList.add(errMsg);
            }
        }catch (NumberFormatException e){            
        }

        return number;
    }
    
    /**
     * 是否有错误产生
     * @return
     */
    public boolean hasError(){
        if (errorList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 向错误list中添加一个错误信息
     * @param errMsg 待添加的错误信息
     * @return
     */
    public boolean addError(String errMsg){
        return this.errorList.add(errMsg);
    }
    
    /**
     * 取得产生的所有错误
     * @return
     */
    public ArrayList getErrors(){
        return this.errorList;
    }
    
    public void clear(){
        this.errorList.clear();
    }
    
    
}

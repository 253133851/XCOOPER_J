/*
 * ´´½¨ÓÚ 2004-10-30 21:08:29
 * JCMS
 */
package com.pabula.fw.exception;

/**
 * @author Dekn
 *
 * JCMS ( Content Manager System for java )
 */
public class DataVerifyException extends UniApplicationException {

    /**
    *
    */
   public DataVerifyException() {
           super();
           // TODO Auto-generated constructor stub
   }

   /**
    * @param message
    */
   public DataVerifyException(String message) {
           super(message);
           // TODO Auto-generated constructor stub
   }

   /**
    * @param cause
    */
   public DataVerifyException(Throwable cause) {
           super(cause);
           // TODO Auto-generated constructor stub
   }

   /**
    * @param message
    * @param cause
    */
   public DataVerifyException(String message, Throwable cause) {
           super(message, cause);
           // TODO Auto-generated constructor stub
   }    
    
    /* (non-Javadoc)
     * @see cn.com.dekn.cms.framework.exception.UniApplicationException#getRealm()
     */
    public String getRealm() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see cn.com.dekn.cms.framework.exception.UniApplicationException#getDefine()
     */
    public String getDefine() {
        // TODO Auto-generated method stub
        return null;
    }

}

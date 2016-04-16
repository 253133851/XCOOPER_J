/*
 * 创建于 2004-10-18 21:03:56
 * JCMS
 */
package com.pabula.fw.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author Dekn
 *
 * JCMS ( Content Manager System for java )
 */
public abstract class UniApplicationException extends Exception  {
    Throwable cause = null;

    /**
     *
     */
    public UniApplicationException() {
            super();
    }

    /**
     * @param message
     */
    public UniApplicationException(String message) {
            super(message);
    }

    /**
     * @param cause
     */
    public UniApplicationException(Throwable cause) {
            super(cause);
            this.cause = cause;
    }

    /**
     * @param message
     * @param cause
     */
    public UniApplicationException(String message, Throwable cause) {
            super(message, cause);
            this.cause = cause;
    }

    /**
     * @return
     */
    public Throwable getCause() {
            return cause;
    }

    /**
     * 获取异常信息，包括被嵌套异常，则输出被嵌套的toString()值
     * (non-Javadoc)
     * @see Throwable#getMessage()
     */
    public String getMessage() {
            StringBuffer sb = new StringBuffer(super.getMessage());
            if (null != cause) {
                    sb.append("; nested exception is:");
                    sb.append(System.getProperty("line.separator"));
                    sb.append(cause.toString());
            }

            return sb.toString();
    }

    /**
     * 将异常内容(异常类型和异常信息)转换成串
     * (non-Javadoc)
     * @see Object#toString()
     */
    public String toString() {
            return getClass().getName() + ": " + this.getMessage();
    }

    /**
     * 递归输出堆栈跟踪内容
     * (non-Javadoc)
     * @see Throwable#printStackTrace()
     */
    public void printStackTrace() {
            System.err.println(toString());
            if (null != cause) {
                    cause.printStackTrace();
            }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * *
     * getMessage(),toString(),printStackTrace
     * 每类信息都是前一类信息的一个子集
     * * * * * * * * * * * * * * * * * * * * * * * * */

    /* (non-Javadoc)
     * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
     */
    public void printStackTrace(PrintStream ps) {
            ps.println(toString());
            if (null != cause) {
                    cause.printStackTrace(ps);
            }
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
     */
    public void printStackTrace(PrintWriter pw) {
            pw.println(toString());
            if (null != cause) {
                    cause.printStackTrace(pw);
            }
    }

    public abstract String getRealm();
    public abstract String getDefine();

    /**
     * 获取异常抛出的栈信息，它反映了异常发生的准确位置
     * @return
     */
    public String getPosition() {
            return StackTraceUtil.getStackTrace(cause);
    }    
}

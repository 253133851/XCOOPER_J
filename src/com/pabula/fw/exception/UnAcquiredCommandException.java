package com.pabula.fw.exception;

/**
 * 无法受理的命令
 */
public class UnAcquiredCommandException extends Exception {

        /**
         * Constructor for UnAcquiredCommanddException
         */
        public UnAcquiredCommandException() {
                super();
        }

        /**
         * Constructor for UnAcquiredCommanddException
         */
        public UnAcquiredCommandException(String exceptionMsg) {
                super(exceptionMsg);
        }

}

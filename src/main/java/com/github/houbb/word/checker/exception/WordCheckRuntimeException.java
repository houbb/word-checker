package com.github.houbb.word.checker.exception;

/**
 * <p> 单词检查运行时异常 </p>
 *
 * <pre> Created: 2018-05-02 10:01  </pre>
 * <pre> Project: word-checker  </pre>
 *
 * @author houbinbin
 * @version 0.0.1
 * @since 0.0.1
 */
public class WordCheckRuntimeException extends RuntimeException {

    public WordCheckRuntimeException() {
    }

    public WordCheckRuntimeException(String message) {
        super(message);
    }

    public WordCheckRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WordCheckRuntimeException(Throwable cause) {
        super(cause);
    }

    public WordCheckRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

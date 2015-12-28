package com.rysiekblah.crom;

/**
 * Created by tomek on 4/28/14.
 */
public class CromException extends RuntimeException {
    public CromException(String detailMessage) {
        super(detailMessage);
    }

    public CromException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}

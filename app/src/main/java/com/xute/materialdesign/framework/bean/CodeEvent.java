package com.xute.materialdesign.framework.bean;

/**
 * Created by xute on 2016/12/26.
 */

public class CodeEvent {
    public static final int WX_CODE_SUCCESSFULLY = 1000;

    public int code;
    public String message;

    public CodeEvent(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

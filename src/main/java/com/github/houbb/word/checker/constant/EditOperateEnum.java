package com.github.houbb.word.checker.constant;

/**
 * 编辑操作
 * @since 1.2.0
 */
public enum EditOperateEnum {
    INSERT("I"),
    DELETE("D"),
    REPLACE("R"),
    ;

    private final String code;

    EditOperateEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

package com.jike.certification.commentEnum;

/**
 * 逻辑删除标识
 *
 * @author wxl
 */
public enum DeleteStatus {
    // 未删除
    NOT_DELETED(0),
    // 已删除
    DELETED(1);

    private int value;

    DeleteStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

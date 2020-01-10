package com.jike.system1.commentEnum;

/**
 * 验证码状态
 *
 * @author wentong
 */

public enum VerifyCodeEnum {
    NOT_VERIFY(0, "未验证"),
    VERIFY_SUCCESS(1, "验证成功"),
    VERIFY_FAIL(2, "验证失败");

    private Integer status;

    private String explain;

    VerifyCodeEnum(Integer status, String explain) {
        this.status = status;
        this.explain = explain;
    }

    public Integer getStatus() {
        return status;
    }

    public VerifyCodeEnum setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getExplain() {
        return explain;
    }

    public VerifyCodeEnum setExplain(String explain) {
        this.explain = explain;
        return this;
    }}


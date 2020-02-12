package com.jike.certification.commentEnum;

/**
 * @author wentong
 */

public enum OrderEnum {
    ASC("asc", "升序"),
    DESC("desc", "降序")
    ;

    private String order;
    private String Explain;

    OrderEnum(String order, String explain) {
        this.order = order;
        Explain = explain;
    }

    public String getOrder() {
        return order;
    }

    public OrderEnum setOrder(String order) {
        this.order = order;
        return this;
    }

    public String getExplain() {
        return Explain;
    }

    public OrderEnum setExplain(String explain) {
        Explain = explain;
        return this;
    }}

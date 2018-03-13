package com.thinkgem.jeesite.modules.ele.enums;

public enum OperateTypeEnum {

    ADD(1, "增加"),

    UPDATE(2, "修改"),

    DELETE(3, "删除");

    public final int type;
    public String name;

    OperateTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}

package com.briup.queuesystem.bean;

import lombok.Getter;

@Getter
public enum TableTypeEnums {
    TYPEA(1, "A"),
    TYPEB(2,"B");

    private Integer code;
    private String name;

    TableTypeEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TableTypeEnums find(Integer code) {
        for (TableTypeEnums instance : TableTypeEnums.values()) {
            if (instance.getCode().equals(code)) {
                return instance;
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        TableTypeEnums status = find(code);
        return null == status ? "" : status.getName();
    }
}

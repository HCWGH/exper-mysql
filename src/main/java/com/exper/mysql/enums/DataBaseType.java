package com.exper.mysql.enums;

/**
 * @author 1E7753
 * @date 2022/8/13 14:15
 * @todo 数据源类型枚举类
 */
public enum DataBaseType {
    //主库
    MASTER(0,"master"),
    //从库1号
    SLAVE_FIRST(1,"slave"),
    ;

    DataBaseType(Integer typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer typeId;
    private String name;
}

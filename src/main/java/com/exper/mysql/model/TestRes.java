package com.exper.mysql.model;

/**
 * @author 1E7753
 * @date 2022/8/13 16:41
 * @todo
 */
public class TestRes {
    private Integer a;
    private Integer b;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "TestRes{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}

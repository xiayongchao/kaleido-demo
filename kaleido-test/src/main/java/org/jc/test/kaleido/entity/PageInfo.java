package org.jc.test.kaleido.entity;

import java.util.List;

/**
 * @author jc
 * @date 2019/9/5 0:02
 */
public class PageInfo<T> {
    private T t;
    private List<T> list;
    private int count;

    public PageInfo() {
    }

    public PageInfo(T t, List<T> list, int count) {
        this.t = t;
        this.list = list;
        this.count = count;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

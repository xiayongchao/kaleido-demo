package org.jc.test.kaleido.entity;

/**
 * @author xiayc
 * @date 2019/9/3
 */
public class UserInfo {
    private long id;
    private String name;
    private boolean isMan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }
}

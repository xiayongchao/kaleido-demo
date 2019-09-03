package org.jc.test.kaleido.entity;

/**
 * @author xiayc
 * @date 2019/9/3
 */
public class UserExtInfo {
    private Integer id;
    private String uid;
    private Double age;

    public UserExtInfo() {
    }

    public UserExtInfo(Integer id, String uid, Double age) {
        this.id = id;
        this.uid = uid;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }
}

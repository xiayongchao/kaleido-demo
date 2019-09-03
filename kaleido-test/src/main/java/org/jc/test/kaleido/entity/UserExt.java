package org.jc.test.kaleido.entity;

/**
 * @author xiayc
 * @date 2019/9/3
 */
public class UserExt {
    private Long id;
    private String uid;
    private float age;

    public UserExt() {
    }

    public UserExt(Long id, String uid, float age) {
        this.id = id;
        this.uid = uid;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }
}

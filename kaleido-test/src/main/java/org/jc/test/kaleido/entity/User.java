package org.jc.test.kaleido.entity;

/**
 * @author xiayc
 * @date 2019/9/3
 */
public class User {
    private Long id;
    private String name;
    private Boolean isMan;
    private UserExt userExt;

    public User() {
    }

    public User(Long id, String name, Boolean isMan) {
        this.id = id;
        this.name = name;
        this.isMan = isMan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMan() {
        return isMan;
    }

    public void setMan(Boolean man) {
        isMan = man;
    }

    public UserExt getUserExt() {
        return userExt;
    }

    public void setUserExt(UserExt userExt) {
        this.userExt = userExt;
    }
}

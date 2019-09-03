package org.jc.test.kaleido.entity;

import java.util.List;

/**
 * @author xiayc
 * @date 2019/9/3
 */
public class UserInfo {
    private long id;
    private String name;
    private boolean isMan;
    private UserExtInfo userExt;
    private List<CommentInfo> commentList;

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

    public UserExtInfo getUserExt() {
        return userExt;
    }

    public void setUserExt(UserExtInfo userExt) {
        this.userExt = userExt;
    }

    public List<CommentInfo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentInfo> commentList) {
        this.commentList = commentList;
    }
}

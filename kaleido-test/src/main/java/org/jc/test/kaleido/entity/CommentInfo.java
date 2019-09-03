package org.jc.test.kaleido.entity;

import java.util.Date;

/**
 * @author jc
 * @date 2019/9/3 23:17
 */
public class CommentInfo {
    private Integer id;
    private String content;
    private Date createTime;

    public CommentInfo() {
    }

    public CommentInfo(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

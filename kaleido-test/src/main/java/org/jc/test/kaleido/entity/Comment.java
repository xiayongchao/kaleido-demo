package org.jc.test.kaleido.entity;

/**
 * @author jc
 * @date 2019/9/3 23:17
 */
public class Comment {
    private Long id;
    private String content;

    public Comment() {
    }

    public Comment(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

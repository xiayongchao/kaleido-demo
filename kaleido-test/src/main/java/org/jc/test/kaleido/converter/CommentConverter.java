package org.jc.test.kaleido.converter;

import org.jc.framework.kaleido.annotation.Converter;
import org.jc.framework.kaleido.converter.AbstractConverter;
import org.jc.test.kaleido.entity.Comment;
import org.jc.test.kaleido.entity.CommentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jc
 * @date 2019/9/4 0:15
 */
//@Converter
public class CommentConverter extends AbstractConverter<List<Comment>, List<CommentInfo>> {
    @Override
    public List<CommentInfo> convert(List<Comment> source) {
        return super.convert(source);
    }

    @Override
    public void copyProperties(List<Comment> source, List<CommentInfo> target) {
        for (Comment comment : source) {
            target.add(kaleidoscope.convert(Comment.class, CommentInfo.class, comment));
        }
    }

    /**
     * 实例化目标对象
     *
     * @return
     */
    @Override
    protected List<CommentInfo> newTarget(List<Comment> source) {
        return new ArrayList<>();
    }
}

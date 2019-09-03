package org.jc.test.kaleido.instancer;

import org.jc.framework.kaleido.annotation.Instancer;
import org.jc.framework.kaleido.instancer.AbstractInstancer;
import org.jc.test.kaleido.entity.CommentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jc
 * @date 2019/9/3 23:50
 */
//@Instancer
public class ListCommentInfoInstancer extends AbstractInstancer<List<CommentInfo>> {

    public ListCommentInfoInstancer() {
        super(new ArrayList<>());
    }

    @Override
    public List<CommentInfo> newInstance() {
        return new ArrayList<>();
    }
}
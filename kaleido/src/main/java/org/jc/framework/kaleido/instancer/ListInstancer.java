package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.Instancer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jc
 * @date 2019/9/3 23:42
 */
@Instancer
public class ListInstancer extends AbstractInstancer<List> {
    public ListInstancer() {
        super(null);
    }

    @Override
    public List newInstance() {
        return new ArrayList();
    }
}

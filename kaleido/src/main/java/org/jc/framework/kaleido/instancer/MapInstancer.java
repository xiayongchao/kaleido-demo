package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.Instancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jc
 * @date 2019/9/4 23:13
 */
@Instancer
public class MapInstancer extends AbstractInstancer<Map> {
    public MapInstancer() {
        super(null);
    }

    @Override
    public Map newInstance() {
        return new HashMap();
    }
}

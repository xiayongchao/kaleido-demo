package org.jc.framework.kaleido.instancer;

import org.jc.framework.kaleido.annotation.TypeRecognition;
import org.jc.framework.kaleido.exception.KaleidoException;

/**
 * @author xiayc
 * @date 2019/9/2
 */
@TypeRecognition(targetClass = char.class)
public class UnBoxCharacterInstancer extends AbstractInstancer<Character> {
    public UnBoxCharacterInstancer() {
        super('\u0000');
    }

    @Override
    public Character newInstance() {
        throw new KaleidoException("[char]基本数据类型不支持newInstance操作");
    }
}

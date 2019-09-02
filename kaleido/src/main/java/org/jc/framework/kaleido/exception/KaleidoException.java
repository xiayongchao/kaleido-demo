package org.jc.framework.kaleido.exception;

/**
 * @author xiayc
 * @date 2019/3/25
 */
public class KaleidoException extends RuntimeException {
    public KaleidoException() {
    }

    public KaleidoException(String template, Object... messages) {
        super(String.format(template, messages));
    }

    public KaleidoException(Throwable cause, String template, Object... messages) {
        super(String.format(template, messages), cause);
    }

    public KaleidoException(Throwable cause) {
        super(cause);
    }
}

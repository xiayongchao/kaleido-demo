package org.jc.framework.kaleido.core;

import org.jc.framework.kaleido.annotation.Null;
import org.jc.framework.kaleido.annotation.TypeRecognition;

import java.lang.reflect.Type;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author jc
 * @date 2019/9/3 23:28
 */
public abstract class KaleidoscopeSupporter {
    protected String getKey(Type sType, Type tType, TypeRecognition typeRecognition) {
        String sourceClassName = typeRecognition != null && !Null.class.equals(typeRecognition.sourceClass()) ? typeRecognition.sourceClass().getName() : null;
        String targetClassName = typeRecognition != null && !Null.class.equals(typeRecognition.targetClass()) ? typeRecognition.targetClass().getName() : null;
        return String.format("%s_%s", sourceClassName != null ? sourceClassName : sType.getTypeName(), targetClassName != null ? targetClassName : tType.getTypeName());
    }

    protected String getKey(Type tType, TypeRecognition typeRecognition) {
        String targetClassName = null;
        String tTypeTypeName;
        if (!(tTypeTypeName = tType.getTypeName()).startsWith(List.class.getName())) {
            targetClassName = typeRecognition != null && !Null.class.equals(typeRecognition.targetClass()) ? typeRecognition.targetClass().getName() : null;
        }
        return targetClassName != null ? targetClassName : tTypeTypeName;
    }

    protected String getListKey(Type tType) {
        if (tType == null) {
            return List.class.getName();
        }
        return String.format("%s<%s>", List.class.getName(), tType.getTypeName());
    }

    protected String getListKey(Type sType, Type tType) {
        String sTypeName, tTypeName;
        if (sType == null) {
            sTypeName = List.class.getName();
        } else {
            sTypeName = String.format("%s<%s>", List.class.getName(), sType.getTypeName());
        }
        if (tType == null) {
            tTypeName = List.class.getName();
        } else {
            tTypeName = String.format("%s<%s>", List.class.getName(), tType.getTypeName());
        }
        return String.format("%s_%s", sTypeName, tTypeName);
    }

    protected String getKey(Type... types) {
        StringJoiner stringJoiner = new StringJoiner("_");
        for (Type type : types) {
            stringJoiner.add(type.getTypeName());
        }
        return stringJoiner.toString();
    }

    protected boolean isEqual(Type sType, Type tType){
        return sType.equals(tType);
    }
}

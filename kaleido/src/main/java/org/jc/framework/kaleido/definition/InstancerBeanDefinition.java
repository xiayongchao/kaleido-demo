package org.jc.framework.kaleido.definition;

/**
 * @author xiayc
 * @date 2018/10/25
 */
public class InstancerBeanDefinition {
    private final String beanName;
    private final String beanClassName;
    private final Class<?> sourceClass;
    private final Class<?> targetClass;

    public InstancerBeanDefinition(String beanName, String beanClassName, Class<?> sourceClass, Class<?> targetClass) {
        this.beanName = beanName;
        this.beanClassName = beanClassName;
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public Class<?> getSourceClass() {
        return sourceClass;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstancerBeanDefinition that = (InstancerBeanDefinition) o;

        if (beanName != null ? !beanName.equals(that.beanName) : that.beanName != null) return false;
        if (beanClassName != null ? !beanClassName.equals(that.beanClassName) : that.beanClassName != null)
            return false;
        if (sourceClass != null ? !sourceClass.equals(that.sourceClass) : that.sourceClass != null) return false;
        return targetClass != null ? targetClass.equals(that.targetClass) : that.targetClass == null;
    }

    @Override
    public int hashCode() {
        int result = beanName != null ? beanName.hashCode() : 0;
        result = 31 * result + (beanClassName != null ? beanClassName.hashCode() : 0);
        result = 31 * result + (sourceClass != null ? sourceClass.hashCode() : 0);
        result = 31 * result + (targetClass != null ? targetClass.hashCode() : 0);
        return result;
    }
}

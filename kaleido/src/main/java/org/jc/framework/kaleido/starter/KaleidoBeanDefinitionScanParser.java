package org.jc.framework.kaleido.starter;


import org.jc.framework.kaleido.definition.KaleidoComponentScanDefinition;
import org.jc.framework.kaleido.exception.KaleidoException;
import org.jc.framework.kaleido.util.ArrayUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * 解析器
 *
 * @author xiayc
 * @date 2019/3/25
 */
public abstract class KaleidoBeanDefinitionScanParser<T> implements ResourceLoaderAware, EnvironmentAware {
    private Environment environment;
    private MetadataReaderFactory metadataReaderFactory;
    private ResourcePatternResolver resourcePatternResolver;
    private Class<? extends Annotation> beanAnnotationClass;

    public KaleidoBeanDefinitionScanParser(Class<? extends Annotation> beanAnnotationClass) {
        this.beanAnnotationClass = beanAnnotationClass;
    }

    /**
     * 扫描并解析组件
     *
     * @param kaleidoBeans
     * @param kaleidoComponentScanDefinition
     * @return
     * @throws IOException
     */
    protected Set<T> scanParse(final Set<String> kaleidoBeans, final KaleidoComponentScanDefinition kaleidoComponentScanDefinition) throws IOException {
        String[] basePackages;
        String resourcePattern;
        Set<T> beanDefinitions = new HashSet<>();

        if (ArrayUtils.isEmpty(basePackages = kaleidoComponentScanDefinition.getBasePackages())
                || !StringUtils.hasText(resourcePattern = kaleidoComponentScanDefinition.getResourcePattern())) {
            return beanDefinitions;
        }
        TypeFilter cachesFilter = new AnnotationTypeFilter(beanAnnotationClass);
        MetadataReader metadataReader;
        AnnotationMetadata annotationMetadata;
        String classPattern = "classpath*:%s/%s";
        Resource[] resources;
        T cachesBeanDefinition;
        String annotationName = beanAnnotationClass.getName();
        for (String basePackage : basePackages) {
            if (!StringUtils.hasText(basePackage)) {
                continue;
            }
            resources = this.resourcePatternResolver.getResources(String.format(classPattern, ClassUtils.convertClassNameToResourcePath(
                    this.environment.resolveRequiredPlaceholders(basePackage)), resourcePattern));
            if (ArrayUtils.isEmpty(resources)) {
                continue;
            }
            for (Resource resource : resources) {
                if (!resource.isReadable()) {
                    continue;
                }
                metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                annotationMetadata = metadataReader.getAnnotationMetadata();
                if (cachesFilter.match(metadataReader, this.metadataReaderFactory)) {
                    if (kaleidoBeans.contains(annotationMetadata.getClassName())) {
                        throw new KaleidoException("不允许重复注册的@%s Bean[%s]", beanAnnotationClass.getName(), annotationMetadata.getClassName());
                    } else if ((cachesBeanDefinition = generateBeanDefinition(annotationMetadata, annotationName, null, null)) != null) {
                        kaleidoBeans.add(annotationMetadata.getClassName());
                        beanDefinitions.add(cachesBeanDefinition);
                    }
                }
            }
        }
        return beanDefinitions;
    }

    protected abstract T generateBeanDefinition(final AnnotationMetadata annotationMetadata, final String annotationName, Class<?> sourceClass, Class<?> targetClass);

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
    }
}

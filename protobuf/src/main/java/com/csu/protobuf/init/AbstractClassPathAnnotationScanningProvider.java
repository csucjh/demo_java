package com.csu.protobuf.init;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractClassPathAnnotationScanningProvider implements ResourceLoaderAware {

    static final String DEFAULT_RESOURCE_PATTERN = "/**/*.class";

    private ResourcePatternResolver resourcePatternResolver;

    private MetadataReaderFactory metadataReaderFactory;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new SimpleMetadataReaderFactory(resourceLoader);
    }

    protected abstract Class<?> scanAnno();

    protected abstract List<String> scanPackages();

    protected abstract void execute(MetadataReader metadataReader);

    @PostConstruct
    protected void init() throws Exception {
        Class<?> annotation = this.scanAnno();
        if (annotation == null) {
            return;
        }

        List<String> packages = this.scanPackages();
        if (CollectionUtils.isEmpty(packages)) {
            return;
        }

        for (String location : packages) {
            String packageSearchPath = this.resolverPackage(location);
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);

            List<Resource> resList = Arrays.asList(resources).stream().filter(Resource::isReadable).collect(Collectors.toList());

            for (Resource resource : resList) {
                MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                Set<String> annos = metadataReader.getAnnotationMetadata().getAnnotationTypes();

                boolean match = annos.stream().anyMatch(anno -> anno.equalsIgnoreCase(annotation.getName()));

                if (match) {
                    Mono.just(metadataReader).publishOn(Schedulers.elastic()).subscribe(this::execute);
                }
            }
        }
    }

    protected String resolverPackage(String location) {
        return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(location) + this.DEFAULT_RESOURCE_PATTERN;
    }
}

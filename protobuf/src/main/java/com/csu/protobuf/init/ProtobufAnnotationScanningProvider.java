package com.csu.protobuf.init;

import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class ProtobufAnnotationScanningProvider extends AbstractClassPathAnnotationScanningProvider {

    @Override
    public Class<?> scanAnno() {
        return ProtobufClass.class;
    }

    @Override
    public List<String> scanPackages() {
        return Arrays.asList("com.csu.protobuf.model");
    }

    @Override
    protected void execute(MetadataReader metadataReader) {
        try {
            ProtobufProxy.create(Class.forName(metadataReader.getClassMetadata().getClassName()));
            System.out.println("222");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}

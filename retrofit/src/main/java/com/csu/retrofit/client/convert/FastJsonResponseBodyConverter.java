package com.csu.retrofit.client.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

final class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];

    private Type type;

    private ParserConfig config;
    private int featureValues;
    private Feature[] features;

    FastJsonResponseBodyConverter(Type type, ParserConfig config, int featureValues,
                                  Feature... features) {
        this.type = type;
        this.config = config;
        this.featureValues = featureValues;
        this.features = features;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            if (this.type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) this.type;
                Type rawType = parameterizedType.getRawType();
                Type actualType = parameterizedType.getActualTypeArguments()[0];
                if (List.class.isAssignableFrom((Class) rawType)) {
                    return (T) JSON.parseArray(value.string(), (Class) actualType);
                }

                return null;
            } else {
                return JSON.parseObject(value.string(), this.type);
            }
        } finally {
            value.close();
        }
    }
}

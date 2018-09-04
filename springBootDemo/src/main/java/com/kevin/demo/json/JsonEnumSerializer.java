package com.kevin.demo.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;

public class JsonEnumSerializer extends JsonSerializer<Enum> {

    @Override
    public void serialize(Enum value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        Class clazz = value.getClass();
        String json = null;

        try {
            Field label = clazz.getDeclaredField("dto");
            label.setAccessible(true); // 设置些属性是可以访问的
            Object val = label.get(value);// 得到此属性的值
            json = val.toString();

        } catch (Exception e) {
            json = value.name();
        }

        jgen.writeString(json);
    }

}

package com.kevin.applets.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonBooleanSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String json = "否";

        if(value){
            json = "是";
        }

        jgen.writeString(json);
    }
}

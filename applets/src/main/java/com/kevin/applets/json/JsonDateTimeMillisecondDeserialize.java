package com.kevin.applets.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateTimeMillisecondDeserialize extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");
		String returnDateString = arg0.getText();
		/*如果返回的时间的字符串是""或者null,那么此时的Date的值将是null*/
		try {
			if(StringUtils.isNotBlank(returnDateString))
				return sdf.parse(returnDateString);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("前台传入的时间格式不正确!");
		}
		return null;
	}
}

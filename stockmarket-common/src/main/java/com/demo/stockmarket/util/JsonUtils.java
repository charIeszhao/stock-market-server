package com.demo.stockmarket.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.demo.stockmarket.exception.BaseRuntimeException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonUtils {

	/**
	 * Shared date format.
	 */
	// private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss z");

	/**
	 * Shared mapper.
	 */
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	public static String toPrettyString(Object obj, Class<?> type) {
		try {
			return mapper.writerFor(type).withDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			throw new BaseRuntimeException(500, e, "Failed to convert Object to json string.");
		}
	}

	public static String toString(Object obj, Class<?> type) {
		try {
			return mapper.writerFor(type).writeValueAsString(obj);
		} catch (Exception e) {
			throw new BaseRuntimeException(500, e, "Failed to convert Object to json string.");
		}
	}

	public static String toPrettyString(Object obj) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			throw new BaseRuntimeException(500, e, "Failed to convert Object to json string.");
		}
	}

	public static String toString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new BaseRuntimeException(500, e, "Failed to convert Object to json string.");
		}
	}

	public static String objectToJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new BaseRuntimeException(500, e, "ObjectToJson error.");
		}
	}

	public static String objectToJsonWithNull(Object obj) {
		try {
			mapper.setSerializationInclusion(Include.ALWAYS);
			String result = mapper.writeValueAsString(obj);
			mapper.setSerializationInclusion(Include.NON_NULL);
			return result;
		} catch (Exception e) {
			throw new BaseRuntimeException(500, e, "objectToJsonWithNull error.");
		}
	}

	public static <T> T jsonToObject(String content, Class<T> valueType) {

		if (StringUtils.isBlank(content)) {
			return null;
		}
		try {
			return mapper.readValue(content, valueType);
		} catch (Exception e) {
			throw new BaseRuntimeException(500, e, "jsonToObject error.");
		}
	}

	public class StringFieldSerializer extends JsonSerializer<Object> {

		@Override
		public void serialize(Object paramT, JsonGenerator paramJsonGenerator,
				SerializerProvider paramSerializerProvider) throws IOException, JsonProcessingException {
			paramJsonGenerator.writeString("********");
		}
	}

}

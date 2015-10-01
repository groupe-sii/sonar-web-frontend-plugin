package fr.sii.sonar.report.core.common.parser;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.sii.sonar.report.core.common.exception.ParseException;

public class JsonParser<R> implements Parser<R> {
	private final ObjectMapper mapper;
	
	private TypeReference<R> typeReference;
	
	public JsonParser() {
		this(new ObjectMapper().findAndRegisterModules());
	}

	public JsonParser(ObjectMapper mapper) {
		this(mapper, new TypeReference<R>() {});
	}

	public JsonParser(TypeReference<R> typeReference) {
		this(new ObjectMapper().findAndRegisterModules(), typeReference);
	}

	public JsonParser(ObjectMapper mapper, TypeReference<R> typeReference) {
		super();
		this.mapper = mapper;
		this.typeReference = typeReference;
	}

	public R parse(InputStream stream) throws ParseException {
		try {
			return mapper.readValue(stream, typeReference);
		} catch (JsonParseException e) {
			throw new ParseException("Failed to parse JSON file", e);
		} catch (JsonMappingException e) {
			throw new ParseException("Failed to parse JSON file", e);
		} catch (IOException e) {
			throw new ParseException("Failed to parse JSON file", e);
		}
	}

}

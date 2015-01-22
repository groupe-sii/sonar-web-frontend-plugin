package fr.sii.sonar.report.core.common.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Closeables;

import fr.sii.sonar.report.core.common.domain.Report;
import fr.sii.sonar.report.core.common.exception.ProviderException;

/**
 * Provider that parses a JSON file and generates an instance of the provided class
 * 
 * @author Aurélien Baudet
 *
 * @param <R> the type used for the structure of the parsed data
 */
public class JsonFileReportProvider<R extends Report> implements Provider<R> {

	private final ObjectMapper objectMapper;
	private final InputStream stream;
	private TypeReference<R> type;
	
	public JsonFileReportProvider(InputStream stream, TypeReference<R> type) {
		super();
		this.objectMapper = new ObjectMapper();
		this.stream = stream;
		this.type = type;
		configure();
	}

	public JsonFileReportProvider(File file, TypeReference<R> type) throws FileNotFoundException {
		this(new FileInputStream(file), type);
	}


	/**
	 * Parse a JSON file to get the report (of any kind)
	 */
	public R get() throws ProviderException {
		try {
			return objectMapper.readValue(stream, type);
		} catch (JsonParseException e) {
			throw new ProviderException("failed to parse json file. Cause: "+e.getMessage(), e);
		} catch (JsonMappingException e) {
			throw new ProviderException("failed to parse json file. Cause: "+e.getMessage(), e);
		} catch (IOException e) {
			throw new ProviderException("failed to parse json file. Cause: "+e.getMessage(), e);
		} finally {
			Closeables.closeQuietly(stream);
		}
	}
	
	protected void configure() {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	protected TypeReference<R> getInstanceType() {
		return type;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}

package fr.sii.sonar.web.client.core.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;

import fr.sii.sonar.web.client.core.domain.report.WebClientReport;

/**
 * Provider that parses a JSON file and generate a {@link WebClientReport} instance
 * 
 * @author aurelien
 */
public class WebClientReportProvider extends JsonFileReportProvider<WebClientReport> {

	public WebClientReportProvider(File file) throws FileNotFoundException {
		super(file, new TypeReference<WebClientReport>() {});
	}

	public WebClientReportProvider(InputStream stream) {
		super(stream, new TypeReference<WebClientReport>() {});
	}
	
	
}

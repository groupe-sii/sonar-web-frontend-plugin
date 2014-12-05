package fr.sii.sonar.report.core.quality.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;

import fr.sii.sonar.report.core.common.provider.JsonFileReportProvider;
import fr.sii.sonar.report.core.quality.domain.report.QualityReport;

/**
 * Provider that parses a JSON file and generate a {@link QualityReport} instance
 * 
 * @author Aurélien Baudet
 */
public class JsonQualityReportProvider extends JsonFileReportProvider<QualityReport> {

	public JsonQualityReportProvider(File file) throws FileNotFoundException {
		super(file, new TypeReference<QualityReport>() {});
	}

	public JsonQualityReportProvider(InputStream stream) {
		super(stream, new TypeReference<QualityReport>() {});
	}
	
	
}

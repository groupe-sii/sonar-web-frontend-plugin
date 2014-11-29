package fr.sii.sonar.report.core;

import org.sonar.api.BatchExtension;
import org.sonar.api.ServerExtension;

public interface ReportConstants extends BatchExtension, ServerExtension {
	public String getReportPathKey();
	
	public String getRepositoryKey();

	public String getRulesProfileName();

	public String getLanguageKey();
	
	public String getMissingFileFailKey();
}

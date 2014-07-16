package fr.sii.sonar.web.client.core;

import org.sonar.api.BatchExtension;
import org.sonar.api.ServerExtension;

public interface WebClientConstants extends BatchExtension, ServerExtension {
	public String getReportPathKey();
	
	public String getRepositoryKey();

	public String getRulesProfileName();

	public String getLanguageKey();
	
	public String getMissingFileFailKey();
}

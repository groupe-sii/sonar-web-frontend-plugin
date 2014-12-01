package fr.sii.sonar.web.client.html;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import fr.sii.sonar.quality.core.QualitySensor;
import fr.sii.sonar.quality.core.StaticRuleProfile;
import fr.sii.sonar.quality.core.factory.JsonQualityReportProviderFactory;
import fr.sii.sonar.quality.core.factory.SimpleQualityReportSaverFactory;

/**
 * This class is the entry point for all extensions
 */
@Properties({
	@Property(
		key = HtmlConstants.FILE_SUFFIXES_KEY,
		defaultValue = HtmlConstants.FILE_SUFFIXES_DEFVALUE,
		name = "File suffixes for html files",
		description = "Comma-separated list of suffixes for files to analyze.",
		global = true,
		project = true
	),
	@Property(
		key = HtmlConstants.REPORT_PATH_KEY, 
		defaultValue = HtmlConstants.REPORT_PATH_DEFVALUE, 
		name = "Report path", 
		description = "The path to the report file to load", 
		global = true, 
		project = true
	),
	@Property(
		key = HtmlConstants.FAIL_MISSING_FILE_KEY, 
		defaultValue = HtmlConstants.FAIL_MISSING_FILE_DEFVALUE, 
		name = "Fail on missing file", 
		description = "True to stop analysis if a file is not found", 
		global = true, 
		project = true
	)
})
public final class HtmlPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getExtensions() {
		return Arrays.asList(
				HtmlConstants.class,
				Html.class,
				JsonQualityReportProviderFactory.class,
				SimpleQualityReportSaverFactory.class,
				HtmlHintRuleRepository.class,
				StaticRuleProfile.class,
				QualitySensor.class
		);
	}
}

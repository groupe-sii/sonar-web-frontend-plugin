package fr.sii.sonar.web.frontend.html.quality;

import fr.sii.sonar.report.core.common.rules.RulesDefinitionConstants;
import fr.sii.sonar.report.core.quality.ProfileDefinitionConstants;
import fr.sii.sonar.report.core.quality.QualityConstants;
import fr.sii.sonar.web.frontend.html.HtmlLanguageConstants;

public class HtmlHintQualityConstants extends HtmlLanguageConstants implements QualityConstants, RulesDefinitionConstants, ProfileDefinitionConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.quality.html.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.quality.html.file.missing.fail";
	public static final String SKIP_FILE_METRICS_KEY = "sonar.sii.quality.html.file.metrics.skip";
	public static final String SKIP_FILE_METRICS_DEFVALUE = "false";
	public static final String REPORT_PATH_DEFVALUE = "/reports/sonar/htmlhint.json";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String RULES_PATH = "/rules/htmlhint.json";
	public static final String REPOSITORY_NAME = "HTMLHint";
	public static final String REPOSITORY_KEY = "htmlhint";
	public static final String SUB_CATEGORY = "Quality";
	public static final String PROFILE_PATH = "/profiles/htmlhint.json";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getRepositoryKey() {
		return REPOSITORY_KEY;
	}

	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

	public String getRepositoryName() {
		return REPOSITORY_NAME;
	}

	public String getRulesJsonPath() {
		return RULES_PATH;
	}

	public String getProfileJsonPath() {
		return PROFILE_PATH;
	}

	public String getSkipFileMetricsKey() {
		return SKIP_FILE_METRICS_KEY;
	}
}

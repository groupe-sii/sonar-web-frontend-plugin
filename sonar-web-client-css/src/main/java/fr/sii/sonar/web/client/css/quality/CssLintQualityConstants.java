package fr.sii.sonar.web.client.css.quality;

import fr.sii.sonar.report.core.common.rules.RulesDefinitionConstants;
import fr.sii.sonar.report.core.quality.ProfileDefinitionConstants;
import fr.sii.sonar.report.core.quality.QualityConstants;
import fr.sii.sonar.web.client.css.CssLanguageConstants;

public class CssLintQualityConstants extends CssLanguageConstants implements QualityConstants, RulesDefinitionConstants, ProfileDefinitionConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.quality.css.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.quality.css.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/csslint.json";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String RULES_PATH = "/rules/csslint.json";
	public static final String REPOSITORY_NAME = "SII Web client - CSS";
	public static final String REPOSITORY_KEY = "csslint";
	public static final String SUB_CATEGORY = "Quality";
	public static final String PROFILE_PATH = "/profiles/csslint.json";
	
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
}

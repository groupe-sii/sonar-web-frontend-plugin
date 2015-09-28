package fr.sii.sonar.web.client.scss.quality;

import fr.sii.sonar.report.core.common.rules.RulesDefinitionConstants;
import fr.sii.sonar.report.core.quality.QualityConstants;
import fr.sii.sonar.web.client.scss.ScssConstants;

public class ScssQualityConstants extends ScssConstants implements QualityConstants, RulesDefinitionConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.quality.scss.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.quality.scss.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/scsslint.json";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String RULES_PATH = "/rules/scsslint.json";
	public static final String REPOSITORY_NAME = "SII Web client - SCSS";
	public static final String REPOSITORY_KEY = "scsslint";
	public static final String RULES_PROFILE_NAME = REPOSITORY_KEY;
	public static final String SUB_CATEGORY = "Quality";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getRepositoryKey() {
		return REPOSITORY_KEY;
	}

	public String getRulesProfileName() {
		return RULES_PROFILE_NAME;
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
}

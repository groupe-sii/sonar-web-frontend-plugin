package fr.sii.sonar.web.frontend.css.duplication;

import fr.sii.sonar.report.core.duplication.DuplicationConstants;
import fr.sii.sonar.web.frontend.css.CssLanguageConstants;

public class CssDuplicationConstants extends CssLanguageConstants implements DuplicationConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.duplication.css.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.duplication.css.file.missing.fail";
	public static final String SKIP_DUPLICATION_KEY = "sonar.sii.duplication.css.skip";
	public static final String SKIP_DUPLICATION_DEFVAL = "false";
	public static final String REPORT_PATH_DEFVALUE = "/reports/sonar/css-duplication.xml";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String SUB_CATEGORY = "Duplication";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

	public String getSkipDuplicationKey() {
		return SKIP_DUPLICATION_KEY;
	}
}

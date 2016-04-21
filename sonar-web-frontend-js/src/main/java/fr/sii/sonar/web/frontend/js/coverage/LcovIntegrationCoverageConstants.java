package fr.sii.sonar.web.frontend.js.coverage;

import fr.sii.sonar.report.core.common.ReportConstants;
import fr.sii.sonar.report.core.coverage.CoverageConstants;
import fr.sii.sonar.web.frontend.js.JsLanguageConstants;

public class LcovIntegrationCoverageConstants extends JsLanguageConstants implements ReportConstants, CoverageConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.coverage.it.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.coverage.it.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/reports/sonar/js-it.lcov";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String SUB_CATEGORY = "Coverage";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

}

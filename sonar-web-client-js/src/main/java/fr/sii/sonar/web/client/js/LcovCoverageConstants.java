package fr.sii.sonar.web.client.js;

import fr.sii.sonar.report.core.ReportConstants;

public class LcovCoverageConstants implements ReportConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.coverage.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.coverage.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/js.lcov";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.coverage.js.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".js";
	public static final String LANGUAGE_KEY = "js";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}

	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

}

package fr.sii.sonar.web.client.ng;

import fr.sii.sonar.report.core.common.ReportConstants;

public abstract class AngularHintConstants implements ReportConstants {
	public static final String LANGUAGE_KEY = "js";
	public static final String CATEGORY = "AngularJS";
	public static final String SUB_CATEGORY = "General";

	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}

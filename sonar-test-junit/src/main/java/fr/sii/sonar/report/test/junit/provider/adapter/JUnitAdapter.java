package fr.sii.sonar.report.test.junit.provider.adapter;

import fr.sii.sonar.report.core.common.provider.ReportAdapter;
import fr.sii.sonar.report.core.test.domain.TestReport;

/**
 * Specialized interface for JUnit adapters
 * 
 * @author Aurélien Baudet
 *
 * @param <T>
 *            the type for JUnit test suites (different according to JUnit
 *            version)
 */
public interface JUnitAdapter<T> extends ReportAdapter<TestReport, T> {
	public TestReport adapt(T suites);
}

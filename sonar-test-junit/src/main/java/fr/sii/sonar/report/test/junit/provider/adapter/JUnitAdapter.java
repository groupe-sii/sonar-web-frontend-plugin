package fr.sii.sonar.report.test.junit.provider.adapter;

import fr.sii.sonar.report.core.test.domain.TestReport;

public interface JUnitAdapter<T> {
	public TestReport adapt(T suites);
}

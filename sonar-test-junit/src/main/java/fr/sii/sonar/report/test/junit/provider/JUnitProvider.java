package fr.sii.sonar.report.test.junit.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import fr.sii.sonar.report.core.common.provider.XmlFileReportProvider;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.test.junit.provider.adapter.JUnitAdapter;

/**
 * Provider that parses a JUnit xml file and provide a {@link TestReport}
 * 
 * @author Aurélien Baudet
 *
 */
public class JUnitProvider<T> extends XmlFileReportProvider<TestReport, T> {

	public JUnitProvider(InputStream stream, Class<T> xmlClass, JUnitAdapter<T> adapter) {
		super(stream, xmlClass, adapter);
	}

	public JUnitProvider(File reportFile, Class<T> xmlClass, JUnitAdapter<T> adapter) throws FileNotFoundException {
		super(reportFile, xmlClass, adapter);
	}
}

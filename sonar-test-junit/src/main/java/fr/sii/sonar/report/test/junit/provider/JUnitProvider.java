package fr.sii.sonar.report.test.junit.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.common.io.Closeables;

import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.test.junit.provider.adapter.JUnitAdapter;

/**
 * Provider that parses a JUnit xml file and provide a {@link TestReport}
 * 
 * @author Aurélien Baudet
 *
 */
public class JUnitProvider<T> implements Provider<TestReport> {

	private InputStream stream;
	private Class<T> junitClass;
	private JUnitAdapter<T> adapter;

	public JUnitProvider(InputStream stream, Class<T> junitClass, JUnitAdapter<T> adapter) {
		super();
		this.stream = stream;
		this.junitClass = junitClass;
		this.adapter = adapter;
	}
	
	public JUnitProvider(File reportFile, Class<T> junitClass, JUnitAdapter<T> adapter) throws FileNotFoundException {
		this(new FileInputStream(reportFile), junitClass, adapter);
	}

	public TestReport get() throws ProviderException {
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(junitClass).createUnmarshaller();
			return adapter.adapt(junitClass.cast(unmarshaller.unmarshal(stream)));
		} catch (JAXBException e) {
			throw new ProviderException("failed to parse JUnit report", e);
		} finally {
			Closeables.closeQuietly(stream);
		}
	}
	
}

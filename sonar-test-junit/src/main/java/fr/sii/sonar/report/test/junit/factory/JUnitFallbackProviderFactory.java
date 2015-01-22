package fr.sii.sonar.report.test.junit.factory;

import java.io.File;
import java.io.FileNotFoundException;

import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.provider.FallbackProvider;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.test.junit.provider.JUnitProvider;
import fr.sii.sonar.report.test.junit.provider.adapter.JUnitV5Adapter;
import fr.sii.sonar.report.test.junit.provider.adapter.JUnitV6Adapter;
import fr.sii.sonar.report.test.junit.provider.adapter.JUnitV7Adapter;

public class JUnitFallbackProviderFactory implements ProviderFactory<TestReport> {

	public Provider<TestReport> create(File reportFile) throws CreateException {
		try {
			return new FallbackProvider<TestReport>(
								new JUnitProvider<fr.sii.sonar.test.junit.domain.v7.Testsuites>(reportFile, fr.sii.sonar.test.junit.domain.v7.Testsuites.class, new JUnitV7Adapter()),
								new JUnitProvider<fr.sii.sonar.test.junit.domain.v6.Testsuites>(reportFile, fr.sii.sonar.test.junit.domain.v6.Testsuites.class, new JUnitV6Adapter()),
								new JUnitProvider<fr.sii.sonar.test.junit.domain.v5.Testsuites>(reportFile, fr.sii.sonar.test.junit.domain.v5.Testsuites.class, new JUnitV5Adapter()));
//								new JUnitProvider<fr.sii.sonar.test.junit.domain.v4.Testsuites>(reportFile, fr.sii.sonar.test.junit.domain.v4.Testsuites.class, new JUnitV4Adapter()),
//								new JUnitProvider<fr.sii.sonar.test.junit.domain.v3.Testsuites>(reportFile, fr.sii.sonar.test.junit.domain.v3.Testsuites.class, new JUnitV3Adapter()),
//								new JUnitProvider<fr.sii.sonar.test.junit.domain.v2.Testsuites>(reportFile, fr.sii.sonar.test.junit.domain.v2.Testsuites.class, new JUnitV2Adapter()),
//								new JUnitProvider<fr.sii.sonar.test.junit.domain.v1.Testsuites>(reportFile, fr.sii.sonar.test.junit.domain.v1.Testsuites.class, new JUnitV1Adapter()));
		} catch (FileNotFoundException e) {
			throw new CreateException("failed to load report file "+reportFile.getAbsolutePath(), e);
		}
	}

}

package fr.sii.sonar.report.core.quality.provider;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.quality.domain.report.QualityReport;

public class TestJsonFileReportProvider {

	@Test
	public void css() throws ProviderException {
		QualityReport report = new JsonQualityReportProvider(getClass().getResourceAsStream("/report/csslint.json")).get();
		Assert.assertEquals("file list should contain 43 elements", 43, report.getFiles().size());
	}

}

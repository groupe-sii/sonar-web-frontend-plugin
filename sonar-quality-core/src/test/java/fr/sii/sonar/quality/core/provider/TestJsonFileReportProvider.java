package fr.sii.sonar.quality.core.provider;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.sonar.quality.core.domain.report.QualityReport;
import fr.sii.sonar.quality.core.provider.JsonQualityReportProvider;
import fr.sii.sonar.report.core.exception.ProviderException;

public class TestJsonFileReportProvider {

	@Test
	public void css() throws ProviderException {
		QualityReport report = new JsonQualityReportProvider(getClass().getResourceAsStream("/report/csslint.json")).get();
		Assert.assertEquals("project should be CroixRouge", "CroixRouge", report.getProject());
		Assert.assertEquals("language should be css", "css", report.getLanguage());
		Assert.assertEquals("number of files should be 43", 43, report.getNbFiles());
		Assert.assertEquals("file list should contain 43 elements", 43, report.getFiles().size());
		// TODO: assertions
	}

}

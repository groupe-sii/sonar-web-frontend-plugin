package fr.sii.sonar.web.client.core.provider;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.sonar.web.client.core.domain.report.WebClientReport;
import fr.sii.sonar.web.client.core.exception.ProviderException;
import fr.sii.sonar.web.client.core.provider.WebClientReportProvider;

public class TestJsonFileReportProvider {

	@Test
	public void css() throws ProviderException {
		WebClientReport report = new WebClientReportProvider(getClass().getResourceAsStream("/report/csslint.json")).get();
		Assert.assertEquals("project should be CroixRouge", "CroixRouge", report.getProject());
		Assert.assertEquals("language should be css", "css", report.getLanguage());
		Assert.assertEquals("number of files should be 43", 43, report.getNbFiles());
		Assert.assertEquals("file list should contain 43 elements", 43, report.getFiles().size());
		// TODO: assertions
	}

}

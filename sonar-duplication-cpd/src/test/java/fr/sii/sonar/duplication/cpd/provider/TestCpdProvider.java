package fr.sii.sonar.duplication.cpd.provider;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

public class TestCpdProvider {
	@Test
	public void groupIdenticalBlocks() throws ProviderException {
		DuplicationReport report = new CpdProvider(getClass().getResourceAsStream("/report/cpd.xml")).get();
		Assert.assertEquals("report should have 34 groups", 34, report.getDuplicationGroups().size());
	}
}

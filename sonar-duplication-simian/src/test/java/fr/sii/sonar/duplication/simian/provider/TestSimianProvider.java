package fr.sii.sonar.duplication.simian.provider;

import org.junit.Assert;
import org.junit.Test;

import fr.sii.sonar.duplication.simian.provider.SimianProvider;
import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

public class TestSimianProvider {
	@Test
	public void groupIdenticalBlocks() throws ProviderException {
		DuplicationReport report = new SimianProvider(getClass().getResourceAsStream("/report/simian.xml")).get();
		Assert.assertEquals("report should have 23 groups", 23, report.getDuplicationGroups().size());
	}
}

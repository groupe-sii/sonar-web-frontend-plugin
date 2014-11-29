package fr.sii.sonar.coverage.lcov.parser;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import fr.sii.sonar.coverage.lcov.domain.LcovReport;

public class TestLcovParser {

	@Test
	public void ibrt() throws IOException {
		LcovParser parser = new LcovParser();
		LcovReport report = parser.parse(IOUtils.readLines(getClass().getResourceAsStream("/ibrt.lcov")));
		Assert.assertNotNull(report);
	}
}

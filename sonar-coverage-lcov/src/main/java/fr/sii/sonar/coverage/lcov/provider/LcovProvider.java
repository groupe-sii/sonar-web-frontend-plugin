package fr.sii.sonar.coverage.lcov.provider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.common.io.Closeables;

import fr.sii.sonar.coverage.lcov.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.parser.LcovParser;
import fr.sii.sonar.report.core.exception.ProviderException;
import fr.sii.sonar.report.core.provider.Provider;

public class LcovProvider implements Provider<LcovReport> {

	private final List<String> lines;

	public LcovProvider(File reportFile) throws IOException {
		super();
		lines = FileUtils.readLines(reportFile);
	}
	
	public LcovProvider(InputStream stream) throws IOException {
		super();
		try {
			lines = IOUtils.readLines(stream);
		} finally {
			Closeables.closeQuietly(stream);
		}
	}
	public LcovProvider(List<String> lines) {
		super();
		this.lines = lines;
	}

	public LcovReport get() throws ProviderException {
		return new LcovParser().parse(lines);
	}


}

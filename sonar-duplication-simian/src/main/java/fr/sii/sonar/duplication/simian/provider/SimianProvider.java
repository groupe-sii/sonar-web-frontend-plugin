package fr.sii.sonar.duplication.simian.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import fr.sii.sonar.duplication.simian.domain.Simian;
import fr.sii.sonar.report.core.common.provider.XmlFileReportProvider;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

/**
 * Provider that parses a Simian xml file and provide a {@link DuplicationReport}
 * 
 * @author Aurélien Baudet
 *
 */
public class SimianProvider extends XmlFileReportProvider<DuplicationReport, Simian> {

	public SimianProvider(InputStream stream) {
		super(stream, Simian.class, new SimianAdapter());
	}

	public SimianProvider(File reportFile) throws FileNotFoundException {
		super(reportFile, Simian.class, new SimianAdapter());
	}

}

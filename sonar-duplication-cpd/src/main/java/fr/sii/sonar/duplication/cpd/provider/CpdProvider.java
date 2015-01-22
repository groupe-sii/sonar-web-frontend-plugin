package fr.sii.sonar.duplication.cpd.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import fr.sii.sonar.duplication.cpd.domain.PmdCpd;
import fr.sii.sonar.report.core.common.provider.XmlFileReportProvider;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

/**
 * Provider that parses a CPD xml file and provide a {@link DuplicationReport}
 * 
 * @author Aurélien Baudet
 *
 */
public class CpdProvider extends XmlFileReportProvider<DuplicationReport, PmdCpd> {

	public CpdProvider(InputStream stream) {
		super(stream, PmdCpd.class, new CpdAdapter());
	}

	public CpdProvider(File reportFile) throws FileNotFoundException {
		super(reportFile, PmdCpd.class, new CpdAdapter());
	}

}

package fr.sii.sonar.coverage.lcov.provider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.common.io.Closeables;

import fr.sii.sonar.coverage.lcov.parser.LcovParser;
import fr.sii.sonar.coverage.lcov.parser.domain.BranchCoverageDetail;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.parser.domain.LineCoverageDetail;
import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.coverage.domain.BranchCoverage;
import fr.sii.sonar.report.core.coverage.domain.CoverageReport;
import fr.sii.sonar.report.core.coverage.domain.FileCoverage;
import fr.sii.sonar.report.core.coverage.domain.LineCoverage;

/**
 * A report provider that is specific to LCOV report
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovProvider implements Provider<CoverageReport> {

	/**
	 * The list of lines in the LCOV file
	 */
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

	public CoverageReport get() throws ProviderException {
		LcovReport lcov = new LcovParser().parse(lines);
		return new CoverageReport(convert(lcov.getFiles()));
	}

	/**
	 * Transforms a list of LCOV file coverage into a list of Sonar file
	 * coverage
	 * 
	 * @param files
	 *            the list of LCOV file coverage
	 * @return the list of Sonar file coverage
	 */
	private List<FileCoverage> convert(List<fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage> files) {
		List<FileCoverage> covFiles = new ArrayList<FileCoverage>(files.size());
		for (fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage file : files) {
			covFiles.add(convert(file));
		}
		return covFiles;
	}

	/**
	 * Transforms a LCOV file coverage into a Sonar file coverage
	 * 
	 * @param file
	 *            the LCOV file coverage
	 * @return the Sonar file coverage
	 */
	private FileCoverage convert(fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage file) {
		return new FileCoverage(file.getPath(), convert(file.getLines().getDetails(), file.getBranches().getDetails()));
	}

	/**
	 * Transforms a list of LCOV line and branch coverage into a list of Sonar
	 * line and branch coverage
	 * 
	 * @param lines
	 *            the LCOV line coverage
	 * @param branches
	 *            the LCOV branch coverage
	 * @return the list of Sonar line and branch coverage
	 */
	private List<LineCoverage> convert(List<LineCoverageDetail> lines, List<BranchCoverageDetail> branches) {
		Map<Integer, BranchCoverage> branchesByLine = groupByLine(branches);
		List<LineCoverage> covLines = new ArrayList<LineCoverage>(lines.size());
		for (LineCoverageDetail line : lines) {
			covLines.add(convert(line, branchesByLine));
		}
		return covLines;
	}

	/**
	 * Transform a LCOV line coverage and branch coverage into generic line and
	 * branch coverage usable by sonar
	 * 
	 * @param line
	 *            the LCOV line coverage
	 * @param branchesByLine
	 *            map of Sonar branch coverage indexed by line number
	 * @return Sonar line coverage
	 */
	private LineCoverage convert(LineCoverageDetail line, Map<Integer, BranchCoverage> branchesByLine) {
		return new LineCoverage(line.getLine(), line.getExecutionCount(), branchesByLine.get(line.getLine()));
	}

	/**
	 * Transforms the list of LCOV branches into a map of Sonar branch coverage
	 * indexed by line number
	 * 
	 * @param branches
	 *            the LCOV coverage details for branches
	 * @return the map of Sonar branch coverage indexed by the line number
	 */
	private Map<Integer, BranchCoverage> groupByLine(List<BranchCoverageDetail> branches) {
		Map<Integer, BranchCoverage> map = new HashMap<Integer, BranchCoverage>();
		for (BranchCoverageDetail b : branches) {
			if (!map.containsKey(b.getLine())) {
				map.put(b.getLine(), new BranchCoverage(0, 0));
			}
			BranchCoverage branchCoverage = map.get(b.getLine());
			branchCoverage.addCondition(1);
			if (b.getTaken() > 0) {
				branchCoverage.addCovered(1);
			}
		}
		return map;
	}

}

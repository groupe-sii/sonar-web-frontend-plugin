package fr.sii.sonar.report.core.coverage.domain;

import java.util.Arrays;
import java.util.List;


/**
 * The report provides information for each covered file.
 * For each covered line in the file, it provides information about the line coverage.
 * 
 * @author Aurélien Baudet
 *
 */
public class FileCoverage {
	/**
	 * The path to the file
	 */
	private String path;
	
	/**
	 * The list of coverage information for each covered line in the file.
	 * If a line is not covered, then it must not appear in this list
	 */
	private List<LineCoverage> lines;

	public FileCoverage(String path, LineCoverage... lines) {
		this(path, Arrays.asList(lines));
	}

	public FileCoverage(String path, List<LineCoverage> lines) {
		super();
		this.path = path;
		this.lines = lines;
	}

	public String getPath() {
		return path;
	}

	public List<LineCoverage> getLines() {
		return lines;
	}

	public void setLines(List<LineCoverage> lines) {
		this.lines = lines;
	}
	
	public void addLine(LineCoverage line) {
		lines.add(line);
	}
}

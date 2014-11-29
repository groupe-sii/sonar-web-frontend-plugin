package fr.sii.sonar.quality.core.domain.report;

import java.util.List;
import java.util.Map;

/**
 * Quality information for a whole project that provides the following
 * information :
 * <ul>
 * <li>the total number of files in the project</li>
 * <li>the list of files with their quality information (number of lines,
 * details for all issues...)</li>
 * <li>a map that provides the total number of violations for a particular
 * severity</li>
 * </ul>
 * 
 * @author aurelien
 *
 */
public class ProjectStat extends LinesStat {

	/**
	 * The total number of files
	 */
	private long nbFiles;

	/**
	 * A map that contains the total number of violations. The map is indexed by
	 * severity.
	 */
	private Map<Severity, Long> violations;

	/**
	 * The list of analyzed files
	 */
	protected List<AnalyzedFile> files;

	public long getNbFiles() {
		return nbFiles;
	}

	public void setNbFiles(long nbFiles) {
		this.nbFiles = nbFiles;
	}

	public Map<Severity, Long> getViolations() {
		return violations;
	}

	public void setViolations(Map<Severity, Long> violations) {
		this.violations = violations;
	}
}
package fr.sii.sonar.report.core.duplication.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import fr.sii.sonar.report.core.common.domain.Report;

/**
 * A report that provides information about code duplication.
 * It provides the following information for each duplicated code block:
 * <ul>
 *  <li>The path to the source file</li>
 *  <li>The start line for the duplicated code</li>
 *  <li>The end line for the duplicated code</li>
 *  <li>The duplicated code if available</li>
 * </ul>
 * 
 * The code blocks are grouped by identical duplicated blocks.
 * 
 * @author Aurélien Baudet
 *
 */
public class DuplicationReport implements Report {
	/**
	 * Group for duplications of same code
	 */
	private List<DuplicationGroup> duplicationGroups;

	public DuplicationReport(DuplicationGroup... duplicationGroups) {
		this(new ArrayList<DuplicationGroup>(Arrays.asList(duplicationGroups)));
	}

	public DuplicationReport(List<DuplicationGroup> duplicationGroups) {
		super();
		this.duplicationGroups = duplicationGroups;
	}

	public List<DuplicationGroup> getDuplicationGroups() {
		return duplicationGroups;
	}

	public void setDuplicationGroups(List<DuplicationGroup> duplicationGroups) {
		this.duplicationGroups = duplicationGroups;
	}

	public void addDuplicationGroup(DuplicationGroup group) {
		duplicationGroups.add(group);
	}
	
	@Override
	public String toString() {
		return StringUtils.join(duplicationGroups, "\r\n");
	}
}

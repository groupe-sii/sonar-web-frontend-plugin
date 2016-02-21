package fr.sii.sonar.report.core.duplication.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.sonar.report.core.common.util.compat.StringUtils;


/**
 * Group same duplicated code and provide information about context for each duplicated code block:
 * <ul>
 *  <li>The path to the source file</li>
 *  <li>The start line for the duplicated code</li>
 *  <li>The end line for the duplicated code</li>
 *  <li>The duplicated code if available</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class DuplicationGroup {
	/**
	 * The list of duplicated code blocks
	 */
	private List<DuplicatedBlock> duplicatedBlocks;

	public DuplicationGroup(List<DuplicatedBlock> duplicatedBlocks) {
		super();
		this.duplicatedBlocks = duplicatedBlocks;
	}
	
	public DuplicationGroup(DuplicatedBlock... duplicatedBlocks) {
		this(new ArrayList<DuplicatedBlock>(Arrays.asList(duplicatedBlocks)));
	}
	
	public void addDuplicatedBlock(DuplicatedBlock block) {
		duplicatedBlocks.add(block);
	}

	public List<DuplicatedBlock> getDuplicatedBlocks() {
		return duplicatedBlocks;
	}

	public void setDuplicatedBlocks(List<DuplicatedBlock> duplicatedBlocks) {
		this.duplicatedBlocks = duplicatedBlocks;
	}

	@Override
	public String toString() {
		return "\t"+StringUtils.join(duplicatedBlocks, "\r\n\t")+"\r\n";
	}
}

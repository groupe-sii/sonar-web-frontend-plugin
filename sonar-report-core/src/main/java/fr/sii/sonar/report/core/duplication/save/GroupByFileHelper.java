package fr.sii.sonar.report.core.duplication.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.sii.sonar.report.core.duplication.domain.DuplicatedBlock;
import fr.sii.sonar.report.core.duplication.domain.DuplicationGroup;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

/**
 * Utility class that extracts duplication information and group it by file
 * 
 * @author Aurélien Baudet
 *
 */
public class GroupByFileHelper {
	/**
	 * Transform the report to group duplicated blocks by file. This is helpful
	 * in order to have a general view for each file about duplications
	 * 
	 * @param report
	 *            the report that contains duplication information
	 * @return the list of files that contain duplications with information
	 *         about the total number of duplicated lines and the list of
	 *         duplicated blocks
	 */
	public static List<DuplicationFileInformation> group(DuplicationReport report) {
		Map<String, List<DuplicatedBlock>> map = new HashMap<String, List<DuplicatedBlock>>();
		for (DuplicationGroup group : report.getDuplicationGroups()) {
			for (DuplicatedBlock block : group.getDuplicatedBlocks()) {
				String sourceFile = block.getSourceFile();
				if (!map.containsKey(sourceFile)) {
					map.put(sourceFile, new ArrayList<DuplicatedBlock>());
				}
				map.get(sourceFile).add(block);
			}
		}
		List<DuplicationFileInformation> files = new ArrayList<DuplicationFileInformation>(map.values().size());
		for (List<DuplicatedBlock> blocks : map.values()) {
			files.add(new DuplicationFileInformation(blocks.get(0).getSourceFile(), countDuplicatedLines(blocks), blocks));
		}
		return files;
	}

	/**
	 * Count the total number of duplicated lines for the provided blocks
	 * 
	 * @param blocks
	 *            the list of blocks used to count duplicated lines
	 * @return the total number of duplicated lines
	 */
	public static int countDuplicatedLines(List<DuplicatedBlock> blocks) {
		int duplicatedLines = 0;
		// count total number of duplicated lines
		for (DuplicatedBlock block : blocks) {
			duplicatedLines += block.getNumLines();
		}
		return duplicatedLines;
	}
}

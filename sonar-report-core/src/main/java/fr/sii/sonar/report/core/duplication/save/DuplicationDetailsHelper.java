package fr.sii.sonar.report.core.duplication.save;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.resources.Project;

import fr.sii.sonar.report.core.common.exception.DuplicationException;
import fr.sii.sonar.report.core.common.exception.KeyException;
import fr.sii.sonar.report.core.common.util.ResourceUtil;
import fr.sii.sonar.report.core.common.util.compat.StringEscapeUtils;
import fr.sii.sonar.report.core.duplication.domain.DuplicatedBlock;
import fr.sii.sonar.report.core.duplication.domain.DuplicationGroup;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

/**
 * Utility class used for duplication details generation
 * 
 * @author Aurélien Baudet
 *
 */
public class DuplicationDetailsHelper {
	/**
	 * In Sonar version < 4.5, duplication details is store in xml. This method
	 * transforms duplication information into an xml string.
	 * 
	 * @param project
	 *            the project under analysis
	 * @param fileSystem
	 *            the utility used to get Sonar folders
	 * @param report
	 *            the duplication report
	 * @param file
	 *            the information about duplication for the file
	 * @return the xml string to save in Sonar
	 * @throws DuplicationException
	 *             when the duplication details couldn't be generated for the file
	 */
	public static String toXml(Project project, FileSystem fileSystem, DuplicationReport report, DuplicationFileInformation file) throws DuplicationException {
		try {
			StringBuilder xml = new StringBuilder();
			xml.append("<duplications>");
			boolean hasDuplications = false;
			for (DuplicationGroup duplication : report.getDuplicationGroups()) {
				StringBuilder group = new StringBuilder();
				boolean containsFile = false;
				group.append("<g>");
				for (DuplicatedBlock part : duplication.getDuplicatedBlocks()) {
					group.append("<b s=\"").append(part.getStartLine())
							.append("\" l=\"").append(part.getNumLines())
							.append("\" r=\"").append(StringEscapeUtils.escapeXml(ResourceUtil.getKey(project, part.getSourceFile(), fileSystem))).append("\"/>");
					if (!containsFile) {
						containsFile = file.getPath().equals(part.getSourceFile());
					}
				}
				group.append("</g>");
				if (containsFile) {
					hasDuplications = true;
					xml.append(group);
				}
			}
			xml.append("</duplications>");
			return hasDuplications ? xml.toString() : null;
		} catch(KeyException e) {
			throw new DuplicationException("failed to generate duplication details for "+file.getPath());
		}
	}

}

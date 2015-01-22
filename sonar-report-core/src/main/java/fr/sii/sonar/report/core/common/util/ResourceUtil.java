package fr.sii.sonar.report.core.common.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.report.core.common.exception.KeyException;

/**
 * An utility class that helps working with Sonar resources
 * 
 * @author Aurélien Baudet
 *
 */
public class ResourceUtil {
	/**
	 * Generate a resource key for the provided absolute file path. It only uses
	 * the base directory as source for relative path computation
	 * 
	 * Using a plugin with some version and Sonar with another version will
	 * result in a null key provided by a {@link File} instance. So we generate
	 * it using the current project key and the relative path to the project.
	 * 
	 * @param project
	 *            the project that contains the file
	 * @param path
	 *            the absolute path to the file
	 * @param fileSystem
	 *            the utility used to get Sonar folders
	 * @return the resource key usable by Sonar
	 * @throws KeyException
	 *             when the key couldn't be generated
	 */
	public static String getKey(Project project, String path, ModuleFileSystem fileSystem) throws KeyException {
		return getKey(project, path, fileSystem.baseDir());
	}

	/**
	 * Generate a resource key for the provided file path.
	 * 
	 * Using a plugin with some version and Sonar with another version will
	 * result in a null key provided by a {@link File} instance. So we generate
	 * it using the current project key and the relative path to the project.
	 * 
	 * @param project
	 *            the project that contains the file
	 * @param path
	 *            the absolute path to the file
	 * @param possibleParents
	 *            a list of directories to search file into ordered by priority
	 * @return the resource key usable by Sonar
	 * @throws KeyException
	 *             when the key couldn't be generated
	 */
	public static String getKey(Project project, String path, java.io.File... possibleParents) throws KeyException {
		return getKey(project, path, Arrays.asList(possibleParents));
	}

	/**
	 * Generate a resource key for the provided file path.
	 * 
	 * Using a plugin with some version and Sonar with another version will
	 * result in a null key provided by a {@link File} instance. So we generate
	 * it using the current project key and the relative path to the project.
	 * 
	 * @param project
	 *            the project that contains the file
	 * @param path
	 *            the absolute path to the file
	 * @param possibleParents
	 *            a list of directories to search file into ordered by priority
	 * @return the resource key usable by Sonar
	 * @throws KeyException
	 *             when the key couldn't be generated
	 */
	public static String getKey(Project project, String path, List<java.io.File> possibleParents) throws KeyException {
		try {
			return project.getKey() + ":" + FileUtil.getRelativePath(path, possibleParents);
		} catch(IOException e) {
			throw new KeyException("failed to generate key for file "+path, e);
		}
	}

}

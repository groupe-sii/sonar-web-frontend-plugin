package fr.sii.sonar.report.core.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.resources.File;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.SaveException;

/**
 * Utility that helps to find a file either into Sonar system abstraction or
 * into real file system.
 * 
 * @author Aurélien Baudet
 *
 */
public class FileUtil {
	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

//	/**
//	 * Get the list of directories for base, sources and tests.
//	 * 
//	 * @param fileSystem
//	 *            Sonar abstraction of the file system that give pointers to
//	 *            useful directories manipulated by Sonar
//	 * @return the list of directories for base, sources and tests
//	 */
//	public static List<java.io.File> getSrcAndTestParents(FileSystem fileSystem) {
//		List<java.io.File> parents = new ArrayList<java.io.File>();
//		parents.add(fileSystem.baseDir());
//		parents.addAll(fileSystem.sourceDirs());
//		parents.addAll(fileSystem.testDirs());
//		return parents;
//	}
//
//	/**
//	 * Get the list of directories for base, tests and sources.
//	 * 
//	 * @param fileSystem
//	 *            Sonar abstraction of the file system that give pointers to
//	 *            useful directories manipulated by Sonar
//	 * @return the list of directories for base, tests and sources
//	 */
//	public static List<java.io.File> getTestAndSrcParents(FileSystem fileSystem) {
//		List<java.io.File> parents = new ArrayList<java.io.File>();
//		parents.add(fileSystem.baseDir());
//		parents.addAll(fileSystem.testDirs());
//		parents.addAll(fileSystem.sourceDirs());
//		return parents;
//	}

//	/**
//	 * Get the sonar file from the provided absolute or relative path. The
//	 * relative path provided in a report may represent the path either relative
//	 * to the project, relative to a source directory or relative to a test
//	 * directory. This helper searches into:
//	 * <ul>
//	 * <li>base directory of the project</li>
//	 * <li>every source directory of the project</li>
//	 * <li>every test directory of the project</li>
//	 * </ul>
//	 * The first file that exists on the system file is the returned one. If the
//	 * path is absolute, then it is returned if it really exists.
//	 * 
//	 * @param path
//	 *            The absolute or relative path to the file
//	 * @param fileSystem
//	 *            Sonar abstraction of the file system that give pointers to
//	 *            useful directories manipulated by Sonar
//	 * @return the sonar source file if found, null otherwise
//	 */
//	public static File getSonarFile(String path, FileSystem fileSystem) {
//		return getSonarFile(path, getSrcAndTestParents(fileSystem));
//	}

//	/**
//	 * Get the sonar file from the provided absolute or relative path. The
//	 * relative path provided in a report may represent either the path relative
//	 * to the project, relative to a source directory or relative to a test
//	 * directory. This helper searches into the provided list of parents. The
//	 * first file that exists on the system file is the returned one. If the
//	 * path is absolute, then it is returned if it really exists.
//	 * 
//	 * @param path
//	 *            The absolute or relative path to the file
//	 * @param possibleParents
//	 *            The list of directories that may contain the file
//	 * @return the sonar source file
//	 */
//	public static File getSonarFile(String path, List<java.io.File> possibleParents) {
//		return File.fromIOFile(getSystemFile(path, possibleParents), possibleParents);
//	}

//	/**
//	 * Get the sonar file from the provided absolute or relative path. The
//	 * relative path provided in a report may represent either the path relative
//	 * to the project, relative to a source directory or relative to a test
//	 * directory. This helper searches into the provided list of parents. The
//	 * first file that exists on the system file is the returned one. If the
//	 * path is absolute, then it is returned if it really exists.
//	 * 
//	 * @param path
//	 *            The absolute or relative path to the file
//	 * @param possibleParents
//	 *            The list of directories that may contain the file
//	 * @return the sonar source file
//	 */
//	public static File getSonarFile(String path, java.io.File... possibleParents) {
//		return getSonarFile(path, Arrays.asList(possibleParents));
//	}

	/**
	 * Search for real file on the file system using the provided absolute or
	 * relative path. The relative path provided in a report may represent the
	 * path either relative to the project, relative to a source directory or
	 * relative to a test directory. This helper searches into the provided list
	 * of parents. The first file that exists on the system file is the returned
	 * one. If the path is absolute, then it is returned if it really exists.
	 * 
	 * @param path
	 *            The absolute or relative path to the file
	 * @param possibleParents
	 *            The list of directories that may contain the file
	 * @return the system file if found, null otherwise
	 */
	public static java.io.File getSystemFile(String path, List<java.io.File> possibleParents) {
		for (java.io.File parent : possibleParents) {
			java.io.File file = new java.io.File(parent, path);
			if (file.exists()) {
				return file;
			}
		}
		java.io.File absoluteFile = new java.io.File(path);
		return absoluteFile.exists() ? absoluteFile : null;
	}

//	/**
//	 * Search for real file on the file system using the provided absolute or
//	 * relative path. The relative path provided in a report may represent the
//	 * path either relative to the project, relative to a source directory or
//	 * relative to a test directory. This helper searches into:
//	 * <ul>
//	 * <li>base directory of the project</li>
//	 * <li>every source directory of the project</li>
//	 * <li>every test directory of the project</li>
//	 * </ul>
//	 * The first file that exists on the system file is the returned one.
//	 * 
//	 * @param path
//	 *            The relative path to the file
//	 * @param fileSystem
//	 *            Sonar abstraction of the file system that give pointers to
//	 *            useful directories manipulated by Sonar
//	 * @return the system file if found, null otherwise
//	 */
//	public static java.io.File getSystemFile(String path, FileSystem fileSystem) {
//		return getSystemFile(path, getSrcAndTestParents(fileSystem));
//	}

	/**
	 * Helper function that checks if the sonar file exists. If the file doesn't
	 * exist, and if the configuration value for missing file is true, then an
	 * exception is thrown. If the file doesn't exist and if the configuration
	 * value for missing file is false, then an log is written to indicate that
	 * the file is missing.
	 * 
	 * @param pluginContext
	 *            The plugin context used to get the configuration value
	 * @param sonarFile
	 *            The sonar file (may be null)
	 * @param path
	 *            The path to the real file on the system (may point to a file
	 *            that doesn't exist)
	 * @param message
	 *            An additional message to add in the log
	 * @throws SaveException
	 *             when the file is missing and the plugin is configured to fail
	 * @return true if the file exists, false if the file doesn't exist and the
	 *         plugin is configured to not fail
	 */
	public static boolean checkMissing(PluginContext pluginContext, File sonarFile, String path, String message) throws SaveException {
		if (sonarFile == null) {
			if (pluginContext.getSettings().getBoolean(pluginContext.getConstants().getMissingFileFailKey())) {
				throw new SaveException("The file " + path + " doesn't exist");
			} else {
				LOG.warn("The file " + path + " doesn't exist. " + message);
			}
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Helper function that checks if the sonar file exists. If the file doesn't
	 * exist, and if the configuration value for missing file is true, then an
	 * exception is thrown. If the file doesn't exist and if the configuration
	 * value for missing file is false, then an log is written to indicate that
	 * the file is missing.
	 * 
	 * @param pluginContext
	 *            The plugin context used to get the configuration value
	 * @param sonarFile
	 *            The sonar file (may be null)
	 * @param path
	 *            The path to the real file on the system (may point to a file
	 *            that doesn't exist)
	 * @param message
	 *            An additional message to add in the log
	 * @throws SaveException
	 *             when the file is missing and the plugin is configured to fail
	 * @return true if the file exists, false if the file doesn't exist and the
	 *         plugin is configured to not fail
	 */
	public static boolean checkMissing(PluginContext pluginContext, InputFile sonarFile, String path, String message) throws SaveException {
		if (sonarFile == null) {
			if (pluginContext.getSettings().getBoolean(pluginContext.getConstants().getMissingFileFailKey())) {
				throw new SaveException("The file " + path + " doesn't exist");
			} else {
				LOG.warn("The file " + path + " doesn't exist. " + message);
			}
			return false;
		} else {
			return true;
		}
	}

//	/**
//	 * Get the relative path from the provided path. The path will be compared
//	 * to base, source and test directories. If the file is relative to one of
//	 * these parents, the relative path will be returned. If the path is already
//	 * relative, then the path is directly returned.
//	 * 
//	 * @param path
//	 *            the path to make relative if not
//	 * @param fileSystem
//	 *            the Sonar file system used to get base, source and test
//	 *            directories
//	 * @return the relative path
//	 * @throws IOException
//	 *             if the path couldn't be read on file system
//	 */
//	public static String getRelativePath(String path, FileSystem fileSystem) throws IOException {
//		return getRelativePath(path, getSrcAndTestParents(fileSystem));
//	}

	/**
	 * Get the relative path from the provided path. The path will be compared
	 * to provided parent directories. If the file is relative to one of these
	 * parents, the relative path will be returned. If the path is already
	 * relative, then the path is directly returned.
	 * 
	 * @param path
	 *            the path to make relative if not
	 * @param possibleParents
	 *            the parent directories that may contain the file
	 * @return the relative path
	 * @throws IOException
	 *             if the path couldn't be read on file system
	 */
	public static String getRelativePath(String path, java.io.File... possibleParents) throws IOException {
		return getRelativePath(path, Arrays.asList(possibleParents));
	}

	/**
	 * Get the relative path from the provided path. The path will be compared
	 * to provided parent directories. If the file is relative to one of these
	 * parents, the relative path will be returned. If the path is already
	 * relative, then the path is directly returned.
	 * 
	 * @param path
	 *            the path to make relative if not
	 * @param possibleParents
	 *            the parent directories that may contain the file
	 * @return the relative path
	 * @throws IOException
	 *             if the path couldn't be read on file system
	 */
	public static String getRelativePath(String path, List<java.io.File> possibleParents) throws IOException {
		java.io.File file = new java.io.File(path);
		if (file.isAbsolute()) {
			for (java.io.File parent : possibleParents) {
				if (file.getCanonicalPath().contains(parent.getCanonicalPath())) {
					return file.getCanonicalPath().replaceFirst(".*" + parent.getCanonicalPath() + "/?(.+$)", "$1");
				}
			}
			return null;
		} else {
			return path;
		}
	}

	public static InputFile getInputFile(FileSystem fileSystem, String path) {
		// try to find the file directly using the provided path (absolute or relative)
		InputFile file = fileSystem.inputFile(fileSystem.predicates().hasPath(path));
		// if not found, maybe the path starts with '/'
		// in this case, Sonar thinks it's an absolute path => manually try relative
		if(file==null && path.startsWith("/")) {
			file = fileSystem.inputFile(fileSystem.predicates().hasRelativePath(path.substring(1)));
		}
		// if not found, try to search it everywhere
		if(file==null) {
			file = fileSystem.inputFile(fileSystem.predicates().matchesPathPattern("**"+path));
		}
		return file;
	}
}

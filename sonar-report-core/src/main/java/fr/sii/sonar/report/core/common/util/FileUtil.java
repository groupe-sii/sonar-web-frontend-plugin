package fr.sii.sonar.report.core.common.util;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.resources.File;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

/**
 * Utility that helps to find a file either into Sonar system abstraction or
 * into real file system.
 * 
 * @author Aurélien Baudet
 *
 */
public class FileUtil {
	/**
	 * Get the list of directories for base, sources and tests.
	 * 
	 * @param fileSystem
	 *            Sonar abstraction of the file system that give pointers to
	 *            useful directories manipulated by Sonar
	 * @return the list of directories for base, sources and tests
	 */
	public static List<java.io.File> getSrcAndTestParents(ModuleFileSystem fileSystem) {
		List<java.io.File> parents = new ArrayList<java.io.File>();
		parents.add(fileSystem.baseDir());
		parents.addAll(fileSystem.sourceDirs());
		parents.addAll(fileSystem.testDirs());
		return parents;
	}

	/**
	 * Get the list of directories for base, tests and sources.
	 * 
	 * @param fileSystem
	 *            Sonar abstraction of the file system that give pointers to
	 *            useful directories manipulated by Sonar
	 * @return the list of directories for base, tests and sources
	 */
	public static List<java.io.File> getTestAndSrcParents(ModuleFileSystem fileSystem) {
		List<java.io.File> parents = new ArrayList<java.io.File>();
		parents.add(fileSystem.baseDir());
		parents.addAll(fileSystem.testDirs());
		parents.addAll(fileSystem.sourceDirs());
		return parents;
	}

	/**
	 * Get the sonar file from the provided relative path. The relative path
	 * provided in a report may represent the path either relative to the
	 * project, relative to a source directory or relative to a test directory.
	 * This helper searches into:
	 * <ul>
	 * <li>base directory of the project</li>
	 * <li>every source directory of the project</li>
	 * <li>every test directory of the project</li>
	 * </ul>
	 * The first file that exists on the system file is the returned one.
	 * 
	 * @param path
	 *            The relative path to the file
	 * @param fileSystem
	 *            Sonar abstraction of the file system that give pointers to
	 *            useful directories manipulated by Sonar
	 * @return the sonar source file if found, null otherwise
	 */
	public static File getSonarFile(String path, ModuleFileSystem fileSystem) {
		return getSonarFile(path, getSrcAndTestParents(fileSystem));
	}

	/**
	 * Get the sonar file from the provided relative path. The relative path
	 * provided in a report may represent either the path relative to the
	 * project, relative to a source directory or relative to a test directory.
	 * This helper searches into the provided list of parents. The first file
	 * that exists on the system file is the returned one.
	 * 
	 * @param path
	 *            The relative path to the file
	 * @param possibleParents
	 *            The list of directories that may contain the file
	 * @return the sonar source file
	 */
	public static File getSonarFile(String path, List<java.io.File> possibleParents) {
		return File.fromIOFile(getSystemFile(path, possibleParents), possibleParents);
	}

	/**
	 * Search for real file on the file system using the provided relative path.
	 * The relative path provided in a report may represent the path either
	 * relative to the project, relative to a source directory or relative to a
	 * test directory. This helper searches into the provided list of parents.
	 * The first file that exists on the system file is the returned one.
	 * 
	 * @param path
	 *            The relative path to the file
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
		return null;
	}

	/**
	 * Search for real file on the file system using the provided relative path.
	 * The relative path provided in a report may represent the path either
	 * relative to the project, relative to a source directory or relative to a
	 * test directory. This helper searches into:
	 * <ul>
	 * <li>base directory of the project</li>
	 * <li>every source directory of the project</li>
	 * <li>every test directory of the project</li>
	 * </ul>
	 * The first file that exists on the system file is the returned one.
	 * 
	 * @param path
	 *            The relative path to the file
	 * @param fileSystem
	 *            Sonar abstraction of the file system that give pointers to
	 *            useful directories manipulated by Sonar
	 * @return the system file if found, null otherwise
	 */
	public static java.io.File getSystemFile(String path, ModuleFileSystem fileSystem) {
		return getSystemFile(path, getSrcAndTestParents(fileSystem));
	}

}

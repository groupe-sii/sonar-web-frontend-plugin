package fr.sii.sonar.report.core.common;

import org.sonar.api.BatchExtension;
import org.sonar.api.ServerExtension;

/**
 * Helper interface used to access plugins constants. Constants are defined in a
 * static final way that makes impossible to directly access them in an
 * implementation. A specific class will then be used in this implementation.
 * But in our case, we need to have a generic implementation that can use some
 * constant values. These constant values can be provided by 1 or more plugins.
 * So declaring an interface that provide values needed by the implementation
 * and implementing one the interface for each plugin allows us to declare
 * constants (static final) with accessors available at runtime.
 * 
 * @author Aurélien Baudet
 *
 */
public interface ReportConstants extends BatchExtension, ServerExtension {
	/**
	 * The key for the configuration entry in sonar properties to indicate the
	 * path to the report
	 */
	public String getReportPathKey();

	/**
	 * The key for the configuration entry in sonar properties to indicate what
	 * to do if a sonar source file doesn't exist
	 */
	public String getMissingFileFailKey();
}

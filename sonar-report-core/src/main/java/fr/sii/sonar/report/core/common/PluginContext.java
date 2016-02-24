package fr.sii.sonar.report.core.common;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.batch.index.BatchComponentCache;
import org.sonar.batch.report.ReportPublisher;

/**
 * Class that just groups resources that are needed by sonar plugins.
 * 
 * @author Aurélien Baudet
 *
 */
public class PluginContext {
	private final PluginDependencies pluginDependencies;
	private final ReportConstants constants;
	
	
	public PluginContext(ReportConstants constants, PluginDependencies pluginDependencies) {
		super();
		this.pluginDependencies = pluginDependencies;
		this.constants = constants;
	}


	public Settings getSettings() {
		return pluginDependencies.getSettings();
	}


	public ResourcePerspectives getResourcePerspective() {
		return pluginDependencies.getResourcePerspective();
	}


	public RuleFinder getRuleFinder() {
		return pluginDependencies.getRuleFinder();
	}


	public FileSystem getFilesystem() {
		return pluginDependencies.getFilesystem();
	}


	public ReportConstants getConstants() {
		return constants;
	}
	
	public BatchComponentCache getBatchComponentCache() {
		return pluginDependencies.getBatchComponentCache();
	}
	
	public ReportPublisher getReportPublisher() {
		return pluginDependencies.getReportPublisher();
	}
}

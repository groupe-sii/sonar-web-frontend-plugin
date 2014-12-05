package fr.sii.sonar.report.core.common;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

/**
 * Class that just groups resources that are needed by sonar plugins.
 * 
 * @author Aurélien Baudet
 *
 */
public class PluginContext {
	private final Settings settings;
	private final ResourcePerspectives resourcePerspective;
	private final RuleFinder ruleFinder;
	private final ModuleFileSystem filesystem;
	private final ReportConstants constants;
	
	
	public PluginContext(Settings settings, ResourcePerspectives resourcePerspective, RuleFinder ruleFinder, ModuleFileSystem filesystem, ReportConstants constants) {
		super();
		this.settings = settings;
		this.resourcePerspective = resourcePerspective;
		this.ruleFinder = ruleFinder;
		this.filesystem = filesystem;
		this.constants = constants;
	}


	public Settings getSettings() {
		return settings;
	}


	public ResourcePerspectives getResourcePerspective() {
		return resourcePerspective;
	}


	public RuleFinder getRuleFinder() {
		return ruleFinder;
	}


	public ModuleFileSystem getFilesystem() {
		return filesystem;
	}


	public ReportConstants getConstants() {
		return constants;
	}
}

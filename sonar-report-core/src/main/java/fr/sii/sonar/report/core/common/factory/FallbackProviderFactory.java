package fr.sii.sonar.report.core.common.factory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sii.sonar.report.core.common.domain.Report;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.provider.FallbackProvider;
import fr.sii.sonar.report.core.common.provider.Provider;

/**
 * Factory that creates a provider that is able to load a report that may be of multiple types
 * 
 * @author Aurélien Baudet
 *
 */
public class FallbackProviderFactory<R extends Report> implements ProviderFactory<R> {

	private List<Class<? extends Provider<R>>> classes;
	
	public FallbackProviderFactory(Class<? extends Provider<R>>... classes) {
		this(Arrays.asList(classes));
	}
	
	public FallbackProviderFactory(List<Class<? extends Provider<R>>> classes) {
		super();
		this.classes = classes;
	}


	public Provider<R> create(File reportFile) throws CreateException {
		try {
			List<Provider<R>> instances = new ArrayList<Provider<R>>(classes.size());
			for(Class<? extends Provider<R>> clazz : classes) {
				instances.add(clazz.getConstructor(File.class).newInstance(reportFile));
			}
			return new FallbackProvider<R>(instances);
		} catch (InstantiationException e) {
			throw new CreateException("failed to load report file " + reportFile.getAbsolutePath(), e);
		} catch (IllegalAccessException e) {
			throw new CreateException("failed to load report file " + reportFile.getAbsolutePath(), e);
		} catch (IllegalArgumentException e) {
			throw new CreateException("failed to load report file " + reportFile.getAbsolutePath(), e);
		} catch (InvocationTargetException e) {
			throw new CreateException("failed to load report file " + reportFile.getAbsolutePath(), e);
		} catch (NoSuchMethodException e) {
			throw new CreateException("failed to load report file " + reportFile.getAbsolutePath(), e);
		} catch (SecurityException e) {
			throw new CreateException("failed to load report file " + reportFile.getAbsolutePath(), e);
		}
	}

}

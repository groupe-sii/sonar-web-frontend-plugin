package fr.sii.sonar.report.core.test.factory;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.core.test.save.TestSaver;

/**
 * Factory that creates an instance of {@link TestSaver} for generate test
 * result measures
 * 
 * @author Aurélien Baudet
 *
 */
public class TestSaverFactory implements SaverFactory<TestReport> {

	public Saver<TestReport> create(PluginContext pluginContext) throws CreateException {
		return new TestSaver(pluginContext);
	}

}

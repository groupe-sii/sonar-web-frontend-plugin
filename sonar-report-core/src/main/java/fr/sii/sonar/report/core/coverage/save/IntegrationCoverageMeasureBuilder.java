package fr.sii.sonar.report.core.coverage.save;

import java.util.ArrayList;
import java.util.Collection;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;

public class IntegrationCoverageMeasureBuilder extends DelegateAndConvertMeasureBuilder {

	@Override
	protected Collection<Measure> convert(Collection<Measure> unitMeasures) {
		Collection<Measure> measures = new ArrayList<Measure>(unitMeasures.size());
		for (Measure m : unitMeasures) {
			measures.add(convertForIT(m));
		}
		return measures;
	}

	/**
	 * Copied from Java plugin... This is how they handle it...
	 * 
	 * @param measure
	 * @return converted measure
	 */
	private Measure convertForIT(Measure measure) {
		Measure itMeasure = null;
		if (CoreMetrics.LINES_TO_COVER.equals(measure.getMetric())) {
			itMeasure = new Measure(CoreMetrics.IT_LINES_TO_COVER, measure.getValue());

		} else if (CoreMetrics.UNCOVERED_LINES.equals(measure.getMetric())) {
			itMeasure = new Measure(CoreMetrics.IT_UNCOVERED_LINES, measure.getValue());

		} else if (CoreMetrics.COVERAGE_LINE_HITS_DATA.equals(measure.getMetric())) {
			itMeasure = new Measure(CoreMetrics.IT_COVERAGE_LINE_HITS_DATA, measure.getData());

		} else if (CoreMetrics.CONDITIONS_TO_COVER.equals(measure.getMetric())) {
			itMeasure = new Measure(CoreMetrics.IT_CONDITIONS_TO_COVER, measure.getValue());

		} else if (CoreMetrics.UNCOVERED_CONDITIONS.equals(measure.getMetric())) {
			itMeasure = new Measure(CoreMetrics.IT_UNCOVERED_CONDITIONS, measure.getValue());

		} else if (CoreMetrics.COVERED_CONDITIONS_BY_LINE.equals(measure.getMetric())) {
			itMeasure = new Measure(CoreMetrics.IT_COVERED_CONDITIONS_BY_LINE, measure.getData());

		} else if (CoreMetrics.CONDITIONS_BY_LINE.equals(measure.getMetric())) {
			itMeasure = new Measure(CoreMetrics.IT_CONDITIONS_BY_LINE, measure.getData());
		}
		return itMeasure;
	}

}

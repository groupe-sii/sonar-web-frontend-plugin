package fr.sii.sonar.report.core.coverage.save;

import java.util.Collection;

import org.sonar.api.measures.Measure;


public class UnitCoverageMeasureBuilder extends DelegateAndConvertMeasureBuilder {

	@Override
	protected Collection<Measure> convert(Collection<Measure> measures) {
		return measures;
	}

}

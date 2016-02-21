package fr.sii.sonar.report.core.coverage.save;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.sonar.api.measures.CoverageMeasuresBuilder;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.Metric;

public abstract class DelegateAndConvertMeasureBuilder implements CoverageMeasureBuilder {

	private CoverageMeasuresBuilder delegate;

	public DelegateAndConvertMeasureBuilder() {
		this.delegate = CoverageMeasuresBuilder.create();
	}
	
	@Override
	public CoverageMeasureBuilder setHits(int lineId, int hits) {
		delegate.setHits(lineId, hits);
		return this;
	}

	@Override
	public CoverageMeasureBuilder setConditions(int lineId, int conditions, int coveredConditions) {
		delegate.setConditions(lineId, conditions, coveredConditions);
		return this;
	}

	@Override
	public Collection<Measure> createMeasures() {
		return convert(delegate.createMeasures());
	}

	protected abstract Collection<Measure> convert(Collection<Measure> createMeasures);

	@Override
	public CoverageMeasureBuilder reset() {
		delegate.reset();
		return this;
	}

}

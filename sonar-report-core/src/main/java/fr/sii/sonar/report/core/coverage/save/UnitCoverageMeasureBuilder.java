package fr.sii.sonar.report.core.coverage.save;

import java.util.Collection;

import org.sonar.api.measures.CoverageMeasuresBuilder;
import org.sonar.api.measures.Measure;

public class UnitCoverageMeasureBuilder implements CoverageMeasureBuilder {

	private CoverageMeasuresBuilder delegate;

	public UnitCoverageMeasureBuilder() {
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
		return delegate.createMeasures();
	}

	@Override
	public CoverageMeasureBuilder reset() {
		delegate.reset();
		return this;
	}

}

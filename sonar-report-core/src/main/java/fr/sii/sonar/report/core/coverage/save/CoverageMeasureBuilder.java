package fr.sii.sonar.report.core.coverage.save;

import java.util.Collection;

import org.sonar.api.measures.CoverageMeasuresBuilder;
import org.sonar.api.measures.Measure;

/**
 * Helper interface to generate coverage measures. Sonar provides {@link CoverageMeasuresBuilder} but it only works for unit coverage.
 * This interface makes an adapter to it for unit coverage and allows to also provide implementation for integration and overall coverage.
 * 
 * @author Aurélien Baudet
 *
 */
public interface CoverageMeasureBuilder {
	public CoverageMeasureBuilder setHits(int lineId, int hits);
	
	public CoverageMeasureBuilder setConditions(int lineId, int conditions, int coveredConditions);
	
	public Collection<Measure> createMeasures();
	
	public CoverageMeasureBuilder reset();
}

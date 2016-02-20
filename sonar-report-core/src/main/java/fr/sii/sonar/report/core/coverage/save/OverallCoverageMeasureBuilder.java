package fr.sii.sonar.report.core.coverage.save;

import java.util.Collection;
import java.util.SortedMap;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.PersistenceMode;
import org.sonar.api.utils.KeyValueFormat;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class OverallCoverageMeasureBuilder implements CoverageMeasureBuilder {
	private int totalCoveredLines = 0, totalConditions = 0, totalCoveredConditions = 0;
	private SortedMap<Integer, Integer> hitsByLine = Maps.newTreeMap();
	private SortedMap<Integer, Integer> conditionsByLine = Maps.newTreeMap();
	private SortedMap<Integer, Integer> coveredConditionsByLine = Maps.newTreeMap();

	@Override
	public CoverageMeasureBuilder setHits(int lineId, int hits) {
		if (!hitsByLine.containsKey(lineId)) {
			hitsByLine.put(lineId, hits);
			if (hits > 0) {
				totalCoveredLines += 1;
			}
		}
		return this;
	}

	@Override
	public CoverageMeasureBuilder setConditions(int lineId, int conditions, int coveredConditions) {
		if (conditions > 0 && !conditionsByLine.containsKey(lineId)) {
			totalConditions += conditions;
			totalCoveredConditions += coveredConditions;
			conditionsByLine.put(lineId, conditions);
			coveredConditionsByLine.put(lineId, coveredConditions);
		}
		return this;
	}

	@Override
	public Collection<Measure> createMeasures() {
		Collection<Measure> measures = Lists.newArrayList();
		if (getLinesToCover() > 0) {
			measures.add(new Measure(CoreMetrics.OVERALL_LINES_TO_COVER, (double) getLinesToCover()));
			measures.add(new Measure(CoreMetrics.OVERALL_UNCOVERED_LINES, (double) (getLinesToCover() - getCoveredLines())));
			measures.add(new Measure(CoreMetrics.OVERALL_COVERAGE_LINE_HITS_DATA).setData(KeyValueFormat.format(hitsByLine)).setPersistenceMode(PersistenceMode.DATABASE));
		}
		if (getConditions() > 0) {
			measures.add(new Measure(CoreMetrics.OVERALL_CONDITIONS_TO_COVER, (double) getConditions()));
			measures.add(new Measure(CoreMetrics.OVERALL_UNCOVERED_CONDITIONS, (double) (getConditions() - getCoveredConditions())));
			measures.add(createConditionsByLine());
			measures.add(createCoveredConditionsByLine());
		}
		return measures;
	}

	@Override
	public CoverageMeasureBuilder reset() {
		totalCoveredLines = 0;
		totalConditions = 0;
		totalCoveredConditions = 0;
		hitsByLine.clear();
		conditionsByLine.clear();
		coveredConditionsByLine.clear();
		return this;
	}

	public int getCoveredLines() {
		return totalCoveredLines;
	}

	public int getLinesToCover() {
		return hitsByLine.size();
	}

	public int getConditions() {
		return totalConditions;
	}

	public int getCoveredConditions() {
		return totalCoveredConditions;
	}

	private Measure createCoveredConditionsByLine() {
		return new Measure(CoreMetrics.OVERALL_COVERED_CONDITIONS_BY_LINE).setData(KeyValueFormat.format(coveredConditionsByLine)).setPersistenceMode(PersistenceMode.DATABASE);
	}

	private Measure createConditionsByLine() {
		return new Measure(CoreMetrics.OVERALL_CONDITIONS_BY_LINE).setData(KeyValueFormat.format(conditionsByLine)).setPersistenceMode(PersistenceMode.DATABASE);
	}

}

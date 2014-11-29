package fr.sii.sonar.coverage.lcov.domain;

import java.util.ArrayList;
import java.util.List;

public class LcovInfo<D> {
	private int found;
	
	private int hit;
	
	private List<D> details;

	public LcovInfo() {
		this(0, 0, new ArrayList<D>());
	}
	
	public LcovInfo(int found, int hit, List<D> details) {
		super();
		this.found = found;
		this.hit = hit;
		this.details = details;
	}

	public int getFound() {
		return found;
	}

	public int getHit() {
		return hit;
	}

	public List<D> getDetails() {
		return details;
	}

	public void addDetails(D detail) {
		details.add(detail);
	}

	public void setFound(int found) {
		this.found = found;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setDetails(List<D> details) {
		this.details = details;
	}
}

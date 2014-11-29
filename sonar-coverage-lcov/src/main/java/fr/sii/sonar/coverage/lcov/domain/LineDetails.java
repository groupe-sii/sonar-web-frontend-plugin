package fr.sii.sonar.coverage.lcov.domain;

public class LineDetails {
	private int line;
	
	private int hit;
	
	private String checksum;

	public LineDetails(int line, int hit, String checksum) {
		super();
		this.line = line;
		this.hit = hit;
		this.checksum = checksum;
	}

	public int getLine() {
		return line;
	}

	public int getHit() {
		return hit;
	}

	public String getChecksum() {
		return checksum;
	}
	
}

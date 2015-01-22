package fr.sii.sonar.report.core.duplication.domain;

/**
 * Information about code duplication:
 * <ul>
 *  <li>The path to the source file</li>
 *  <li>The start line for the duplicated code</li>
 *  <li>The end line for the duplicated code</li>
 *  <li>The duplicated code if available</li>
 * </ul>

 * @author Aurélien Baudet
 *
 */
public class DuplicatedBlock {
	/**
	 * The path to the file that contains duplications
	 */
	private String sourceFile;
	
	/**
	 * The start line for duplicated code
	 */
	private int startLine;
	
	/**
	 * The end line for duplicated code
	 */
	private int endLine;
	
	/**
	 * The duplicated code if information is available
	 */
	private String code;

	public DuplicatedBlock(String sourceFile, int startLine, int endLine, String code) {
		super();
		this.sourceFile = sourceFile;
		this.startLine = startLine;
		this.endLine = endLine;
		this.code = code;
	}

	public DuplicatedBlock(String sourceFile, int startLine, int endLine) {
		this(sourceFile, startLine, endLine, null);
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public int getNumLines() {
		return endLine - startLine;
	}

	@Override
	public String toString() {
		return sourceFile + " : " + startLine + " -> " + endLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endLine;
		result = prime * result + ((sourceFile == null) ? 0 : sourceFile.hashCode());
		result = prime * result + startLine;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DuplicatedBlock other = (DuplicatedBlock) obj;
		if (endLine != other.endLine)
			return false;
		if (sourceFile == null) {
			if (other.sourceFile != null)
				return false;
		} else if (!sourceFile.equals(other.sourceFile))
			return false;
		if (startLine != other.startLine)
			return false;
		return true;
	}
}

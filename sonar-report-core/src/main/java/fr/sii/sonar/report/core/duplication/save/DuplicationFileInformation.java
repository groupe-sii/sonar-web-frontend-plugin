package fr.sii.sonar.report.core.duplication.save;

import java.util.List;

import fr.sii.sonar.report.core.duplication.domain.DuplicatedBlock;

/**
 * Provide general information about duplication for the file:
 * <ul>
 *  <li>The path to the file</li>
 *  <li>The total number of duplicated lines</li>
 *  <li>The list of duplicated blocks</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class DuplicationFileInformation {
	/**
	 * The path to the file
	 */
	private String path;
	
	/**
	 * The total number of duplicated lines for the file
	 */
	private int lines;
	
	/**
	 * The duplicated blocks for the file
	 */
	private List<DuplicatedBlock> blocks;
	
	public DuplicationFileInformation(String path, int lines, List<DuplicatedBlock> blocks) {
		super();
		this.path = path;
		this.lines = lines;
		this.blocks = blocks;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public List<DuplicatedBlock> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<DuplicatedBlock> blocks) {
		this.blocks = blocks;
	}
}

package fr.sii.sonar.coverage.lcov.parser.domain;

import fr.sii.sonar.coverage.lcov.parser.statement.LcovSourceFileStatement;

/**
 * LCOV provides information for each covered file.
 * It provides information about the functions, the lines and the code branches.
 * 
 * @author Aurélien Baudet
 * 
 * @see LcovSourceFileStatement
 *
 */
public class FileCoverage {
	/**
	 * The path to the file
	 */
	private String path;
	
	/**
	 * Information about functions (number of found function, number of covered functions) and function details (name, line, execution count)
	 */
	private CoverageInfo<FunctionCoverageDetail> functions;
	
	/**
	 * Information about functions (number of found lines, number of covered lines) and line details (line, execution count, checksum)
	 */
	private CoverageInfo<LineCoverageDetail> lines;
	
	/**
	 * Information about branches (number of found branches, number of covered branches) and branch details (line, block id, branch id, number of branch execution)
	 */
	private CoverageInfo<BranchCoverageDetail> branches;

	public FileCoverage(String path) {
		this(path, new CoverageInfo<FunctionCoverageDetail>(), new CoverageInfo<LineCoverageDetail>(), new CoverageInfo<BranchCoverageDetail>());
	}
	

	public FileCoverage(String path, CoverageInfo<FunctionCoverageDetail> functions, CoverageInfo<LineCoverageDetail> lines, CoverageInfo<BranchCoverageDetail> branches) {
		super();
		this.path = path;
		this.functions = functions;
		this.lines = lines;
		this.branches = branches;
	}



	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public CoverageInfo<FunctionCoverageDetail> getFunctions() {
		return functions;
	}


	public void setFunctions(CoverageInfo<FunctionCoverageDetail> functions) {
		this.functions = functions;
	}


	public CoverageInfo<LineCoverageDetail> getLines() {
		return lines;
	}


	public void setLines(CoverageInfo<LineCoverageDetail> lines) {
		this.lines = lines;
	}


	public CoverageInfo<BranchCoverageDetail> getBranches() {
		return branches;
	}


	public void setBranches(CoverageInfo<BranchCoverageDetail> branches) {
		this.branches = branches;
	}

}

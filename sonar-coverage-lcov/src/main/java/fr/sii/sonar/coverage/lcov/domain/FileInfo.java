package fr.sii.sonar.coverage.lcov.domain;


public class FileInfo {
	private String path;
	
	private LcovInfo<FunctionDetails> functions;
	
	private LcovInfo<LineDetails> lines;
	
	private LcovInfo<BranchDetails> branches;

	public FileInfo(String path) {
		this(path, new LcovInfo<FunctionDetails>(), new LcovInfo<LineDetails>(), new LcovInfo<BranchDetails>());
	}
	

	public FileInfo(String path, LcovInfo<FunctionDetails> functions, LcovInfo<LineDetails> lines, LcovInfo<BranchDetails> branches) {
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


	public LcovInfo<FunctionDetails> getFunctions() {
		return functions;
	}


	public void setFunctions(LcovInfo<FunctionDetails> functions) {
		this.functions = functions;
	}


	public LcovInfo<LineDetails> getLines() {
		return lines;
	}


	public void setLines(LcovInfo<LineDetails> lines) {
		this.lines = lines;
	}


	public LcovInfo<BranchDetails> getBranches() {
		return branches;
	}


	public void setBranches(LcovInfo<BranchDetails> branches) {
		this.branches = branches;
	}

}

package fr.sii.sonar.report.core.quality.domain.profile;

import java.util.ArrayList;
import java.util.List;

public class Profile {
	private String name;
	
	private String language;
	
	private List<ProfileRule> rules;
	
	private List<ProfileRepository> repositories;

	
	public Profile() {
		this(null, null, new ArrayList<ProfileRule>(), new ArrayList<ProfileRepository>());
	}

	public Profile(String name, String language, List<ProfileRule> rules, List<ProfileRepository> repositories) {
		super();
		this.name = name;
		this.language = language;
		this.rules = rules;
		this.repositories = repositories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<ProfileRule> getRules() {
		return rules;
	}

	public void setRules(List<ProfileRule> rules) {
		this.rules = rules;
	}

	public List<ProfileRepository> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<ProfileRepository> repositories) {
		this.repositories = repositories;
	}
}

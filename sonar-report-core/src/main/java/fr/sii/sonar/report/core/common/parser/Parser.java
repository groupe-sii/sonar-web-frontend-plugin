package fr.sii.sonar.report.core.common.parser;

import java.io.InputStream;

import fr.sii.sonar.report.core.common.exception.ParseException;

public interface Parser<R> {
	public R parse(InputStream stream) throws ParseException;
}

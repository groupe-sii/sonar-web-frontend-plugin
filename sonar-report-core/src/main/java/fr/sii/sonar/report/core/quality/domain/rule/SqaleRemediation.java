package fr.sii.sonar.report.core.quality.domain.rule;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(SqaleConstantRemediation.class),
	@Type(SqaleLinearRemediation.class),
	@Type(SqaleLinearWithOffsetRemediation.class)})
public interface SqaleRemediation {
}

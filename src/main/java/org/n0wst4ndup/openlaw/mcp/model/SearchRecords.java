package org.n0wst4ndup.openlaw.mcp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class SearchRecords {
	/**
	 * 법령 검색 개별 레코드
	 * */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record LawSearch(
		@JsonProperty("법령ID") String 법령ID,
		@JsonProperty("법령명한글") String 법령명,
		@JsonProperty("현행연혁코드") String 현행연혁코드,
		@JsonProperty("법령구분명") String 법령구분명,
		@JsonProperty("소관부처명") String 소관부처명,
		@JsonProperty("공동부령정보") JsonNode 공동부령정보,
		@JsonProperty("시행일자") String 시행일자,
		@JsonProperty("공포일자") String 공포일자
	) { 
		@Override
		public final String toString() {
			return String.format("{title: %s (%s), id: %s, type: %s, publication: %s, exec: %s %s}",
					법령명(), 현행연혁코드(), 법령ID(), 법령구분명(), 시행일자(), 소관부처명(), 공동부령정보());
	}}
	
	/**
	 * 행정규칙 검색 개별 레코드
	 * */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record AdmrulSearch(
		@JsonProperty("행정규칙일련번호") String 행정규칙일련번호,
		@JsonProperty("행정규칙명") String 행정규칙명,
		@JsonProperty("현행연혁구분") String 현행연혁구분,
		@JsonProperty("행정규칙종류") String 행정규칙종류,
		@JsonProperty("소관부처명") String 소관부처명,
		@JsonProperty("시행일자") String 시행일자,
		@JsonProperty("발령일자") String 발령일자
	) { 
		@Override
		public final String toString() {
			return String.format("{title: %s (%s), id: %s, type: %s, publication: %s, exec: %s}",
					행정규칙명(), 현행연혁구분(), 행정규칙일련번호(), 행정규칙종류(), 시행일자(), 소관부처명());
	}}
	
	/**
	 * 자치법규 검색 개별 레코드
	 * */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record OrdinSearch(
		@JsonProperty("자치법규ID") String 자치법규ID,
		@JsonProperty("자치법규명") String 자치법규명,
		@JsonProperty("자치법규분야명") String 자치법규분야명,
		@JsonProperty("자치법규종류") String 자치법규종류,
		@JsonProperty("지자체기관명") String 지자체기관명,
		@JsonProperty("시행일자") String 시행일자,
		@JsonProperty("공포일자") String 공포일자
	) { 
		@Override
		public final String toString() {
			return String.format("{title: %s (%s), id: %s, type: %s, publication: %s, exec: %s}",
					자치법규명(), 자치법규분야명(), 자치법규ID(), 자치법규종류(), 시행일자(), 지자체기관명());
	}}

}
package org.n0wst4ndup.openlaw.mcp.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDTO implements JsonDTO {
	
  /** 검색 대상 */
	@JsonProperty("target")
	private String target;
  /** 검색 영역 ()*/
	@JsonProperty("section")
	private String section;
  /** 검색 키워드 */
	@JsonProperty("키워드")
	private String query;
  /** 검색 결과 총 개수 */
	@JsonProperty("totalCnt")
	private Integer totalCnt;
  /** 검색 결과 수 */
	@JsonProperty("numOfRows")
	private Integer numOfRows;
  /** 검색 결과 페이지 번호 */
	@JsonProperty("page")
	private Integer page;
  /** 검색 결과 데이터 */
	private ArrayNode datas;
	
	@JsonAnySetter
	public void setData(String key, JsonNode value) {

		ArrayNode tmp = null;
		
		if (value.isValueNode()) return;
		if (value.isObject()) tmp = JsonNodeFactory.instance.arrayNode().add(value);
		if (value.isArray()) tmp = (ArrayNode) value;
		
		this.datas = tmp;
	}

}

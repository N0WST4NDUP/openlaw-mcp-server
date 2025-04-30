package org.n0wst4ndup.openlaw.mcp.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDTO implements JsonDTO {
	
	@JsonProperty("target")
	private String target;
	@JsonProperty("section")
	private String section;
	@JsonProperty("키워드")
	private String query;
	@JsonProperty("totalCnt")
	private Integer totalCnt;
	@JsonProperty("numOfRows")
	private Integer numOfRows;
	@JsonProperty("page")
	private Integer page;

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

package org.n0wst4ndup.openlaw.mcp.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicJsonDTO {
	
	@Getter
	private JsonDTO json;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@JsonAnySetter
	public void setJson(String key, JsonNode value) {
		
		JsonDTO tmp = null;

		if (key.contains("Search")) tmp = mapper.convertValue(value, SearchDTO.class);
		if (key.contains("Service")) tmp = mapper.convertValue(value, ServiceDTO.class);
		
		this.json = tmp;
	}
}

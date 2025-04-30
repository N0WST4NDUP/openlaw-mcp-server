package org.n0wst4ndup.openlaw.mcp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDTO implements JsonDTO {

}

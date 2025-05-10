package org.n0wst4ndup.openlaw.mcp.tool;

import java.util.List;

import org.n0wst4ndup.openlaw.mcp.model.DynamicJsonDTO;
import org.n0wst4ndup.openlaw.mcp.model.SearchDTO;
import org.n0wst4ndup.openlaw.mcp.model.SearchRecords.AdmrulSearch;
import org.n0wst4ndup.openlaw.mcp.model.SearchRecords.LawSearch;
import org.n0wst4ndup.openlaw.mcp.model.SearchRecords.OrdinSearch;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Host가 사용할 수 있는 OpenLaw api 툴을 제공합니다.
 * @author n0wst4ndup
 * */

@Service
public class OpenLawService {
	
	private static final String BASE_URL = "http://www.law.go.kr";
	
	private final RestClient restClient;
	
	private final ObjectMapper mapper;
	
	@Value("${openlaw.oc}")
	private String oc;
	@Value("${openlaw.type}")
	private String type;

	public OpenLawService(ObjectMapper mapper) {
		this.mapper = mapper;
		this.restClient = RestClient.builder()
			.baseUrl(BASE_URL)
	        .defaultHeaders(headers -> {
	        	headers.add("Accept", "application/json");
	        	headers.add("User-Agent", "LawApiClient/1.0 " + oc);
	        })
			.build();
		
	}
	
	/**
	 * 법령, 행정규칙, 자치법규, 판례 검색 MCP 툴
	 * @param target	서비스대상 (법령: law / 행정규칙: admrul / 자치법규: ordin) (Required)
	 * @param search	검색범위 (1: 법령명 / 2:본문)
	 * @param query		검색범위에서 검색을 원하는 질의 (e.g. 대한민국헌법)
	 * @param display	검색 결과 개수
	 * @param page		검색 결과 페이지
	 * @return {@link SearchDTO}
	 * @throws RestClientException 요청 실패시
	 * @apiNote -
	 * */
	@Tool(description = "국가법령정보 Open API를 통해 법령, 행정규칙, 자치법규를 검색합니다.")
	public String law_search(
                  @ToolParam(description = "서비스대상(law:법령/admrul:행정규칙/ordin:자치법규)", required = true) 
							    String target,
							 
							    @ToolParam(description = "검색범위(1:제목/2:본문, default=1)", required = false)
							    Integer search,
							 
							    @ToolParam(description = "검색범위에서 검색을 원하는 질의(e.g. 대한민국헌법)\n"
							 						              + "복수질의의 경우 '+'를 사용해 검색(e.g. 고용+산업)", required = false)
							    String query,
							 
							    @ToolParam(description = "검색 결과 개수(default=20 max=100)", required = false)
							    Integer display,
							 
							    @ToolParam(description = "검색 결과 페이지(default=1)", required = false) 
							    Integer page
                  ) {	
		// Param Setting
		UriComponentsBuilder uri = 
			UriComponentsBuilder
				.fromPath("/DRF/lawSearch.do")
				.queryParam("OC", oc)
				.queryParam("type", type)
				.queryParam("target", target);
		
		if (search != null) uri.queryParam("search", search);
		if (query != null) uri.queryParam("query", query);
		if (display != null) uri.queryParam("display", display);
		if (page != null) uri.queryParam("page", page);
		
		// Get Request
		DynamicJsonDTO response = restClient.get().uri(uri.build().toUriString()).retrieve().body(DynamicJsonDTO.class);
		if (response == null) throw new RestClientException("API 응답이 null입니다.");
		SearchDTO searchDTO = (SearchDTO) response.getJson();;
    
		// Data Processing for Host
		TypeReference<?> convertType = null;
		
		if (target.equals("law")) convertType = new TypeReference<List<LawSearch>>() {};
		if (target.equals("admrul")) convertType = new TypeReference<List<AdmrulSearch>>() {};
		if (target.equals("ordin")) convertType = new TypeReference<List<OrdinSearch>>() {};
		
		var dataList = convertType != null 
				? mapper.convertValue(searchDTO.getDatas(), convertType) : "Target not found";
		
    int start = display != null ? display*(searchDTO.getPage()-1) : searchDTO.getNumOfRows()*(searchDTO.getPage()-1);
    int end = start + searchDTO.getNumOfRows();

		return String.format("query: %s, scope: %d-%d/%d, result: %s", 
            searchDTO.getQuery(), 
            start, 
            end, 
            searchDTO.getTotalCnt(), 
            dataList != null ? dataList : "No search results");
	}

  /**
   * 개발 전
   * @return {@link null}
   */
	public String law_service() {	
		return null;
	}

}

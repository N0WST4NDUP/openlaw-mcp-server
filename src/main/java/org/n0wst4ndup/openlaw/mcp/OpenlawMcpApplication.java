package org.n0wst4ndup.openlaw.mcp;

import org.n0wst4ndup.openlaw.mcp.tool.OpenLawService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenlawMcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenlawMcpApplication.class, args);
	}
	
	@Bean
	ToolCallbackProvider lawTools(OpenLawService openLawService) {
    
	    return MethodToolCallbackProvider.builder()
	                                     .toolObjects(openLawService)
	                                     .build();
	}

}

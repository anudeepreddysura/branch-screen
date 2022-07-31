package com.techscreen.branch.project;

import com.techscreen.branch.project.model.mapper.configuration.GithubRepositoryMappingConfiguration;
import com.techscreen.branch.project.model.mapper.configuration.GithubUserMappingConfiguration;
import com.techscreen.branch.project.utility.client.GithubClientErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class ProjectApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new GithubClientErrorHandler());
		return restTemplate;
	}

	@Bean
	public GithubUserMappingConfiguration getGithubUserMapper() {
		return new GithubUserMappingConfiguration();
	}

	@Bean
	public GithubRepositoryMappingConfiguration getGithubRepositoryMapper() {
		return new GithubRepositoryMappingConfiguration();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}

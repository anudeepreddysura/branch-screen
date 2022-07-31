package com.techscreen.branch.project.model.mapper.configuration;

import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.techscreen.branch.project.model.Repository;
import com.techscreen.branch.project.utility.client.GithubRepository;

public class GithubRepositoryMappingConfiguration extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(Repository.class, GithubRepository.class)
                .fields("name", "name")
                .fields("url", "url");
    }
}

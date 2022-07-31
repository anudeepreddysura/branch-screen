package com.techscreen.branch.project.model.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.techscreen.branch.project.model.Repository;
import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.model.mapper.configuration.GithubRepositoryMappingConfiguration;
import com.techscreen.branch.project.model.mapper.configuration.GithubUserMappingConfiguration;
import com.techscreen.branch.project.utility.client.GithubRepository;
import com.techscreen.branch.project.utility.client.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMapper {

    @Autowired
    private GithubRepositoryMappingConfiguration githubRepositoryMappingConfiguration;

    private static Mapper mapper = DozerBeanMapperBuilder.create()
                                    .withMappingBuilder(new GithubRepositoryMappingConfiguration())
                                    .build();

    public Repository convert(GithubRepository githubRepository) {
        return mapper.map(githubRepository, Repository.class);
    }

    public GithubRepository convert(Repository repository) {
        return mapper.map(repository, GithubRepository.class);
    }
}

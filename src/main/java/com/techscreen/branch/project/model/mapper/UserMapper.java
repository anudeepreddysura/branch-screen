package com.techscreen.branch.project.model.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.utility.client.GithubUser;
import com.techscreen.branch.project.model.mapper.configuration.GithubUserMappingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private GithubUserMappingConfiguration githubUserMappingConfiguration;

    private static Mapper mapper = DozerBeanMapperBuilder.create()
                                    .withMappingBuilder(new GithubUserMappingConfiguration())
                                    .build();

    public User convert(GithubUser githubUser) {
        return mapper.map(githubUser, User.class);
    }

    public GithubUser convert(User user) {
        return mapper.map(user, GithubUser.class);
    }
}

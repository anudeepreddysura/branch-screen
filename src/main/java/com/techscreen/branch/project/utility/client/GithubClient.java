package com.techscreen.branch.project.utility.client;

import com.techscreen.branch.project.model.Repository;
import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.model.mapper.RepositoryMapper;
import com.techscreen.branch.project.model.mapper.UserMapper;
import com.techscreen.branch.project.utility.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class GithubClient implements GithubDAO {

    private final String githubBaseURI = "https://api.github.com";

    private final Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RepositoryMapper repositoryMapper;

    /**
     * Get User information from github and map the user to our model.
     * @param username
     * @return Mapped User information
     * @throws BadRequestException if user is not present in github.
     */
    public User getUser(String username) throws BadRequestException {
        GithubUser githubUser = null;
        try {
            LOGGER.info("Getting user from GIT with username : " + username);
            githubUser  = restTemplate.getForObject(githubBaseURI + "/users/" + username, GithubUser.class);
        } catch (ResourceAccessException e) {
            LOGGER.error("User not found in GIT with username : " + username);
            throw new BadRequestException("Requested user is unavailable");
        }
        User user = userMapper.convert(githubUser);
        return user;
    }

    /**
     * Get user repositories information from github and map the repositories to our model.
     * @param username
     * @return List of mapped user repositories.
     */
    public List<Repository> getUserRepos(String username) {
        LOGGER.info("Getting user repos from GIT for username : " + username);
        GithubRepository[] githubRepositories = restTemplate.getForObject(githubBaseURI + "/users/" + username + "/repos", GithubRepository[].class);
        return Arrays.stream(githubRepositories)
                .map(githubRepository ->  repositoryMapper.convert(githubRepository))
                .collect(Collectors.toList());
    }
}

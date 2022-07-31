package com.techscreen.branch.project.service;

import com.techscreen.branch.project.model.Repository;
import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.utility.client.GithubDAO;
import com.techscreen.branch.project.utility.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class GithubService {

    private final Logger LOGGER = LoggerFactory.getLogger(GithubService.class);

    @Autowired
    private GithubDAO githubClient;

    /**
     * Get github summary of user by calling the github client to fetch necessary information.
     * Also caches the request, response for 10 minutes in EH Cache.
     *
     * @param username
     * @return User details along with repository information
     * @throws BadRequestException if user is not present in github.
     */
    @Cacheable("github")
    public User getSummary(String username) throws BadRequestException {
        LOGGER.info("Get Summary for user : " + username);
        User user = githubClient.getUser(username);
        List<Repository> repos = githubClient.getUserRepos(username);
        user.setRepos(repos);
        return user;
    }
}

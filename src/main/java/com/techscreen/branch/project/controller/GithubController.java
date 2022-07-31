package com.techscreen.branch.project.controller;

import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.service.GithubService;
import com.techscreen.branch.project.utility.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@NoArgsConstructor
@AllArgsConstructor
@RestController()
@RequestMapping(path = "/github")
public class GithubController {

    private final Logger LOGGER = LoggerFactory.getLogger(GithubController.class);

    @Autowired
    private GithubService githubService;

    /**
     * Get Github Summary of User.
     * @param username
     * @return User Details along with Repository information
     */
    @GetMapping(path = "{username}")
    public User getSummary(@PathVariable("username") String username) {
        LOGGER.info("Get Summary for user : " + username);
        try {
            return githubService.getSummary(username);
        } catch (BadRequestException e) {
            LOGGER.error("Bad Request on Get Summary for user : " + username, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}
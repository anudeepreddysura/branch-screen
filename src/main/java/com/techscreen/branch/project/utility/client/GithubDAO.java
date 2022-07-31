package com.techscreen.branch.project.utility.client;

import com.techscreen.branch.project.model.Repository;
import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.utility.exception.BadRequestException;

import java.util.List;

public interface GithubDAO {

    User getUser(String username) throws BadRequestException;
    List<Repository> getUserRepos(String username);
}

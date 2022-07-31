package com.techscreen.branch.project.service;

import com.techscreen.branch.project.model.Repository;
import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.utility.client.GithubDAO;
import com.techscreen.branch.project.utility.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GithubServiceTest {

    private GithubService githubService;

    @Mock
    private GithubDAO githubClient;

    @BeforeEach
    void setUp() {
        githubService = new GithubService(githubClient);
    }

    @Test
    @DisplayName("getSummary_Success")
    void getSummary() throws BadRequestException {
        Repository repository = new Repository();
        repository.setName("dog");
        List<Repository> expectedRepositories = Arrays.asList(repository);
        User expectedUser = new User();
        expectedUser.setUser_name("abcd");

        when(githubClient.getUser(anyString())).thenReturn(expectedUser);
        when(githubClient.getUserRepos(anyString())).thenReturn(expectedRepositories);

        User actualUser = githubService.getSummary("abcd");
        assertEquals(actualUser.getUser_name(), expectedUser.getUser_name());
        assertEquals(actualUser.getRepos(), expectedRepositories);
        verify(githubClient, times(1)).getUser(anyString());
        verify(githubClient, times(1)).getUserRepos(anyString());
    }
}
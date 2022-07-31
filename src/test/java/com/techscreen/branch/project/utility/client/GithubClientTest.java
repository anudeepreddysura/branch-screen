package com.techscreen.branch.project.utility.client;

import com.techscreen.branch.project.model.Repository;
import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.model.mapper.RepositoryMapper;
import com.techscreen.branch.project.model.mapper.UserMapper;
import com.techscreen.branch.project.utility.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GithubClientTest {

    private GithubClient githubClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RepositoryMapper repositoryMapper;

    @BeforeEach
    void setUp() {
        githubClient = new GithubClient(restTemplate, userMapper, repositoryMapper);
    }

    @Test
    @DisplayName("getUser_Success")
    void getUser() throws BadRequestException {
        GithubUser githubUser = new GithubUser();
        githubUser.setName("abcd");
        User user = new User();
        user.setUser_name("abcd");

        when(restTemplate.getForObject(anyString(), eq(GithubUser.class))).thenReturn(githubUser);
        when(userMapper.convert(githubUser)).thenReturn(user);

        assertEquals(githubClient.getUser("abcd"), user);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(GithubUser.class));
        verify(userMapper, times(1)).convert(githubUser);
    }

    @Test
    @DisplayName("getUser_BadRequest")
    void getUserError() throws BadRequestException {
        GithubUser githubUser = new GithubUser();
        githubUser.setName("abcd");

        when(restTemplate.getForObject(anyString(), eq(GithubUser.class))).thenThrow(new ResourceAccessException("Resource not available"));

        assertThrows(BadRequestException.class, () -> githubClient.getUser("abcd"));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(GithubUser.class));
        verify(userMapper, times(0)).convert(githubUser);
    }

    @Test
    @DisplayName("getUserRepos_Success")
    void getUserRepos() {
        GithubRepository githubRepository = new GithubRepository();
        githubRepository.setName("abcd");
        Repository repository = new Repository();
        repository.setName("abcd");
        GithubRepository[] githubRepositories = {githubRepository};

        when(restTemplate.getForObject(anyString(), eq(GithubRepository[].class))).thenReturn(githubRepositories);
        when(repositoryMapper.convert(githubRepository)).thenReturn(repository);

        assertEquals(githubClient.getUserRepos("abcd"), List.of(repository));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(GithubRepository[].class));
        verify(repositoryMapper, times(1)).convert(githubRepository);
    }
}
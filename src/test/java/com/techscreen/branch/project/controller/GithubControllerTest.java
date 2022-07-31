package com.techscreen.branch.project.controller;

import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.service.GithubService;
import com.techscreen.branch.project.utility.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GithubControllerTest {

    private GithubController githubController;

    @Mock
    private GithubService githubService;

    @BeforeEach
    void setUp() {
        githubController = new GithubController(githubService);
    }

    @Test
    @DisplayName("getSummary_Success")
    void getSummary() throws BadRequestException {
        User user = new User();
        user.setUser_name("abcd");

        when(githubService.getSummary(anyString())).thenReturn(user);

        assertEquals(githubController.getSummary("abcd"), user);
        verify(githubService, times(1)).getSummary(anyString());
    }

    @Test
    @DisplayName("getSummary_BadRequest")
    void getSummaryError() throws BadRequestException {
        User user = new User();
        user.setUser_name("abcd");

        when(githubService.getSummary(anyString())).thenThrow(new BadRequestException());

        assertThrows(ResponseStatusException.class, () -> githubController.getSummary("abcd"));
        verify(githubService, times(1)).getSummary(anyString());
    }
}
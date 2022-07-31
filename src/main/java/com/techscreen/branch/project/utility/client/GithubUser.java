package com.techscreen.branch.project.utility.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techscreen.branch.project.model.Repository;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubUser {

    @JsonProperty("login")
    private String login;
    @JsonProperty("name")
    private String name;
    @JsonProperty("avatar_url")
    private String avatar_url;
    @JsonProperty("location")
    private String location;
    private String email;
    @JsonProperty("html_url")
    private String html_url;
    private LocalDateTime created_at;
    private List<Repository> repos;

}
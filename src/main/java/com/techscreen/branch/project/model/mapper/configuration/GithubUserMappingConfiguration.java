package com.techscreen.branch.project.model.mapper.configuration;

import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.techscreen.branch.project.model.User;
import com.techscreen.branch.project.utility.client.GithubUser;

public class GithubUserMappingConfiguration extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(User.class, GithubUser.class)
                .fields("user_name", "login")
                .fields("display_name", "name")
                .fields("avatar", "avatar_url")
                .fields("geo_location", "location")
                .fields("email", "email")
                .fields("url", "html_url")
                .fields("created_at", "created_at", FieldsMappingOptions.customConverter(GithubDateConverter.class))
                .fields("repos", "repos");
    }
}

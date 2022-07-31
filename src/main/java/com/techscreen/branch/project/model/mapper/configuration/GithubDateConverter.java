package com.techscreen.branch.project.model.mapper.configuration;

import com.github.dozermapper.core.CustomConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class GithubDateConverter implements CustomConverter {

    @Override
    public Object convert(Object dest, Object source, Class<?> arg2, Class<?> arg3) {
        if (source == null)
            return null;

        LocalDateTime dateTime = (LocalDateTime) source;
        DateTimeFormatter githubUserDateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter userDateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (dateTime.toString().indexOf('T')!=-1) {
            String dateText = dateTime.format(userDateformat);
            return LocalDateTime.parse(dateText, userDateformat);
        } else {
            String dateText = dateTime.format(githubUserDateformat);
            return LocalDateTime.parse(dateText, githubUserDateformat);
        }
    }
}

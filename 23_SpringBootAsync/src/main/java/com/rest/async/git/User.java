package com.rest.async.git;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*
define a representation for the data you will retrieve through GitHub’s API.
Spring uses the Jackson JSON library to convert GitHub’s JSON response into a
User object. The @JsonIgnoreProperties annotation tells Spring to ignore any
attributes not listed in the class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User [name: " + name + ", blog: " + blog + "]";
    }
}

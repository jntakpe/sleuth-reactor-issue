package com.github.jntakpe.sleuthreactorissue;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Issue {

    @Id
    private ObjectId id;

    private String name;

    private String username;

    public ObjectId getId() {
        return id;
    }

    public Issue setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Issue setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Issue setUsername(String username) {
        this.username = username;
        return this;
    }
}

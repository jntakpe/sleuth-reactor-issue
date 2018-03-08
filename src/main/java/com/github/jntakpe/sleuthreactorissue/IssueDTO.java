package com.github.jntakpe.sleuthreactorissue;

import javax.validation.constraints.NotEmpty;

public class IssueDTO {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public IssueDTO setName(String name) {
        this.name = name;
        return this;
    }
}

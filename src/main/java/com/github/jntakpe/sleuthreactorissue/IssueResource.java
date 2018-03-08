package com.github.jntakpe.sleuthreactorissue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/issues")
public class IssueResource {

    private final IssueService issueService;

    public IssueResource(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public Mono<Issue> create(@RequestBody @Valid IssueDTO issue) {
        return issueService.create(issue.getName());
    }

}

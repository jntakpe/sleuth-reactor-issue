package com.github.jntakpe.sleuthreactorissue;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Mono<Issue> create(String name) {
        return ReactiveSecurityContextHolder.getContext()
                                            .switchIfEmpty(error())
                                            .map(SecurityContext::getAuthentication)
                                            .map(Authentication::getPrincipal)
                                            .cast(User.class)
                                            .map(p -> new Issue().setName(name).setUsername(p.getUsername()))
                                            .flatMap(issueRepository::save);
    }

    private Mono<SecurityContext> error() {
        return Mono.error(new IllegalStateException("Security context is empty. Disable Sleuth to get an user"));
    }

}

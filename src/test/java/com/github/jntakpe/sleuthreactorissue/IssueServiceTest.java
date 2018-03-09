package com.github.jntakpe.sleuthreactorissue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import reactor.util.context.Context;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class IssueServiceTest {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    @WithUserDetails
    public void create() {
        String name = "Test issue service";
        StepVerifier.create(issueService.create(name))
                    .expectSubscription()
                    .consumeNextWith(i -> {
                        assertThat(i.getName()).isEqualTo(name);
                        assertThat(i.getUsername()).isEqualTo("user");
                        assertThat(i.getId()).isNotNull();
                    })
                    .verifyComplete();
    }

    @Test
    public void noSecurity() {
        String name = "No secu issue service";
        UserDetails user = userDetailsService.loadUserByUsername("user");
        Context ctx = ReactiveSecurityContextHolder.withAuthentication(new UsernamePasswordAuthenticationToken(user, "pwd"));
        StepVerifier.create(issueService.create(name).subscriberContext(ctx))
                    .expectSubscription()
                    .consumeNextWith(i -> {
                        assertThat(i.getName()).isEqualTo(name);
                        assertThat(i.getUsername()).isEqualTo("user");
                        assertThat(i.getId()).isNotNull();
                    })
                    .verifyComplete();
    }
}
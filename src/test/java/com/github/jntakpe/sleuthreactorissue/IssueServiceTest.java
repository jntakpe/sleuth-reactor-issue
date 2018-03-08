package com.github.jntakpe.sleuthreactorissue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@WithUserDetails
@ExtendWith(SpringExtension.class)
public class IssueServiceTest {

    @Autowired
    private IssueService issueService;

    @Test
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
}
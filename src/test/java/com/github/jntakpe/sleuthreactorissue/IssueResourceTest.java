package com.github.jntakpe.sleuthreactorissue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@SpringBootTest
@WithUserDetails
@ExtendWith(SpringExtension.class)
public class IssueResourceTest {

    private WebTestClient webTestClient;

    @BeforeEach
    public void setup(ReactiveWebApplicationContext context) {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                                     .apply(springSecurity())
                                     .configureClient()
                                     .build();
    }

    @Test
    public void createShouldInsertNewIssue() {
        webTestClient.post()
                     .uri("/issues")
                     .syncBody(new IssueDTO().setName("Test name"))
                     .exchange()
                     .expectStatus().is2xxSuccessful()
                     .expectBody(Issue.class)
                     .consumeWith(i -> {
                         Issue body = i.getResponseBody();
                         assertThat(body.getUsername()).isEqualTo("user");
                         assertThat(body.getName()).isEqualTo("Test name");
                         assertThat(body.getId()).isNotNull();
                     });
    }
}
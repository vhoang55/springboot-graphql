package com.playground.vhoang.graphQl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureHttpGraphQlTester
class SongGraphQLIntegrationTest {

    @LocalServerPort
    private int port;

    HttpGraphQlTester tester;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port + "/graphql").build();
        this.tester = HttpGraphQlTester.create(client);
    }


    @Test
    public void shouldCreateAndFetchSong() {
        String mutation = """
                mutation($title: String!) {
                  createSong(createSongInput: {title: $title}) {
                    id
                    title
                  }
                }
                """;

        tester.document(mutation)
                .variable("title", "Bohemian Rhapsody")
                .execute()
                .path("createSong.id").hasValue()
                .path("createSong.title").entity(String.class).isEqualTo("Bohemian Rhapsody");


        String query = """
                query {
                  songs {
                    id
                    title
                  }
                }
                """;

        tester.document(query)
                .execute()
                .path("songs").hasValue();
    }
}
package com.abuyog.Lab_7;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class DebugController {

    @GetMapping("/debug")
    public Map<String, Object> debug() {
        return Map.of(
                "graphqlEndpoint", "/graphql",
                "graphiql", "/graphiql",
                "postmanEndpoint", "/api/graphql",
                "status", "Check console for GraphQL initialization"
        );
    }
}
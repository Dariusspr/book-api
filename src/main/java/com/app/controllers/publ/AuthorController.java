package com.app.controllers.publ;

import com.app.constants.EndpointConstants;
import com.app.dtos.responses.AuthorResponse;
import com.app.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("publAuthorController")
@RequestMapping(value = AuthorController.BASE_PATH)
public class AuthorController {
    public static final String BASE_PATH = EndpointConstants.PUBLIC_API + "/authors";

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(authorService.getResponses(pageable));
    }

    @GetMapping("/{name}/name")
    public ResponseEntity<Page<AuthorResponse>> getByName(@PathVariable String name, Pageable pageable) {
        System.out.println(name + " " + pageable.toString());
        return ResponseEntity.ok(authorService.getResponseByName(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getResponseById(id));
    }
}

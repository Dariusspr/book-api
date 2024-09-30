package com.app.controllers.publ;


import com.app.constants.EndpointConstants;
import com.app.dtos.responses.CategoryResponse;
import com.app.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("publCategoryController")
@RequestMapping(CategoryController.BASE_PATH)
public class CategoryController {
	public static final String BASE_PATH = EndpointConstants.PUBLIC_API + "/categories";

	private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
	public ResponseEntity<Page<CategoryResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(categoryService.getResponses(pageable));
	}

	@GetMapping("/{title}/title")
	public ResponseEntity<Page<CategoryResponse>> getByTitle(@PathVariable String title, Pageable pageable) {
		return ResponseEntity.ok(categoryService.getResponsesByTitle(title, pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getResponseById(id));
	}

}

package com.app.repositories;

import com.app.dtos.responses.CategoryResponse;
import com.app.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new com.app.dtos.responses.CategoryResponse(c.id, c.title) FROM Category c WHERE c.title LIKE %:title%")
    Page<CategoryResponse> findByTitle(@Param("title") String title, Pageable pageable);

    @Query("SELECT new com.app.dtos.responses.CategoryResponse(c.id, c.title) FROM Category c")
    Page<CategoryResponse> findResponses(Pageable pageable);
}

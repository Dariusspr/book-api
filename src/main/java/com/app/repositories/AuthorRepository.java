package com.app.repositories;

import com.app.dtos.responses.AuthorResponse;
import com.app.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("""
            SELECT new com.app.dtos.responses.AuthorResponse(a.id, a.firstName, a.lastName) 
            FROM Author a 
            WHERE LOWER(CONCAT(a.firstName, ' ', a.lastName))
            LIKE %:name%""")
    Page<AuthorResponse> findByName(@Param("name") String name, Pageable pageable);

    @Query("""
            SELECT new com.app.dtos.responses.AuthorResponse(a.id, a.firstName, a.lastName) 
            FROM Author a""")
    Page<AuthorResponse> findAuthors(Pageable pageable);
}
package com.app.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book{

    @Id
    @Column(name = "isbn")
    private String isbn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "published_year")
    private int year;

    @Column(name = "pages")
    private int pages;

    public Book() {
    }

    public Book(String isbn, Set<Author> authors, Category category, String title, int year, int pages) {
        this.isbn = isbn;
        this.authors = authors;
        this.category = category;
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
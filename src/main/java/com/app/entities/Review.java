package com.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review{

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_isbn")
    private Book book;


    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    public Review() {
    }

    public Review(Book book, int rating, String comment) {
        this.book = book;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

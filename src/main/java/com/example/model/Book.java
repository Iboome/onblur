package com.example.model;

import javax.persistence.*;

@Entity
@Table(name="t_book")
public class Book {
    private Integer id;
    private String bookName;
    private String autor;

    @Id
    @GeneratedValue
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="book_name")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Column(name="autor")
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Book() {
    }

    public Book(Integer id, String bookName, String autor) {
        this.id = id;
        this.bookName = bookName;
        this.autor = autor;
    }

    public Book(String bookName, String autor) {
        this.bookName = bookName;
        this.autor = autor;
    }
}

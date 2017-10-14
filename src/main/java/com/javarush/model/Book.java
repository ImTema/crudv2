package com.javarush.model;

import javax.persistence.*;

@Entity
@Table(name = "books", schema = "test")
public class Book {
    private int id;
    private String title;
    private String description;
    private String author;
    private String isbn;
    private Integer printYear;
    private Byte isReadAlready;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "printYear")
    public Integer getPrintYear() {
        return printYear;
    }

    public void setPrintYear(Integer printYear) {
        this.printYear = printYear;
    }

    @Basic
    @Column(name = "isReadAlready")
    public Byte getIsReadAlready() {
        return isReadAlready;
    }

    public void setIsReadAlready(Byte isReadAlready) {
        this.isReadAlready = isReadAlready;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book that = (Book) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (printYear != null ? !printYear.equals(that.printYear) : that.printYear != null) return false;
        if (isReadAlready != null ? !isReadAlready.equals(that.isReadAlready) : that.isReadAlready != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (printYear != null ? printYear.hashCode() : 0);
        result = 31 * result + (isReadAlready != null ? isReadAlready.hashCode() : 0);
        return result;
    }
}

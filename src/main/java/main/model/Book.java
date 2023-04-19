package main.model;

public class Book {

    private String id;
    private String title;
    private String description;
    private String author;
    private int stock;

    public Book(String id, String title, String description, String author, int stock) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.stock = stock;
    }

    public Book(String title, String description, String author, int stock) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public int getStock() {
        return stock;
    }
}

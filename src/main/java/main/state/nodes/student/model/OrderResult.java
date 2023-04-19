package main.state.nodes.student.model;

import java.sql.Date;

public class OrderResult {
    private String bookTitle;
    private String bookAuthor;

    private Date orderCreatedDate;
    private Date orderExpirationDate;

    public OrderResult(String bookTitle, String bookAuthor, Date orderCreatedDate, Date orderExpirationDate) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.orderCreatedDate = orderCreatedDate;
        this.orderExpirationDate = orderExpirationDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public Date getOrderCreatedDate() {
        return orderCreatedDate;
    }

    public Date getOrderExpirationDate() {
        return orderExpirationDate;
    }
}


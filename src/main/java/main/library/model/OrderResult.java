package main.library.model;

import java.sql.Date;

public class OrderResult {

    private String userId;
    private String userName;
    private String userEmail;

    private String bookId;
    private String bookTitle;
    private String bookAuthor;

    private Date orderCreatedDate;
    private Date orderExpirationDate;

    public OrderResult(String userId, String userName, String userEmail, String bookId, String bookTitle, String bookAuthor, Date orderCreatedDate, Date orderExpirationDate) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.orderCreatedDate = orderCreatedDate;
        this.orderExpirationDate = orderExpirationDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getBookId() {
        return bookId;
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

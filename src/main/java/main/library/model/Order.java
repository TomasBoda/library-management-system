package main.library.model;

import java.sql.Date;

public class Order {

    private String userId;
    private String bookId;
    private Date createdDate;
    private Date expirationDate;

    public Order(String userId, String bookId, Date createdDate, Date expirationDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}

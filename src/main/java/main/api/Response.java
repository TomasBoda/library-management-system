package main.api;

public class Response<T> {

    private int status;
    private String message;
    private T data;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() { return status; }

    public String getMessage() { return message; }

    public T getData() { return data; }
}

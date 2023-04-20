package main.api;

public class Response<DataType> {

    private int status;
    private String message;
    private DataType data;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(int status, String message, DataType data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DataType getData() {
        return data;
    }
}

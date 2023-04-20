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

    /**
     * Retrieves the status of the API response
     * @return status of the API response
     */
    public int getStatus() {
        return status;
    }

    /**
     * Retrieves the message of the API response
     * @return message of the API response
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the data from the API response
     * @return data from the API response
     */
    public DataType getData() {
        return data;
    }
}

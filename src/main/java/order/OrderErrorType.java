package order;


public class OrderErrorType{
    private String errorMessage;

    public OrderErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

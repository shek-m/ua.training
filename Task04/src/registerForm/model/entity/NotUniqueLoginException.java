package registerForm.model.entity;

public class NotUniqueLoginException extends Exception{

    public NotUniqueLoginException(String message) {
        super(message);
    }
}
package registerForm.model.entity;

import registerForm.view.TextConstants;
import registerForm.view.View;

public class NotUniqueLoginException extends Exception{

    public NotUniqueLoginException() {
        super(View.bundle.getString(TextConstants.NOT_UNIQUE_LOGIN_EXCEPTION));
    }
}
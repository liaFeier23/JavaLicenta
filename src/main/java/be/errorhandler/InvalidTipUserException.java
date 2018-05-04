package be.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorised")
public class InvalidTipUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidTipUserException(String msg) { super(msg); }
}

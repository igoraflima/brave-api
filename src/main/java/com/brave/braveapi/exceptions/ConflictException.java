package com.brave.braveapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException() {
        super("Ocorreu um conflito no servidor");
    }

    public ConflictException(String msg) {
        super(msg);
    }
}

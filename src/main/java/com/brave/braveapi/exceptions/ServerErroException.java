package com.brave.braveapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErroException extends RuntimeException {

    public ServerErroException() {
        super("Ocorreu um erro no servidor");
    }

    public ServerErroException(String msg) {
        super(msg);
    }
}

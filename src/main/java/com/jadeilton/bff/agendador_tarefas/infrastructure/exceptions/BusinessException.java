package com.jadeilton.bff.agendador_tarefas.infrastructure.exceptions;


public class BusinessException extends RuntimeException {

    public BusinessException(String message) {

        super(message);
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

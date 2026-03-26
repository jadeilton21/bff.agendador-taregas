package com.jadeilton.bff.agendador_tarefas.infrastructure.client.config;

import com.jadeilton.bff.agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.jadeilton.bff.agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.jadeilton.bff.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.jadeilton.bff.agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.concurrent.ConcurrentException;

public class FeingError implements ErrorDecoder {





    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 409:
                 return new ConflictException("Erro atributo já existente");

            case 403:
                return new ResourceNotFoundException("Erro Atributo não Encontrado ");

            case 401:
                return new UnauthorizedException("Erro Usuário nãoa Autorizado");
            default:
                return new BusinessException("Erro de Servidor!!! ");
        }
    }
}

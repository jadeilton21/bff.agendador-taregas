package com.jadeilton.bff.agendador_tarefas.infrastructure.client;


import com.jadeilton.bff.agendador_tarefas.business.dto.in.TarefasDTORquest;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.jadeilton.bff.agendador_tarefas.business.enums.StatusNotificacoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

            void enviarEmail(@RequestBody TarefasDTOResponse dto);

}

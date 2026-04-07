package com.jadeilton.bff.agendador_tarefas.infrastructure.client;


import com.jadeilton.bff.agendador_tarefas.business.dto.in.TarefasDTORquest;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.jadeilton.bff.agendador_tarefas.business.enums.StatusNotificacoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@FeignClient(
        name = "agendador-tarefas-back-end",
        url = "${agendador-tarefas-back-end.url}"
)
public interface TarefasClient {

    @PostMapping("/tarefas")
    TarefasDTOResponse gravarTarefas(
            @RequestBody TarefasDTORquest dto,
            @RequestHeader("Authorization") String token
    );

    @GetMapping("/tarefas/eventos")
    List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(

            @RequestParam("dataInicial")
            String  dataInicial,

            @RequestParam("dataFinal")
            String  dataFinal,
            @RequestHeader("Authorization") String token
    );

    @GetMapping("/tarefas")
    List<TarefasDTOResponse> buscaTarefasPorEmail(
            @RequestHeader("Authorization") String token
    );

    @DeleteMapping("/tarefas")
    void deletaTarefaPorId(
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token
    );

    @PatchMapping("/tarefas")
    TarefasDTOResponse alterarStatus(
            @RequestParam("status") StatusNotificacoEnum status,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token
    );

    @PutMapping("/tarefas")
    TarefasDTOResponse updateTarefas(
            @RequestBody TarefasDTORquest dto,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token
    );
}
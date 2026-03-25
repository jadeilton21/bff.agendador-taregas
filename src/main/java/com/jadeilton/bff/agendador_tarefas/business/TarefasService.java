package com.jadeilton.bff.agendador_tarefas.business;


import com.jadeilton.bff.agendador_tarefas.business.dto.in.TarefasDTORquest;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.jadeilton.bff.agendador_tarefas.business.enums.StatusNotificacoEnum;
import com.jadeilton.bff.agendador_tarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    private String formatarToken(String token) {

        if (token == null) return null;

        return "Bearer " + token
                .replace("Bearer ", "")
                .replace("Bearer:", "")
                .trim();
    }

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORquest dto) {
        return tarefasClient.gravarTarefas(dto, formatarToken(token));
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal,
            String token) {

        if (dataInicial.isAfter(dataFinal)) {
            throw new IllegalArgumentException("dataInicial não pode ser maior que dataFinal");
        }

        return tarefasClient.buscaListaDeTarefasPorPeriodo(
                dataInicial,
                dataFinal,
                formatarToken(token)

        );
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscaTarefasPorEmail(formatarToken(token));
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletaTarefaPorId(id, formatarToken(token));
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacoEnum status, String id, String token) {
        return tarefasClient.alterarStatus(status, id, formatarToken(token));
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORquest dto, String id, String token) {
        return tarefasClient.updateTarefas(dto, id, formatarToken(token));
    }
}
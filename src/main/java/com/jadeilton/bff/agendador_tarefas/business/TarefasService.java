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



    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORquest dto){
            return tarefasClient.gravarTarefas(dto, token);
    }


    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataIncial,
                                                                    LocalDateTime dataFinal,
                                                                    String token){

     return tarefasClient.buscaListaDeTarefasPorPeriodo(dataFinal,dataIncial,token);

    }




    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token){
                return tarefasClient.buscaTarefasPorEmail(token);
    }


    public void deletaTarefaPorId(String id, String token){
        tarefasClient.deletaTarefaPorId(id,token);
    }


    public TarefasDTOResponse alteraStatus(StatusNotificacoEnum status, String id, String token){

        return tarefasClient.alterarStatus(status,id,token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORquest dto, String id, String token){
        return tarefasClient.updateTarefas(dto, id, token);

    }

}

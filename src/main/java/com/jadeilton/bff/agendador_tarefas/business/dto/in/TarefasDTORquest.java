package com.jadeilton.bff.agendador_tarefas.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jadeilton.bff.agendador_tarefas.business.enums.StatusNotificacoEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TarefasDTORquest {

    private String nomeTarefa;
    private String descricao;


    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEvento;


    private String emailUsuario;
}
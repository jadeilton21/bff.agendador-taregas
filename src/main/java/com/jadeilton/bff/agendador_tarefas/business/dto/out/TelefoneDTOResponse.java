package com.jadeilton.bff.agendador_tarefas.business.dto.out;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDTOResponse {



    private String numero;
    private String ddd;
}

package com.jadeilton.bff.agendador_tarefas.business;


import com.jadeilton.bff.agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.jadeilton.bff.agendador_tarefas.business.enums.StatusNotificacoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;
    private StatusNotificacoEnum statusNotificacoEnum;

    @Scheduled(cron = "${cron.horario}")

    public void buscaTarefasProximaHora() {

        String token = login(converterParaRquestDTO());
        log.info("Iniciando a busca de tarefas");

        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);



        List<TarefasDTOResponse> listaTarefas =
                tarefasService.buscaTarefasAgendadasPorPeriodo(
                        horaFutura,
                        horaFuturaMaisCinco,
                        token);
        log.info("Tarefas encontradas " + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuário" + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacoEnum.NOTIFICADO, tarefa.getId(), "Bearer " + token);
        });

        log.info("Finalizada a busca por notificaçãoes e tarefas!");
    }

    public String login(LoginRequestDTO dto) {
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequestDTO converterParaRquestDTO() {
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
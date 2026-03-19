package com.jadeilton.bff.agendador_tarefas.business;



import com.jadeilton.bff.agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.jadeilton.bff.agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.jadeilton.bff.agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.jadeilton.bff.agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {



    private final UsuarioClient client;
    public UsuarioDTORequest salvaUsuario(UsuarioDTORequest usuarioDTO) {

        return client.salvarUsuario(usuarioDTO);


    }
    public String loginUsuario(LoginRequestDTO usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTORequest buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email,token);
    }

    public void deletarEmail(String email, String token) {
         client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuari(String token, UsuarioDTORequest dto) {
        return client.atualizaDadosUsuario(dto, token);
    }




    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){

        return client.atualizaEndereco(enderecoDTO, idEndereco, token);


    }



    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){


        return client.cadastroTelefone(telefoneDTO,token,idTelefone);


    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
       return client.cadastroEndereco(dto, token);
    }



    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto, Long idTelefone) {

        return client.cadastroTelefone(dto, token, idTelefone);

    }





}

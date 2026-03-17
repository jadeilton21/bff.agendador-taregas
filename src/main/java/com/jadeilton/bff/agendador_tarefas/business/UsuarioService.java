package com.jadeilton.bff.agendador_tarefas.business;



import com.jadeilton.bff.agendador_tarefas.business.dto.EnderecoDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.TelefoneDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.UsuarioDTO;
import com.jadeilton.bff.agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {



    private final UsuarioClient client;
    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {

        return client.salvarUsuario(usuarioDTO);


    }
    public String loginUsuario(UsuarioDTO usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email,token);
    }

    public void deletarEmail(String email, String token) {
         client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizaDadosUsuari(String token, UsuarioDTO dto) {
        return client.atualizaDadosUsuario(dto, token);
    }




    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO, String token){

        return client.atualizaEndereco(enderecoDTO, idEndereco, token);


    }



    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO telefoneDTO, String token){


        return client.cadastroTelefone(telefoneDTO,token,idTelefone);


    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO dto) {
       return client.cadastroEndereco(dto, token);
    }



    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO dto, Long idTelefone) {

        return client.cadastroTelefone(dto, token, idTelefone);

    }





}

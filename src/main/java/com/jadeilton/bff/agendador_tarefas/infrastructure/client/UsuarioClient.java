package com.jadeilton.bff.agendador_tarefas.infrastructure.client;


import com.jadeilton.bff.agendador_tarefas.business.dto.EnderecoDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.TelefoneDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario") UsuarioDTO buscaUsuarioPorEmail(
            @RequestParam("email") String email, @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioDTO salvarUsuario(@RequestBody UsuarioDTO usuarioDTO);


    @PostMapping("/login")
     String login(@RequestBody UsuarioDTO usuarioDTO);




    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,@RequestHeader("Authorization") String token) ;

    @PutMapping
    UsuarioDTO atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                                           @RequestHeader("Authorization") String token);
    @PutMapping("/endereco")
    EnderecoDTO atualizaEndereco(@RequestBody EnderecoDTO dto,
                                  @RequestParam("id") Long id,
                                  @RequestHeader("Authorization") String token);
    @PutMapping("/telefone")
    TelefoneDTO atualizaTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token);


    @PostMapping("/endereco")
     EnderecoDTO cadastroEndereco(@RequestBody EnderecoDTO dto, @RequestHeader ("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTO cadastroTelefone(@RequestBody TelefoneDTO dto, @RequestHeader("Authorization") String token, @PathVariable("id")Long idTelefone);



}

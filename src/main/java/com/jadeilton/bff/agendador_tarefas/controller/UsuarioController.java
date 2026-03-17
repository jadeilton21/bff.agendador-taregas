package com.jadeilton.bff.agendador_tarefas.controller;



import com.jadeilton.bff.agendador_tarefas.business.UsuarioService;
import com.jadeilton.bff.agendador_tarefas.business.dto.EnderecoDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.TelefoneDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário",description = "Cadastro e Login e usários")
public class UsuarioController {


    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar Usuários",description = "Criar um novo Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário salvo com Sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário Já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }


    @PostMapping("/login")
    @Operation(summary = "Login Usuários",description = "Login do Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário logado com Sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {

        return usuarioService.loginUsuario(usuarioDTO);

    }
    @GetMapping
    @Operation(summary = "Buscar Usuários por email",description = "Buscar dados do Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));

    }
    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuários por Id",description = "Deleta Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário Deletado com Sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,@RequestHeader("Authorization") String token) {
        usuarioService.deletarEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "atualizar dados de Usuários",description = "Atualizar dados de Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário atualizado com Sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                                           @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.atualizaDadosUsuari(token, dto));
    }
    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuário",description = "Atualiza Endereço de Usuário")
    @ApiResponse(responseCode = "200",description = "Endereço atualizado com Sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto,token));



    }
    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuário",description = "Atualiza Telefone de Usuário")
    @ApiResponse(responseCode = "200",description = "Telefone atualizado com Sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto,token));



    }


    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuário",description = "Salva Endereço de Usuário")
    @ApiResponse(responseCode = "200",description = "Endereço Salvo com Sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<EnderecoDTO> cadastroEndereco(@RequestBody EnderecoDTO dto, @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,dto));

    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuário",description = "Salva Telefone de Usuário")
    @ApiResponse(responseCode = "200",description = "Telefone Salvo com Sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<TelefoneDTO> cadastroTelefone(@RequestBody TelefoneDTO dto, @RequestHeader("Authorization") String token, Long idTelefone) {



        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,dto, idTelefone));
    }

}


package com.jadeilton.bff.agendador_tarefas.controller;



import com.jadeilton.bff.agendador_tarefas.business.UsuarioService;
import com.jadeilton.bff.agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.jadeilton.bff.agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.jadeilton.bff.agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.jadeilton.bff.agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.jadeilton.bff.agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário",description = "Cadastro e Login de Usuários")
public class UsuarioController {


    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar Usuários",description = "Criar um novo Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário salvo com Sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário Já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public ResponseEntity<UsuarioDTORequest> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }


    @PostMapping("/login")
    @Operation(summary = "Login Usuários",description = "Login do Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário logado com Sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO) {

        return usuarioService.loginUsuario(usuarioDTO);

    }
    @GetMapping
    @Operation(summary = "Buscar Usuários por email",description = "Buscar dados do Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário encontrado")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    public ResponseEntity<UsuarioDTORequest> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token ) {

        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));

    }
    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuários por Id",description = "Deleta Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário Deletado com Sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,@RequestHeader(name = "Authorization", required = false) String token ) {
        usuarioService.deletarEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "atualizar dados de Usuários",description = "Atualizar dados de Usuário")
    @ApiResponse(responseCode = "200",description = "Usuário atualizado com Sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization", required = false) String token ) {

        return ResponseEntity.ok(usuarioService.atualizaDadosUsuari(token, dto));
    }
    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuário",description = "Atualiza Endereço de Usuário")
    @ApiResponse(responseCode = "200",description = "Endereço atualizado com Sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token ) {

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto,token));



    }
    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuário",description = "Atualiza Telefone de Usuário")
    @ApiResponse(responseCode = "200",description = "Telefone atualizado com Sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token ) {

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto,token));



    }


    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuário",description = "Salva Endereço de Usuário")
    @ApiResponse(responseCode = "200",description = "Endereço Salvo com Sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    public ResponseEntity<EnderecoDTOResponse> cadastroEndereco(@RequestBody EnderecoDTORequest dto, @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,dto));

    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuário",description = "Salva Telefone de Usuário")
    @ApiResponse(responseCode = "200",description = "Telefone Salvo com Sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de Servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    public ResponseEntity<TelefoneDTOResponse> cadastroTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader(name = "Authorization", required = false) String token, Long idTelefone) {



        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,dto, idTelefone));
    }

}


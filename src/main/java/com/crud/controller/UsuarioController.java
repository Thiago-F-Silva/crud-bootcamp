package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crud.model.Usuario;
import com.crud.service.UsuarioService;

@Component
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    public void criarUsuario(String nome, String email) {

        Usuario u = new Usuario();

        u.setNome(nome);
        u.setEmail(email);

        service.salvarUsuario(u);

    }

    public Optional<Usuario> buscarPorId(String idString) {

        Long id = Long.parseLong(idString);

        return service.buscarPorId(id);
    }

    public Usuario buscarPorNomeEmail(String nome, String email) {

        return service.buscarPorNomeEmail(nome, email);

    }

    public void atualizarUsuario(String nome, String email, Long id) {

        Usuario u = new Usuario(nome, email, id);

        service.atualizarUsuario(u);

    }

    public boolean autenticacao(String nome, String email) {

        Usuario u = new Usuario();

        u.setEmail(email);
        u.setNome(nome);

        return service.autenticacao(u);

    }

    public void excluirUsuario(String idString) {

        Long id = Long.parseLong(idString);

        service.excluirUsuario(id);

    }

    public List<Usuario> listarUsuarios() {
        return service.listarUsuarios();
    }

}

package com.crud.controller;

import java.util.List;

import com.crud.model.Usuario;
import com.crud.service.UsuarioService;

public class UsuarioController {

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

    public Usuario buscarPorId(String idString) {

        Long id = Long.parseLong(idString);

        return service.buscarPorId(id);
    }

    public void atualizarUsuario(String nome, String email, Long id) {

        Usuario u = new Usuario(nome, email, id);

        service.atualizarUsuario(u);

    }

    public boolean autenticacao(String nome, String email) {

        Usuario u = new Usuario();

        u.setEmail(email);
        u.setNome(nome);

        if (service.autenticacao(u)) {
            System.out.println("usuario nao autenticado");
        }

        return service.autenticacao(u);

    }

    public void excluirUsuario(String nome, String email, String idString) {

        Long id = Long.parseLong(idString);

        Usuario u = new Usuario(nome, email, id);

        service.excluirUsuario(id, u);

    }

    public List<Usuario> listarUsuarios() {
        return service.listarUsuarios();
    }

}

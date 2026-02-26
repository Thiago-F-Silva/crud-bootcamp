package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Usuario;
import com.crud.service.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public void criarUsuario(@RequestBody Usuario usuario) {
        service.salvarUsuario(usuario); 
    }

    public Optional<Usuario> buscarPorId(String idString) {

        Long id = Long.parseLong(idString);

        return service.buscarPorId(id);
    }

    public Usuario buscarPorNomeEmail(String nome, String email) {

        return service.buscarPorNomeEmail(nome, email);

    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        service.atualizarUsuario(usuario);
    }

    public boolean autenticacao(String nome, String email) {

        Usuario u = new Usuario();

        u.setEmail(email);
        u.setNome(nome);

        return service.autenticacao(u);

    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable("id") Long id) {
        service.excluirUsuario(id);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return service.listarUsuarios();
    }

}

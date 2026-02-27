package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public Usuario buscarPorNomeEmail(@RequestParam("nome") String nome, @RequestParam("email") String email) {
        return service.buscarPorNomeEmail(nome, email);

    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        service.atualizarUsuario(usuario);
    }

    @GetMapping("/autenticar")
    public ResponseEntity<Void> autenticacao(@RequestParam("nome") String nome, @RequestParam("email") String email) {
        boolean autenticacao = service.autenticacao(new Usuario(nome, email, null));
            if (autenticacao) {
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.status(403).build();
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

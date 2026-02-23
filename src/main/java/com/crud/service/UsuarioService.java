package com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.crud.model.Usuario;
import com.crud.repository.UsuarioRepository;

@Component
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não pode ser nulo");
        } else if (usuario.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        } else if (!usuario.getNome().matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new IllegalArgumentException("Nome deve conter apenas letras");
        } else if (usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("email não pode estar vazio");
        }

        repository.save(usuario);

    }

    public Optional<Usuario> buscarPorId(Long id) {

        if (id <= 0) {
            throw new RuntimeException("Id inválido");
        }

        Optional<Usuario> usuario = repository.findById(id);

        if (usuario == null) {
            throw new RuntimeException("Usuario não existe");
        }

        return usuario;

    }

    public Usuario buscarPorNomeEmail(String nome, String email) {
        Usuario usuario = repository.findByNomeContainingAndEmail(nome, email);
        if (usuario == null) {
            System.out.println("Usuario não existe");
            return null;
        }
        return usuario;
    }

    public void atualizarUsuario(Usuario usuario) {

        buscarPorId(usuario.getId());

        if (usuario.getNome().isBlank()) {
            throw new RuntimeException("Nome não pode estar em branco");
        } else if (usuario.getEmail().isBlank()) {
            throw new RuntimeException("Email não pode estar vazio");
        }

        repository.save(usuario);
    }

    public boolean autenticacao(Usuario usuario) {

        if (!"ADMINISTRADOR".equals(usuario.getNome()) || !"admin@gmail.com".equals(usuario.getEmail())) {

            throw new RuntimeException("Apenas administradores podem excluir usuarios");
        }

        System.out.println("Autenticado com sucesso");

        return true;

    }

    public void excluirUsuario(Long id) {

        if (id <= 0) {
            throw new RuntimeException("ID inválido");

        }

        Usuario usuario = buscarPorId(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        repository.delete(usuario);

    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

}

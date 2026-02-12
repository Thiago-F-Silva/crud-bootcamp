package com.crud.service;

import java.util.List;

import com.crud.model.Usuario;
import com.crud.repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não pode ser nulo");
        } else if (usuario.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        } else if (usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("email não pode estar vazio");
        }

        repository.salvar(usuario);

    }

    public Usuario buscarPorId(Long id) {

        if (id <= 0) {
            throw new RuntimeException("Id inválido");
        }

        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null) {
            throw new RuntimeException("Usuario não existe");
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

        repository.atualizar(usuario);
    }

    public boolean autenticacao(Usuario usuario) {

            if (!"ADMINISTRADOR".equals(usuario.getNome()) && !"admin@gmail.com".equals(usuario.getEmail())) {

                throw new RuntimeException("Apenas administradores podem excluir usuarios");
            }

        System.out.println("Autenticado com sucesso");

        return true;

    }

    public void excluirUsuario(Long excluirId, Usuario usuario) {

        if (excluirId <= 0) {
            throw new RuntimeException("ID inválido");

        } else if (usuario == null) {
            throw new RuntimeException("Usuario não autenticado");
        }

        repository.deletar(excluirId);

    }

    public List<Usuario> listarUsuarios() {
        return repository.listarUsuarios();
    }

}

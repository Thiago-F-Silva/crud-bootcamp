package com.crud.service;

import java.util.List;

import com.crud.model.Usuario;
import com.crud.repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public void salvarUsuario(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não pode ser nulo");
        } else if (usuario.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        } else if (usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException("email não pode estar vazio");
        }

        usuarioRepository.salvar(usuario);

    }

    public Usuario buscarPorId(Long id) {

        if (id <= 0) {
            throw new RuntimeException("Id inválido");
        }

        Usuario usuario = usuarioRepository.buscarPorId(id);

        if (usuario == null) {
            throw new RuntimeException("Usuario não existe");
        }

        return usuario;

    }

    public void atualizarUsuario(Usuario usuario, Long id) {

        buscarPorId(id);

       if (usuario.getNome().isBlank()) {
            throw new RuntimeException("Nome não pode estar em branco");
        }

        usuarioRepository.atualizar(usuario);
    }

    public void excluirUsuario(Long excluirId, Usuario usuario) {

        if (excluirId <= 0) {
            throw new RuntimeException("ID inválido");

        } else if (usuario == null) {
            throw new RuntimeException("Usuario não autenticado");
        }

        if (!"ADMINISTRADOR".equals(usuario.getNome()) && !"admin@gmail.com".equals(usuario.getEmail())) {
            throw new RuntimeException("Apenas administradores podem excluir usuarios");
        }

        usuarioRepository.deletar(excluirId);

    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

}

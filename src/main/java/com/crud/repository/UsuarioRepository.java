package com.crud.repository;

import java.util.List;

import com.crud.model.Usuario;

public interface UsuarioRepository {

    void salvar(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario buscarPorId(Long id);
    void atualizar(Usuario usuario);
    void deletar(Long id);
    Usuario buscarPorNomeEmail(String nome, String email);
}
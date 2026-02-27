package com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crud.model.Usuario;
import com.crud.repository.UsuarioFiltroRepository;
import com.crud.repository.UsuarioRepository;

@Component
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioFiltroRepository filtroRepository;

    public void salvarUsuario(Usuario usuario) {

        validarUsuario(usuario);
        validarEmail(usuario.getEmail());
        validarNome(usuario.getNome());
        repository.save(usuario);

    }

    public void validarEmail(String email) { 

        if (email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Entrada de email inválida");
        }

    }

    public void validarNome(String nome) {

        if (nome.isBlank() || !nome.matches("[a-zA-ZÀ-ÿ ]+")) {
            throw new IllegalArgumentException("Entrada de nome inválida");
        }

    }

    public void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Entrada de usuário inválida");
        }
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
        Usuario usuario = filtroRepository.findByNomeContainingAndEmail(nome, email);
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

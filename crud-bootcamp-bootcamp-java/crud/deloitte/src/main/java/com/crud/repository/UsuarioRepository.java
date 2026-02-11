package com.crud.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.crud.connection.ConexaoH2;
import com.crud.model.Usuario;

public class UsuarioRepository {
    
    public UsuarioRepository (){
        criarTabela();
    }

    private void criarTabela() {

        String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                id IDENTITY PRIMARY KEY,
                nome VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL
                )
                """;

                try (Connection conn = ConexaoH2.getConnection(); 
                PreparedStatement statement = conn.prepareStatement(sql)){
                    
                    statement.execute();

                } catch (Exception e) {
                    throw new RuntimeException("Erro ao criar tabela usuario", e);
                }
    }

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";

        try (Connection conn = ConexaoH2.getConnection(); 
            PreparedStatement statement = conn.prepareStatement(sql)){

                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getEmail());
                statement.executeUpdate();

                System.out.println("Usuario salvo com sucesso");
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar salvar usuario", e);
        }

    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConexaoH2.getConnection(); 
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Usuario u = new Usuario();
                    u.setNome(resultSet.getString("nome"));
                    u.setId(resultSet.getLong("id"));
                    u.setEmail(resultSet.getString("email"));

                    usuarios.add(u);
                }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar usuarios", e);
        }

        return usuarios;
    }

    public Usuario buscarPorId(Long id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection conn = ConexaoH2.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){

                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Usuario u = new Usuario();
                    u.setId(resultSet.getLong("id"));
                    u.setNome(resultSet.getString("nome"));
                    u.setEmail(resultSet.getString("email"));
                    return u;
                }

                return null;
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar id", e);
        }

    }

    public void atualizar(Usuario usuario){

        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";

        try (Connection conn = ConexaoH2.getConnection(); 
            PreparedStatement statement = conn.prepareStatement(sql)){

                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getEmail());
                statement.setLong(3, usuario.getId());

                statement.executeUpdate();
                System.out.println("Usuario atualizado com sucesso");
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar usuario", e);
        }

    }

    public void deletar(Long id){

        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = ConexaoH2.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setLong(1, id);
                statement.executeUpdate();

            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar usuario", e);
        }
    }

}

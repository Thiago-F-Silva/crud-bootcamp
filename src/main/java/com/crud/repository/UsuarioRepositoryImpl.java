package com.crud.repository;

import java.util.List;

import com.crud.conexaoJPA.JPAUtil;
import com.crud.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    
    public UsuarioRepositoryImpl (){
        // criarTabela();
    }

    // private void criarTabela() {

    //     String sql = """
    //             CREATE TABLE IF NOT EXISTS usuarios (
    //             id IDENTITY PRIMARY KEY,
    //             nome VARCHAR(100) NOT NULL,
    //             email VARCHAR(100) NOT NULL
    //             )
    //             """;

    //             try (Connection conn = ConexaoH2.getConnection(); 
    //             PreparedStatement statement = conn.prepareStatement(sql)){
                    
    //                 statement.execute();

    //             } catch (Exception e) {
    //                 throw new RuntimeException("Erro ao criar tabela usuario", e);
    //             }
    // }

    @Override
    public void salvar(Usuario usuario) {

        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("crudBootcampJava");
        EntityManager manager = managerFactory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
            manager.close();

            System.out.println("Usuario salvo com sucesso");
 

    }

    @Override
    public List<Usuario> listarUsuarios() {

        EntityManager manager = JPAUtil.getEntityManager();

        TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u", Usuario.class);
        List<Usuario> list = query.getResultList();
        manager.close();


        return list;
    }

    @Override
    public Usuario buscarPorId(Long id) {

        EntityManager manager = JPAUtil.getEntityManager();

        manager.getTransaction().begin();
        Usuario usuario = manager.find(Usuario.class, id);
        manager.getTransaction().commit();
        manager.close();

        return usuario;
            

    }

    @Override
    public void atualizar(Usuario usuario){

        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("crudBootcampJava");
        EntityManager manager = managerFactory.createEntityManager();

        manager.getTransaction().begin();

        manager.merge(usuario);

        manager.getTransaction().commit();
        manager.close();

    }

    @Override
    public void deletar(Long id){

        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("crudBootcampJava");
        EntityManager manager = managerFactory.createEntityManager();

        Usuario usuario = manager.find(Usuario.class, id);

        manager.getTransaction().begin();
        manager.remove(usuario);
        manager.getTransaction().commit();
        
    }

}

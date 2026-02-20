package com.crud.conexaoJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    private static final EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("crudBootcampJava");

    public static EntityManager getEntityManager(){
        return managerFactory.createEntityManager();
    } 

}

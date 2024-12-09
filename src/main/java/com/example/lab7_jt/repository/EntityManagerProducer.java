package com.example.lab7_jt.repository;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jdk.jfr.Name;

@Named
@ApplicationScoped
public class EntityManagerProducer{

    private EntityManager em;

    public EntityManager getEntityManager(){
        if(em == null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
            em = emf.createEntityManager();
        }

        return em;
    }

}

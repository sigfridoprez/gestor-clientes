/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.utilerias.entity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author sigfrido
 */
public final class EntityManagerFactory {

    private static final EntityManager entityManager = null;

    public static synchronized EntityManager getEntityManager() {
        if (entityManager == null) {
            return Persistence.createEntityManagerFactory("gestor-clientes-db-accessPU").createEntityManager();
        }
        return entityManager;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.utilerias;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.sig.utilerias.entity.EntityManagerFactory;
import static java.lang.ProcessBuilder.Redirect.from;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        inicializaLookAndFeel();
        inicializaBaseDatos();
    }

    private void inicializaLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
//            UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
//            UIManager.setLookAndFeel(new NimbusLookAndFeel());
//            UIManager.setLookAndFeel(new NimRODLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    private void inicializaBaseDatos() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inicia:RUN:" + new Date());
                EntityManager em = EntityManagerFactory.getEntityManager();
                Query query = em.createNativeQuery("select current_timestamp from sequence");
                query.getResultList();
                System.out.println("Fin:RUN:" + new Date());
            }
        });
    }

}

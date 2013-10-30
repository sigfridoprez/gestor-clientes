/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.utilerias;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        inicializaLookAndFeel();
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

}

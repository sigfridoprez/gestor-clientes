/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.visorcliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "com.sig.visorcliente.CreateUsuario"
)
@ActionRegistration(
        iconBase = "com/sig/visorcliente/resources/usuario_agregar.png",
        displayName = "#CTL_CreateUsuario"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1450),
    @ActionReference(path = "Toolbars/File", position = 300),
    @ActionReference(path = "Shortcuts", name = "D-N")
})
@Messages("CTL_CreateUsuario=Crear Usuario")
public final class CreateUsuario implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        VisorClienteTopComponent tc = VisorClienteTopComponent.findInstance();
        tc.open();
        tc.requestActive();
        tc.resetFields();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.six.expclientes.util;

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;

/**
 *
 * @author sigfrido
 */
public class ClienteRootNode extends AbstractNode {

    public ClienteRootNode(Children children) {
        super(children);
        setDisplayName("Clientes");
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("/com/six/expclientes/resources/usuario_agregar.png");
    }

}

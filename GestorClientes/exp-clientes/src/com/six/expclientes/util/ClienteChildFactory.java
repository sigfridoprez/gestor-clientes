/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.six.expclientes.util;

import com.six.dto.GcliCliente;
import java.awt.Image;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author sigfrido
 */
public class ClienteChildFactory extends ChildFactory<GcliCliente> {

    private final List<GcliCliente> resultList;

    public ClienteChildFactory(List<GcliCliente> resultList) {
        this.resultList = resultList;
    }

    @Override
    protected boolean createKeys(List<GcliCliente> list) {
        for (GcliCliente Customer : resultList) {
            list.add(Customer);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(GcliCliente c) {
        Node node = new AbstractNode(Children.LEAF, Lookups.singleton(c)){

            @Override
            public Image getIcon(int type) {
                return ImageUtilities.loadImage("/com/six/expclientes/resources/usuario_listado.png");
            }
            
        };

        node.setDisplayName(c.getIdPersona().getNombre() + " " + c.getIdPersona().getApellidoPaterno() + " " + c.getIdPersona().getApellidoMaterno());
        node.setShortDescription(c.getIdPersona().getNombre());
        return node;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.six.expclientes.util;

import com.six.dto.GcliCliente;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.DeleteAction;
import org.openide.actions.PropertiesAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.BeanNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.actions.SystemAction;
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
        Node node = new AbstractNode(Children.LEAF, Lookups.singleton(c));
        node.setDisplayName(c.getIdPersona().getNombre() + " " + c.getIdPersona().getApellidoPaterno() + " " + c.getIdPersona().getApellidoMaterno());
        node.setShortDescription(c.getIdPersona().getNombre());
        return node;
    }
}

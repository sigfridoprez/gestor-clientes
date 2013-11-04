/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.six.expclientes.util;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author sigfrido
 */
public class ClienteRootNode extends AbstractNode{

    public ClienteRootNode(Children children) {
        super(children);
        setDisplayName("Root");
    }
    
}

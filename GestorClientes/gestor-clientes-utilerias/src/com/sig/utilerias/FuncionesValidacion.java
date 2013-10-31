/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.utilerias;

/**
 *
 * @author sigfrido
 */
public class FuncionesValidacion {

    public static boolean esValido(Object valor) {
        if (valor != null) {
            if (valor instanceof String) {
                return !"".equals((String) valor);
            }
        }
        return false;
    }
}

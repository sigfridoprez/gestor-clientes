/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.utilerias;

/**
 *
 * @author sigfrido.perez
 */
public class FuncionesCadena {

    public static String limpiaCadenaTelefono(String tel) {
        if (FuncionesValidacion.esValido(tel)) {
            return tel.replace('(', ' ').replace(')', ' ').replaceAll("-", "").replaceAll(" ", "").trim();
        }
        return "0";
    }
}

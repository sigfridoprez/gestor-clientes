/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.six.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author sigfrido
 */
@Embeddable
public class GcliCardexPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    private long idCliente;
    @Basic(optional = false)
    @Column(name = "ID_EXP_ACTUAL")
    private long idExpActual;

    public GcliCardexPK() {
    }

    public GcliCardexPK(long idCliente, long idExpActual) {
        this.idCliente = idCliente;
        this.idExpActual = idExpActual;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdExpActual() {
        return idExpActual;
    }

    public void setIdExpActual(long idExpActual) {
        this.idExpActual = idExpActual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCliente;
        hash += (int) idExpActual;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliCardexPK)) {
            return false;
        }
        GcliCardexPK other = (GcliCardexPK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.idExpActual != other.idExpActual) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliCardexPK[ idCliente=" + idCliente + ", idExpActual=" + idExpActual + " ]";
    }
    
}

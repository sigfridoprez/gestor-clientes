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
public class GcliHistoriaClinicaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    private long idCliente;
    @Basic(optional = false)
    @Column(name = "ID_PROBLEMA")
    private long idProblema;

    public GcliHistoriaClinicaPK() {
    }

    public GcliHistoriaClinicaPK(long idCliente, long idProblema) {
        this.idCliente = idCliente;
        this.idProblema = idProblema;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(long idProblema) {
        this.idProblema = idProblema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCliente;
        hash += (int) idProblema;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliHistoriaClinicaPK)) {
            return false;
        }
        GcliHistoriaClinicaPK other = (GcliHistoriaClinicaPK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.idProblema != other.idProblema) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliHistoriaClinicaPK[ idCliente=" + idCliente + ", idProblema=" + idProblema + " ]";
    }
    
}

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
public class GcliInfAsentamientoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;
    @Basic(optional = false)
    @Column(name = "CODIGO_ASENTAMIENTO")
    private String codigoAsentamiento;

    public GcliInfAsentamientoPK() {
    }

    public GcliInfAsentamientoPK(String codigoPostal, String codigoAsentamiento) {
        this.codigoPostal = codigoPostal;
        this.codigoAsentamiento = codigoAsentamiento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoAsentamiento() {
        return codigoAsentamiento;
    }

    public void setCodigoAsentamiento(String codigoAsentamiento) {
        this.codigoAsentamiento = codigoAsentamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPostal != null ? codigoPostal.hashCode() : 0);
        hash += (codigoAsentamiento != null ? codigoAsentamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfAsentamientoPK)) {
            return false;
        }
        GcliInfAsentamientoPK other = (GcliInfAsentamientoPK) object;
        if ((this.codigoPostal == null && other.codigoPostal != null) || (this.codigoPostal != null && !this.codigoPostal.equals(other.codigoPostal))) {
            return false;
        }
        if ((this.codigoAsentamiento == null && other.codigoAsentamiento != null) || (this.codigoAsentamiento != null && !this.codigoAsentamiento.equals(other.codigoAsentamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfAsentamientoPK[ codigoPostal=" + codigoPostal + ", codigoAsentamiento=" + codigoAsentamiento + " ]";
    }
    
}

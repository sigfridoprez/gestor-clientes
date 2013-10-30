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
public class GcliInfMunicipioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODIGO_ESTADO")
    private String codigoEstado;
    @Basic(optional = false)
    @Column(name = "CODIGO_MUNICIPIO")
    private String codigoMunicipio;

    public GcliInfMunicipioPK() {
    }

    public GcliInfMunicipioPK(String codigoEstado, String codigoMunicipio) {
        this.codigoEstado = codigoEstado;
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEstado != null ? codigoEstado.hashCode() : 0);
        hash += (codigoMunicipio != null ? codigoMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfMunicipioPK)) {
            return false;
        }
        GcliInfMunicipioPK other = (GcliInfMunicipioPK) object;
        if ((this.codigoEstado == null && other.codigoEstado != null) || (this.codigoEstado != null && !this.codigoEstado.equals(other.codigoEstado))) {
            return false;
        }
        if ((this.codigoMunicipio == null && other.codigoMunicipio != null) || (this.codigoMunicipio != null && !this.codigoMunicipio.equals(other.codigoMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfMunicipioPK[ codigoEstado=" + codigoEstado + ", codigoMunicipio=" + codigoMunicipio + " ]";
    }
    
}

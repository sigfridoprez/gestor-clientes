/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.six.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sigfrido
 */
@Entity
@Table(name = "GCLI_INF_ASENTAMIENTO", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliInfAsentamiento.findAll", query = "SELECT g FROM GcliInfAsentamiento g"),
    @NamedQuery(name = "GcliInfAsentamiento.findByCodigoPostal", query = "SELECT g FROM GcliInfAsentamiento g WHERE g.gcliInfAsentamientoPK.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "GcliInfAsentamiento.findByCodigoAsentamiento", query = "SELECT g FROM GcliInfAsentamiento g WHERE g.gcliInfAsentamientoPK.codigoAsentamiento = :codigoAsentamiento"),
    @NamedQuery(name = "GcliInfAsentamiento.findByNombreAsentamiento", query = "SELECT g FROM GcliInfAsentamiento g WHERE g.nombreAsentamiento = :nombreAsentamiento")})
public class GcliInfAsentamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GcliInfAsentamientoPK gcliInfAsentamientoPK;
    @Basic(optional = false)
    @Column(name = "NOMBRE_ASENTAMIENTO")
    private String nombreAsentamiento;
    @JoinColumn(name = "CODIGO_TIPO_ASENTAMIENTO", referencedColumnName = "CODIGO_TIPO_ASENTAMIENTO")
    @ManyToOne(optional = false)
    private GcliInfTipoAsentamiento codigoTipoAsentamiento;
    @JoinColumn(name = "CODIGO_POSTAL", referencedColumnName = "CODIGO_POSTAL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GcliInfCodigoPostal gcliInfCodigoPostal;

    public GcliInfAsentamiento() {
    }

    public GcliInfAsentamiento(GcliInfAsentamientoPK gcliInfAsentamientoPK) {
        this.gcliInfAsentamientoPK = gcliInfAsentamientoPK;
    }

    public GcliInfAsentamiento(GcliInfAsentamientoPK gcliInfAsentamientoPK, String nombreAsentamiento) {
        this.gcliInfAsentamientoPK = gcliInfAsentamientoPK;
        this.nombreAsentamiento = nombreAsentamiento;
    }

    public GcliInfAsentamiento(String codigoPostal, String codigoAsentamiento) {
        this.gcliInfAsentamientoPK = new GcliInfAsentamientoPK(codigoPostal, codigoAsentamiento);
    }

    public GcliInfAsentamientoPK getGcliInfAsentamientoPK() {
        return gcliInfAsentamientoPK;
    }

    public void setGcliInfAsentamientoPK(GcliInfAsentamientoPK gcliInfAsentamientoPK) {
        this.gcliInfAsentamientoPK = gcliInfAsentamientoPK;
    }

    public String getNombreAsentamiento() {
        return nombreAsentamiento;
    }

    public void setNombreAsentamiento(String nombreAsentamiento) {
        this.nombreAsentamiento = nombreAsentamiento;
    }

    public GcliInfTipoAsentamiento getCodigoTipoAsentamiento() {
        return codigoTipoAsentamiento;
    }

    public void setCodigoTipoAsentamiento(GcliInfTipoAsentamiento codigoTipoAsentamiento) {
        this.codigoTipoAsentamiento = codigoTipoAsentamiento;
    }

    public GcliInfCodigoPostal getGcliInfCodigoPostal() {
        return gcliInfCodigoPostal;
    }

    public void setGcliInfCodigoPostal(GcliInfCodigoPostal gcliInfCodigoPostal) {
        this.gcliInfCodigoPostal = gcliInfCodigoPostal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gcliInfAsentamientoPK != null ? gcliInfAsentamientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfAsentamiento)) {
            return false;
        }
        GcliInfAsentamiento other = (GcliInfAsentamiento) object;
        if ((this.gcliInfAsentamientoPK == null && other.gcliInfAsentamientoPK != null) || (this.gcliInfAsentamientoPK != null && !this.gcliInfAsentamientoPK.equals(other.gcliInfAsentamientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfAsentamiento[ gcliInfAsentamientoPK=" + gcliInfAsentamientoPK + " ]";
    }
    
}

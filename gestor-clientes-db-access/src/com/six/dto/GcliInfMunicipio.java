/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.six.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sigfrido
 */
@Entity
@Table(name = "GCLI_INF_MUNICIPIO", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliInfMunicipio.findAll", query = "SELECT g FROM GcliInfMunicipio g"),
    @NamedQuery(name = "GcliInfMunicipio.findByCodigoEstado", query = "SELECT g FROM GcliInfMunicipio g WHERE g.gcliInfMunicipioPK.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "GcliInfMunicipio.findByCodigoMunicipio", query = "SELECT g FROM GcliInfMunicipio g WHERE g.gcliInfMunicipioPK.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "GcliInfMunicipio.findByNombreMunicipio", query = "SELECT g FROM GcliInfMunicipio g WHERE g.nombreMunicipio = :nombreMunicipio")})
public class GcliInfMunicipio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GcliInfMunicipioPK gcliInfMunicipioPK;
    @Basic(optional = false)
    @Column(name = "NOMBRE_MUNICIPIO")
    private String nombreMunicipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gcliInfMunicipio")
    private List<GcliInfCodigoPostal> gcliInfCodigoPostalList;
    @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO_ESTADO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GcliInfEstado gcliInfEstado;

    public GcliInfMunicipio() {
    }

    public GcliInfMunicipio(GcliInfMunicipioPK gcliInfMunicipioPK) {
        this.gcliInfMunicipioPK = gcliInfMunicipioPK;
    }

    public GcliInfMunicipio(GcliInfMunicipioPK gcliInfMunicipioPK, String nombreMunicipio) {
        this.gcliInfMunicipioPK = gcliInfMunicipioPK;
        this.nombreMunicipio = nombreMunicipio;
    }

    public GcliInfMunicipio(String codigoEstado, String codigoMunicipio) {
        this.gcliInfMunicipioPK = new GcliInfMunicipioPK(codigoEstado, codigoMunicipio);
    }

    public GcliInfMunicipioPK getGcliInfMunicipioPK() {
        return gcliInfMunicipioPK;
    }

    public void setGcliInfMunicipioPK(GcliInfMunicipioPK gcliInfMunicipioPK) {
        this.gcliInfMunicipioPK = gcliInfMunicipioPK;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public List<GcliInfCodigoPostal> getGcliInfCodigoPostalList() {
        return gcliInfCodigoPostalList;
    }

    public void setGcliInfCodigoPostalList(List<GcliInfCodigoPostal> gcliInfCodigoPostalList) {
        this.gcliInfCodigoPostalList = gcliInfCodigoPostalList;
    }

    public GcliInfEstado getGcliInfEstado() {
        return gcliInfEstado;
    }

    public void setGcliInfEstado(GcliInfEstado gcliInfEstado) {
        this.gcliInfEstado = gcliInfEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gcliInfMunicipioPK != null ? gcliInfMunicipioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfMunicipio)) {
            return false;
        }
        GcliInfMunicipio other = (GcliInfMunicipio) object;
        if ((this.gcliInfMunicipioPK == null && other.gcliInfMunicipioPK != null) || (this.gcliInfMunicipioPK != null && !this.gcliInfMunicipioPK.equals(other.gcliInfMunicipioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfMunicipio[ gcliInfMunicipioPK=" + gcliInfMunicipioPK + " ]";
    }
    
}

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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "GCLI_INF_CODIGO_POSTAL", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliInfCodigoPostal.findAll", query = "SELECT g FROM GcliInfCodigoPostal g"),
    @NamedQuery(name = "GcliInfCodigoPostal.findByCodigoPostal", query = "SELECT g FROM GcliInfCodigoPostal g WHERE g.codigoPostal = :codigoPostal")})
public class GcliInfCodigoPostal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gcliInfCodigoPostal")
    private List<GcliInfAsentamiento> gcliInfAsentamientoList;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_ESTADO", referencedColumnName = "CODIGO_ESTADO"),
        @JoinColumn(name = "CODIGO_MUNICIPIO", referencedColumnName = "CODIGO_MUNICIPIO")})
    @ManyToOne(optional = false)
    private GcliInfMunicipio gcliInfMunicipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPostal")
    private List<GcliInfDomicilio> gcliInfDomicilioList;

    public GcliInfCodigoPostal() {
    }

    public GcliInfCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public List<GcliInfAsentamiento> getGcliInfAsentamientoList() {
        return gcliInfAsentamientoList;
    }

    public void setGcliInfAsentamientoList(List<GcliInfAsentamiento> gcliInfAsentamientoList) {
        this.gcliInfAsentamientoList = gcliInfAsentamientoList;
    }

    public GcliInfMunicipio getGcliInfMunicipio() {
        return gcliInfMunicipio;
    }

    public void setGcliInfMunicipio(GcliInfMunicipio gcliInfMunicipio) {
        this.gcliInfMunicipio = gcliInfMunicipio;
    }

    public List<GcliInfDomicilio> getGcliInfDomicilioList() {
        return gcliInfDomicilioList;
    }

    public void setGcliInfDomicilioList(List<GcliInfDomicilio> gcliInfDomicilioList) {
        this.gcliInfDomicilioList = gcliInfDomicilioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPostal != null ? codigoPostal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfCodigoPostal)) {
            return false;
        }
        GcliInfCodigoPostal other = (GcliInfCodigoPostal) object;
        if ((this.codigoPostal == null && other.codigoPostal != null) || (this.codigoPostal != null && !this.codigoPostal.equals(other.codigoPostal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfCodigoPostal[ codigoPostal=" + codigoPostal + " ]";
    }
    
}

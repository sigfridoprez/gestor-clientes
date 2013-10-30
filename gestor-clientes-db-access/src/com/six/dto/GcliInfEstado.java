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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sigfrido
 */
@Entity
@Table(name = "GCLI_INF_ESTADO", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliInfEstado.findAll", query = "SELECT g FROM GcliInfEstado g"),
    @NamedQuery(name = "GcliInfEstado.findByCodigoEstado", query = "SELECT g FROM GcliInfEstado g WHERE g.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "GcliInfEstado.findByNombreEstado", query = "SELECT g FROM GcliInfEstado g WHERE g.nombreEstado = :nombreEstado")})
public class GcliInfEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_ESTADO")
    private String codigoEstado;
    @Basic(optional = false)
    @Column(name = "NOMBRE_ESTADO")
    private String nombreEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gcliInfEstado")
    private List<GcliInfMunicipio> gcliInfMunicipioList;

    public GcliInfEstado() {
    }

    public GcliInfEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public GcliInfEstado(String codigoEstado, String nombreEstado) {
        this.codigoEstado = codigoEstado;
        this.nombreEstado = nombreEstado;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public List<GcliInfMunicipio> getGcliInfMunicipioList() {
        return gcliInfMunicipioList;
    }

    public void setGcliInfMunicipioList(List<GcliInfMunicipio> gcliInfMunicipioList) {
        this.gcliInfMunicipioList = gcliInfMunicipioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEstado != null ? codigoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfEstado)) {
            return false;
        }
        GcliInfEstado other = (GcliInfEstado) object;
        if ((this.codigoEstado == null && other.codigoEstado != null) || (this.codigoEstado != null && !this.codigoEstado.equals(other.codigoEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfEstado[ codigoEstado=" + codigoEstado + " ]";
    }
    
}

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
@Table(name = "GCLI_INF_TIPO_ASENTAMIENTO", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliInfTipoAsentamiento.findAll", query = "SELECT g FROM GcliInfTipoAsentamiento g"),
    @NamedQuery(name = "GcliInfTipoAsentamiento.findByCodigoTipoAsentamiento", query = "SELECT g FROM GcliInfTipoAsentamiento g WHERE g.codigoTipoAsentamiento = :codigoTipoAsentamiento"),
    @NamedQuery(name = "GcliInfTipoAsentamiento.findByNombreTipoAsentamiento", query = "SELECT g FROM GcliInfTipoAsentamiento g WHERE g.nombreTipoAsentamiento = :nombreTipoAsentamiento")})
public class GcliInfTipoAsentamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_TIPO_ASENTAMIENTO")
    private String codigoTipoAsentamiento;
    @Basic(optional = false)
    @Column(name = "NOMBRE_TIPO_ASENTAMIENTO")
    private String nombreTipoAsentamiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTipoAsentamiento")
    private List<GcliInfAsentamiento> gcliInfAsentamientoList;

    public GcliInfTipoAsentamiento() {
    }

    public GcliInfTipoAsentamiento(String codigoTipoAsentamiento) {
        this.codigoTipoAsentamiento = codigoTipoAsentamiento;
    }

    public GcliInfTipoAsentamiento(String codigoTipoAsentamiento, String nombreTipoAsentamiento) {
        this.codigoTipoAsentamiento = codigoTipoAsentamiento;
        this.nombreTipoAsentamiento = nombreTipoAsentamiento;
    }

    public String getCodigoTipoAsentamiento() {
        return codigoTipoAsentamiento;
    }

    public void setCodigoTipoAsentamiento(String codigoTipoAsentamiento) {
        this.codigoTipoAsentamiento = codigoTipoAsentamiento;
    }

    public String getNombreTipoAsentamiento() {
        return nombreTipoAsentamiento;
    }

    public void setNombreTipoAsentamiento(String nombreTipoAsentamiento) {
        this.nombreTipoAsentamiento = nombreTipoAsentamiento;
    }

    public List<GcliInfAsentamiento> getGcliInfAsentamientoList() {
        return gcliInfAsentamientoList;
    }

    public void setGcliInfAsentamientoList(List<GcliInfAsentamiento> gcliInfAsentamientoList) {
        this.gcliInfAsentamientoList = gcliInfAsentamientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTipoAsentamiento != null ? codigoTipoAsentamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfTipoAsentamiento)) {
            return false;
        }
        GcliInfTipoAsentamiento other = (GcliInfTipoAsentamiento) object;
        if ((this.codigoTipoAsentamiento == null && other.codigoTipoAsentamiento != null) || (this.codigoTipoAsentamiento != null && !this.codigoTipoAsentamiento.equals(other.codigoTipoAsentamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfTipoAsentamiento[ codigoTipoAsentamiento=" + codigoTipoAsentamiento + " ]";
    }
    
}

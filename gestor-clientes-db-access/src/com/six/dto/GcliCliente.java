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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "GCLI_CLIENTE", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliCliente.findAll", query = "SELECT g FROM GcliCliente g"),
    @NamedQuery(name = "GcliCliente.findByIdCliente", query = "SELECT g FROM GcliCliente g WHERE g.idCliente = :idCliente")})
public class GcliCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "ID_CLIENTE")
    private Long idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gcliCliente")
    private List<GcliHistoriaClinica> gcliHistoriaClinicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gcliCliente")
    private List<GcliCardex> gcliCardexList;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private GcliInfPersona idPersona;

    public GcliCliente() {
    }

    public GcliCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<GcliHistoriaClinica> getGcliHistoriaClinicaList() {
        return gcliHistoriaClinicaList;
    }

    public void setGcliHistoriaClinicaList(List<GcliHistoriaClinica> gcliHistoriaClinicaList) {
        this.gcliHistoriaClinicaList = gcliHistoriaClinicaList;
    }

    public List<GcliCardex> getGcliCardexList() {
        return gcliCardexList;
    }

    public void setGcliCardexList(List<GcliCardex> gcliCardexList) {
        this.gcliCardexList = gcliCardexList;
    }

    public GcliInfPersona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(GcliInfPersona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliCliente)) {
            return false;
        }
        GcliCliente other = (GcliCliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliCliente[ idCliente=" + idCliente + " ]";
    }
    
}

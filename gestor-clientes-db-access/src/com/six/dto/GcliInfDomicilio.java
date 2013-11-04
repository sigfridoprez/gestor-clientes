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
import javax.persistence.TableGenerator;

/**
 *
 * @author sigfrido
 */
@Entity
@Table(name = "GCLI_INF_DOMICILIO", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliInfDomicilio.findAll", query = "SELECT g FROM GcliInfDomicilio g"),
    @NamedQuery(name = "GcliInfDomicilio.findByIdDomicilio", query = "SELECT g FROM GcliInfDomicilio g WHERE g.idDomicilio = :idDomicilio"),
    @NamedQuery(name = "GcliInfDomicilio.findByCalle", query = "SELECT g FROM GcliInfDomicilio g WHERE g.calle = :calle"),
    @NamedQuery(name = "GcliInfDomicilio.findByNumeroExterior", query = "SELECT g FROM GcliInfDomicilio g WHERE g.numeroExterior = :numeroExterior"),
    @NamedQuery(name = "GcliInfDomicilio.findByNumeroInterior", query = "SELECT g FROM GcliInfDomicilio g WHERE g.numeroInterior = :numeroInterior")})
public class GcliInfDomicilio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEC_DOMICILIO", allocationSize = 1, initialValue = 0)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEC_DOMICILIO")
    @Basic(optional = false)
    @Column(name = "ID_DOMICILIO")
    private Long idDomicilio;
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "NUMERO_EXTERIOR")
    private String numeroExterior;
    @Column(name = "NUMERO_INTERIOR")
    private String numeroInterior;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDomicilio")
    private List<GcliInfPersona> gcliInfPersonaList;
    @JoinColumn(name = "CODIGO_POSTAL", referencedColumnName = "CODIGO_POSTAL")
    @ManyToOne(optional = false)
    private GcliInfCodigoPostal codigoPostal;

    public GcliInfDomicilio() {
    }

    public GcliInfDomicilio(Long idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public Long getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Long idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public List<GcliInfPersona> getGcliInfPersonaList() {
        return gcliInfPersonaList;
    }

    public void setGcliInfPersonaList(List<GcliInfPersona> gcliInfPersonaList) {
        this.gcliInfPersonaList = gcliInfPersonaList;
    }

    public GcliInfCodigoPostal getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(GcliInfCodigoPostal codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDomicilio != null ? idDomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfDomicilio)) {
            return false;
        }
        GcliInfDomicilio other = (GcliInfDomicilio) object;
        if ((this.idDomicilio == null && other.idDomicilio != null) || (this.idDomicilio != null && !this.idDomicilio.equals(other.idDomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfDomicilio[ idDomicilio=" + idDomicilio + " ]";
    }

}

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sigfrido
 */
@Entity
@Table(name = "GCLI_INF_PROBLEMA_MEDICO", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliInfProblemaMedico.findAll", query = "SELECT g FROM GcliInfProblemaMedico g"),
    @NamedQuery(name = "GcliInfProblemaMedico.findByIdProblema", query = "SELECT g FROM GcliInfProblemaMedico g WHERE g.idProblema = :idProblema"),
    @NamedQuery(name = "GcliInfProblemaMedico.findByDescProblema", query = "SELECT g FROM GcliInfProblemaMedico g WHERE g.descProblema = :descProblema")})
public class GcliInfProblemaMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "ID_PROBLEMA")
    private Integer idProblema;
    @Basic(optional = false)
    @Column(name = "DESC_PROBLEMA")
    private String descProblema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gcliInfProblemaMedico")
    private List<GcliHistoriaClinica> gcliHistoriaClinicaList;

    public GcliInfProblemaMedico() {
    }

    public GcliInfProblemaMedico(Integer idProblema) {
        this.idProblema = idProblema;
    }

    public GcliInfProblemaMedico(Integer idProblema, String descProblema) {
        this.idProblema = idProblema;
        this.descProblema = descProblema;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(Integer idProblema) {
        this.idProblema = idProblema;
    }

    public String getDescProblema() {
        return descProblema;
    }

    public void setDescProblema(String descProblema) {
        this.descProblema = descProblema;
    }

    public List<GcliHistoriaClinica> getGcliHistoriaClinicaList() {
        return gcliHistoriaClinicaList;
    }

    public void setGcliHistoriaClinicaList(List<GcliHistoriaClinica> gcliHistoriaClinicaList) {
        this.gcliHistoriaClinicaList = gcliHistoriaClinicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProblema != null ? idProblema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfProblemaMedico)) {
            return false;
        }
        GcliInfProblemaMedico other = (GcliInfProblemaMedico) object;
        if ((this.idProblema == null && other.idProblema != null) || (this.idProblema != null && !this.idProblema.equals(other.idProblema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfProblemaMedico[ idProblema=" + idProblema + " ]";
    }
    
}

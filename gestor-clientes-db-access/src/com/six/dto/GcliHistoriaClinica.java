/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.six.dto;

import java.io.Serializable;
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
@Table(name = "GCLI_HISTORIA_CLINICA", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliHistoriaClinica.findAll", query = "SELECT g FROM GcliHistoriaClinica g"),
    @NamedQuery(name = "GcliHistoriaClinica.findByIdCliente", query = "SELECT g FROM GcliHistoriaClinica g WHERE g.gcliHistoriaClinicaPK.idCliente = :idCliente"),
    @NamedQuery(name = "GcliHistoriaClinica.findByIdProblema", query = "SELECT g FROM GcliHistoriaClinica g WHERE g.gcliHistoriaClinicaPK.idProblema = :idProblema"),
    @NamedQuery(name = "GcliHistoriaClinica.findByObservaciones", query = "SELECT g FROM GcliHistoriaClinica g WHERE g.observaciones = :observaciones")})
public class GcliHistoriaClinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GcliHistoriaClinicaPK gcliHistoriaClinicaPK;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_PROBLEMA", referencedColumnName = "ID_PROBLEMA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GcliInfProblemaMedico gcliInfProblemaMedico;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GcliCliente gcliCliente;

    public GcliHistoriaClinica() {
    }

    public GcliHistoriaClinica(GcliHistoriaClinicaPK gcliHistoriaClinicaPK) {
        this.gcliHistoriaClinicaPK = gcliHistoriaClinicaPK;
    }

    public GcliHistoriaClinica(long idCliente, long idProblema) {
        this.gcliHistoriaClinicaPK = new GcliHistoriaClinicaPK(idCliente, idProblema);
    }

    public GcliHistoriaClinicaPK getGcliHistoriaClinicaPK() {
        return gcliHistoriaClinicaPK;
    }

    public void setGcliHistoriaClinicaPK(GcliHistoriaClinicaPK gcliHistoriaClinicaPK) {
        this.gcliHistoriaClinicaPK = gcliHistoriaClinicaPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public GcliInfProblemaMedico getGcliInfProblemaMedico() {
        return gcliInfProblemaMedico;
    }

    public void setGcliInfProblemaMedico(GcliInfProblemaMedico gcliInfProblemaMedico) {
        this.gcliInfProblemaMedico = gcliInfProblemaMedico;
    }

    public GcliCliente getGcliCliente() {
        return gcliCliente;
    }

    public void setGcliCliente(GcliCliente gcliCliente) {
        this.gcliCliente = gcliCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gcliHistoriaClinicaPK != null ? gcliHistoriaClinicaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliHistoriaClinica)) {
            return false;
        }
        GcliHistoriaClinica other = (GcliHistoriaClinica) object;
        if ((this.gcliHistoriaClinicaPK == null && other.gcliHistoriaClinicaPK != null) || (this.gcliHistoriaClinicaPK != null && !this.gcliHistoriaClinicaPK.equals(other.gcliHistoriaClinicaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliHistoriaClinica[ gcliHistoriaClinicaPK=" + gcliHistoriaClinicaPK + " ]";
    }
    
}

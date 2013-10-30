/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.six.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sigfrido
 */
@Entity
@Table(name = "GCLI_CARDEX", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliCardex.findAll", query = "SELECT g FROM GcliCardex g"),
    @NamedQuery(name = "GcliCardex.findByIdCliente", query = "SELECT g FROM GcliCardex g WHERE g.gcliCardexPK.idCliente = :idCliente"),
    @NamedQuery(name = "GcliCardex.findByIdExpActual", query = "SELECT g FROM GcliCardex g WHERE g.gcliCardexPK.idExpActual = :idExpActual"),
    @NamedQuery(name = "GcliCardex.findByFecha", query = "SELECT g FROM GcliCardex g WHERE g.fecha = :fecha"),
    @NamedQuery(name = "GcliCardex.findByBHistorico", query = "SELECT g FROM GcliCardex g WHERE g.bHistorico = :bHistorico"),
    @NamedQuery(name = "GcliCardex.findByNombreArchivo", query = "SELECT g FROM GcliCardex g WHERE g.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "GcliCardex.findByAltoOblea", query = "SELECT g FROM GcliCardex g WHERE g.altoOblea = :altoOblea"),
    @NamedQuery(name = "GcliCardex.findByDistanciaInterPupilar", query = "SELECT g FROM GcliCardex g WHERE g.distanciaInterPupilar = :distanciaInterPupilar"),
    @NamedQuery(name = "GcliCardex.findByAgudezaVisual", query = "SELECT g FROM GcliCardex g WHERE g.agudezaVisual = :agudezaVisual"),
    @NamedQuery(name = "GcliCardex.findByOjoDerecho", query = "SELECT g FROM GcliCardex g WHERE g.ojoDerecho = :ojoDerecho"),
    @NamedQuery(name = "GcliCardex.findByAddDerecho", query = "SELECT g FROM GcliCardex g WHERE g.addDerecho = :addDerecho"),
    @NamedQuery(name = "GcliCardex.findByQueratometriaOd", query = "SELECT g FROM GcliCardex g WHERE g.queratometriaOd = :queratometriaOd"),
    @NamedQuery(name = "GcliCardex.findByOjoIzquierdo", query = "SELECT g FROM GcliCardex g WHERE g.ojoIzquierdo = :ojoIzquierdo"),
    @NamedQuery(name = "GcliCardex.findByAddIzquierdo", query = "SELECT g FROM GcliCardex g WHERE g.addIzquierdo = :addIzquierdo"),
    @NamedQuery(name = "GcliCardex.findByQueratometriaOi", query = "SELECT g FROM GcliCardex g WHERE g.queratometriaOi = :queratometriaOi"),
    @NamedQuery(name = "GcliCardex.findByObservaciones", query = "SELECT g FROM GcliCardex g WHERE g.observaciones = :observaciones")})
public class GcliCardex implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GcliCardexPK gcliCardexPK;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "B_HISTORICO")
    private Serializable bHistorico;
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ALTO_OBLEA")
    private Double altoOblea;
    @Column(name = "DISTANCIA_INTER_PUPILAR")
    private Double distanciaInterPupilar;
    @Column(name = "AGUDEZA_VISUAL")
    private String agudezaVisual;
    @Basic(optional = false)
    @Column(name = "OJO_DERECHO")
    private String ojoDerecho;
    @Column(name = "ADD_DERECHO")
    private Integer addDerecho;
    @Column(name = "QUERATOMETRIA_OD")
    private String queratometriaOd;
    @Basic(optional = false)
    @Column(name = "OJO_IZQUIERDO")
    private String ojoIzquierdo;
    @Column(name = "ADD_IZQUIERDO")
    private Integer addIzquierdo;
    @Column(name = "QUERATOMETRIA_OI")
    private String queratometriaOi;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_USUARIO_ATENDIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private GcliUsuario idUsuarioAtendio;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GcliCliente gcliCliente;

    public GcliCardex() {
    }

    public GcliCardex(GcliCardexPK gcliCardexPK) {
        this.gcliCardexPK = gcliCardexPK;
    }

    public GcliCardex(GcliCardexPK gcliCardexPK, Date fecha, Serializable bHistorico, String ojoDerecho, String ojoIzquierdo) {
        this.gcliCardexPK = gcliCardexPK;
        this.fecha = fecha;
        this.bHistorico = bHistorico;
        this.ojoDerecho = ojoDerecho;
        this.ojoIzquierdo = ojoIzquierdo;
    }

    public GcliCardex(long idCliente, long idExpActual) {
        this.gcliCardexPK = new GcliCardexPK(idCliente, idExpActual);
    }

    public GcliCardexPK getGcliCardexPK() {
        return gcliCardexPK;
    }

    public void setGcliCardexPK(GcliCardexPK gcliCardexPK) {
        this.gcliCardexPK = gcliCardexPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Serializable getBHistorico() {
        return bHistorico;
    }

    public void setBHistorico(Serializable bHistorico) {
        this.bHistorico = bHistorico;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Double getAltoOblea() {
        return altoOblea;
    }

    public void setAltoOblea(Double altoOblea) {
        this.altoOblea = altoOblea;
    }

    public Double getDistanciaInterPupilar() {
        return distanciaInterPupilar;
    }

    public void setDistanciaInterPupilar(Double distanciaInterPupilar) {
        this.distanciaInterPupilar = distanciaInterPupilar;
    }

    public String getAgudezaVisual() {
        return agudezaVisual;
    }

    public void setAgudezaVisual(String agudezaVisual) {
        this.agudezaVisual = agudezaVisual;
    }

    public String getOjoDerecho() {
        return ojoDerecho;
    }

    public void setOjoDerecho(String ojoDerecho) {
        this.ojoDerecho = ojoDerecho;
    }

    public Integer getAddDerecho() {
        return addDerecho;
    }

    public void setAddDerecho(Integer addDerecho) {
        this.addDerecho = addDerecho;
    }

    public String getQueratometriaOd() {
        return queratometriaOd;
    }

    public void setQueratometriaOd(String queratometriaOd) {
        this.queratometriaOd = queratometriaOd;
    }

    public String getOjoIzquierdo() {
        return ojoIzquierdo;
    }

    public void setOjoIzquierdo(String ojoIzquierdo) {
        this.ojoIzquierdo = ojoIzquierdo;
    }

    public Integer getAddIzquierdo() {
        return addIzquierdo;
    }

    public void setAddIzquierdo(Integer addIzquierdo) {
        this.addIzquierdo = addIzquierdo;
    }

    public String getQueratometriaOi() {
        return queratometriaOi;
    }

    public void setQueratometriaOi(String queratometriaOi) {
        this.queratometriaOi = queratometriaOi;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public GcliUsuario getIdUsuarioAtendio() {
        return idUsuarioAtendio;
    }

    public void setIdUsuarioAtendio(GcliUsuario idUsuarioAtendio) {
        this.idUsuarioAtendio = idUsuarioAtendio;
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
        hash += (gcliCardexPK != null ? gcliCardexPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliCardex)) {
            return false;
        }
        GcliCardex other = (GcliCardex) object;
        if ((this.gcliCardexPK == null && other.gcliCardexPK != null) || (this.gcliCardexPK != null && !this.gcliCardexPK.equals(other.gcliCardexPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliCardex[ gcliCardexPK=" + gcliCardexPK + " ]";
    }
    
}

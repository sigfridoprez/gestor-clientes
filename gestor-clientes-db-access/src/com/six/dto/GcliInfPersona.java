/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.six.dto;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "GCLI_INF_PERSONA", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliInfPersona.findAll", query = "SELECT g FROM GcliInfPersona g"),
    @NamedQuery(name = "GcliInfPersona.findByIdPersona", query = "SELECT g FROM GcliInfPersona g WHERE g.idPersona = :idPersona"),
    @NamedQuery(name = "GcliInfPersona.findByNombre", query = "SELECT g FROM GcliInfPersona g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GcliInfPersona.findByApellidoPaterno", query = "SELECT g FROM GcliInfPersona g WHERE g.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "GcliInfPersona.findByApellidoMaterno", query = "SELECT g FROM GcliInfPersona g WHERE g.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "GcliInfPersona.findByTelefonoFijo", query = "SELECT g FROM GcliInfPersona g WHERE g.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "GcliInfPersona.findByTelefonoCelular", query = "SELECT g FROM GcliInfPersona g WHERE g.telefonoCelular = :telefonoCelular"),
    @NamedQuery(name = "GcliInfPersona.findByCorreoElectronico", query = "SELECT g FROM GcliInfPersona g WHERE g.correoElectronico = :correoElectronico")})
public class GcliInfPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "ID_PERSONA")
    private Long idPersona;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @Column(name = "TELEFONO_FIJO")
    private BigInteger telefonoFijo;
    @Column(name = "TELEFONO_CELULAR")
    private BigInteger telefonoCelular;
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    @JoinColumn(name = "ID_DOMICILIO", referencedColumnName = "ID_DOMICILIO")
    @ManyToOne(optional = false)
    private GcliInfDomicilio idDomicilio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<GcliCliente> gcliClienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<GcliUsuario> gcliUsuarioList;

    public GcliInfPersona() {
    }

    public GcliInfPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public GcliInfPersona(Long idPersona, String nombre, String apellidoPaterno) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public BigInteger getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(BigInteger telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public BigInteger getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(BigInteger telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public GcliInfDomicilio getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(GcliInfDomicilio idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public List<GcliCliente> getGcliClienteList() {
        return gcliClienteList;
    }

    public void setGcliClienteList(List<GcliCliente> gcliClienteList) {
        this.gcliClienteList = gcliClienteList;
    }

    public List<GcliUsuario> getGcliUsuarioList() {
        return gcliUsuarioList;
    }

    public void setGcliUsuarioList(List<GcliUsuario> gcliUsuarioList) {
        this.gcliUsuarioList = gcliUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliInfPersona)) {
            return false;
        }
        GcliInfPersona other = (GcliInfPersona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.six.dto.GcliInfPersona[ idPersona=" + idPersona + " ]";
    }
    
}

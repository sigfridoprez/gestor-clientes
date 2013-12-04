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
@Table(name = "GCLI_USUARIO", catalog = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "GcliUsuario.findAll", query = "SELECT g FROM GcliUsuario g"),
    @NamedQuery(name = "GcliUsuario.findByIdUsuario", query = "SELECT g FROM GcliUsuario g WHERE g.idUsuario = :idUsuario"),
    @NamedQuery(name = "GcliUsuario.findByUsuario", query = "SELECT g FROM GcliUsuario g WHERE g.usuario = :usuario"),
    @NamedQuery(name = "GcliUsuario.findByPassword", query = "SELECT g FROM GcliUsuario g WHERE g.password = :password"),
    @NamedQuery(name = "GcliUsuario.findByBAdministrador", query = "SELECT g FROM GcliUsuario g WHERE g.bAdministrador = :bAdministrador")})
public class GcliUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEC_USUARIO",allocationSize = 1, initialValue = 0)
    @GeneratedValue(strategy=GenerationType.TABLE, generator = "SEC_USUARIO")
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "B_ADMINISTRADOR")
    private Boolean bAdministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioAtendio")
    private List<GcliCardex> gcliCardexList;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")
    @ManyToOne(optional = false)
    private GcliInfPersona idPersona;

    public GcliUsuario() {
    }

    public GcliUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public GcliUsuario(Long idUsuario, String usuario, String password, Boolean bAdministrador) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.bAdministrador = bAdministrador;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBAdministrador() {
        return bAdministrador;
    }

    public void setBAdministrador(Boolean bAdministrador) {
        this.bAdministrador = bAdministrador;
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
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcliUsuario)) {
            return false;
        }
        GcliUsuario other = (GcliUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getIdPersona().getNombre() + " " + this.getIdPersona().getApellidoPaterno() + " " + this.getIdPersona().getApellidoMaterno();
    }
    
}

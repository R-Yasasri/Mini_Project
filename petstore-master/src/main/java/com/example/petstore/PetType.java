/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.petstore;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajitha Yasasri
 */
@Entity
@Table(name = "pettype")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Pettype.findAll", query = "SELECT p FROM PetType p")
        , @NamedQuery(name = "Pettype.findByIdpetType", query = "SELECT p FROM PetType p WHERE p.idpetType = :idpetType")
        , @NamedQuery(name = "Pettype.findByPetType", query = "SELECT p FROM PetType p WHERE p.petType = :petType")
        , @NamedQuery(name = "Pettype.findByStatus", query = "SELECT p FROM PetType p WHERE p.status = :status")})
public class PetType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpetType")
    private Integer idpetType;
    @Size(max = 45)
    @Column(name = "petType")
    private String petType;
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpetType")
    private transient Collection<Pet> petCollection;

    public PetType() {
    }

    public PetType(Integer idpetType) {
        this.idpetType = idpetType;
    }

    public Integer getIdpetType() {
        return idpetType;
    }

    public void setIdpetType(Integer idpetType) {
        this.idpetType = idpetType;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Pet> getPetCollection() {
        return petCollection;
    }

    public void setPetCollection(Collection<Pet> petCollection) {
        this.petCollection = petCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpetType != null ? idpetType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PetType)) {
            return false;
        }
        PetType other = (PetType) object;
        if ((this.idpetType == null && other.idpetType != null) || (this.idpetType != null && !this.idpetType.equals(other.idpetType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pettype[ idpetType=" + idpetType + " ]";
    }

}

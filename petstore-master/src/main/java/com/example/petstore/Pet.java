package com.example.petstore;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pet")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p")
		, @NamedQuery(name = "Pet.findByPetId", query = "SELECT p FROM Pet p WHERE p.petId = :petId")
		, @NamedQuery(name = "Pet.findByPetAge", query = "SELECT p FROM Pet p WHERE p.petAge = :petAge")
		, @NamedQuery(name = "Pet.findByPetName", query = "SELECT p FROM Pet p WHERE p.petName = :petName")})
public class Pet implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "petId")
	private Integer petId;
	@Column(name = "petAge")
	private Integer petAge;
	@Size(max = 45)
	@Column(name = "petName")
	private String petName;
	@JoinColumn(name = "idpetType", referencedColumnName = "idpetType")
	@ManyToOne(optional = false)
	private PetType idpetType;

	public Pet() {
	}

	public Pet(Integer petId) {
		this.petId = petId;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Integer getPetAge() {
		return petAge;
	}

	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public PetType getIdpetType() {
		return idpetType;
	}

	public void setIdpetType(PetType idpetType) {
		this.idpetType = idpetType;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (petId != null ? petId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Pet)) {
			return false;
		}
		Pet other = (Pet) object;
		if ((this.petId == null && other.petId != null) || (this.petId != null && !this.petId.equals(other.petId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.Pet[ petId=" + petId + " ]";
	}

}

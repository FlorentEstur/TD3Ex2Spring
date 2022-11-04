package com.inti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="professeur_TD3")
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Professeur {
	
	@Id
	private int id;
	private String nom, prenom;
	private double salaire;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Professeur_Etudiant", joinColumns = @JoinColumn(name="idProfesseur"),
	inverseJoinColumns = @JoinColumn(name="idEtudiant"))
	private List<Etudiant> listeEtudiant;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Ecole_Professeur", joinColumns = @JoinColumn(name="idProfesseur"),
	inverseJoinColumns = @JoinColumn(name="idEcole"))
	private List<Ecole> listeEcole;
}

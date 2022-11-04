package com.inti.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Etudiant2")
@Data @NoArgsConstructor @AllArgsConstructor
public class Etudiant {

	@Id
	private int id;
	private String nom, prenom, email, telephone;
	private int anneeEtude;
}

package com.inti.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inti.model.Professeur;

@Repository
public interface IProfesseurRepository extends JpaRepository<Professeur, Integer> {

	@Query(value = "select max(id) from professeur_TD3", nativeQuery = true)
	int findMaxId();
}

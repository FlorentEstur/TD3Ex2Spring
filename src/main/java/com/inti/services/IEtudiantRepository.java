package com.inti.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Etudiant;

@Repository
public interface IEtudiantRepository extends JpaRepository<Etudiant, Integer>{

}

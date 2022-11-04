package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Professeur;
import com.inti.services.IProfesseurRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("professeur")
@Slf4j
public class ProfesseurController {
	
	@Autowired
	IProfesseurRepository ipr;
	
	@GetMapping("listeProfesseur")
	public List<Professeur> getAllEcole()
	{
		log.info("Voici la liste des professeurs");
		return ipr.findAll();
	}
	
	@GetMapping("getProfesseur/{id}")
	public Professeur getProfesseur(@PathVariable int id)
	{
		int idMax = ipr.findMaxId();
		if (id > 0 && id<=idMax)
		{
		log.info("Voici le professeur avec l'id "+id);
		return ipr.findById(id).get();
		}
		log.error("professeur inexistant");
		return null;
	}
	
	@PostMapping("saveProfesseur")
	public boolean saveProfesseur(@RequestBody Professeur prof)
	{
		if (prof!=null)
		{
			log.info("le professeur a bien été sauvegardé");
			ipr.save(prof);
			return true;
		}
		log.error("le professeur n'a pas pu être sauvegardé");
		return false;
	}
	
	@DeleteMapping("deleteProfesseur/{id}")
	public boolean deleteProfesseur(@PathVariable int id)
	{
		int idMax = ipr.findMaxId();
		if (id > 0 && id<=idMax)
		{
			log.info("Le professeur a bien été supprimé");
			ipr.deleteById(id);
			return true;
		}
		log.error("Le professeur n'a pas pu être supprimé");
		return false;
	}
	
	@PutMapping("updateProfesseur/{id}")
	public Professeur updateProfesseur(@RequestBody Professeur newProfesseur, @PathVariable int id)
	{
		return ipr.findById(id).map(professeur ->{
			professeur.setNom(newProfesseur.getNom());
			professeur.setPrenom(newProfesseur.getPrenom());
			professeur.setSalaire(newProfesseur.getSalaire());
			return ipr.save(professeur);
		}).orElseGet(() ->{
			return ipr.save(newProfesseur);
		});
	}

}

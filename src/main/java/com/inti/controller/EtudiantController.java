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

import com.inti.model.Etudiant;
import com.inti.services.IEtudiantRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("etudiant")
@Slf4j
public class EtudiantController {

	@Autowired
	IEtudiantRepository ier;
	
	@GetMapping("listeEtudiant")
	public List<Etudiant> getAllEtudiant()
	{
		log.info("Voici la liste des étudiants");
		return ier.findAll();
	}
	
	@GetMapping("getEtudiant/{id}")
	public Etudiant getEtudiant(@PathVariable int id)
	{
		int idMax = ier.findMaxId();
		if (id > 0 && id<=idMax)
		{
		log.info("Voici l'étudiant avec l'id "+id);
		return ier.findById(id).get();
		}
		log.error("étudiant inexistant");
		return null;
	}
	
	@PostMapping("saveEtu")
	public boolean saveEtudiant(@RequestBody Etudiant etu)
	{
		if (etu!=null)
		{
			log.info("l'étudiant a bien été sauvegardé");
			ier.save(etu);
			return true;
		}
		log.error("l'étudiant n'a pas pu être sauvegardé");
		return false;
	}
	
	@DeleteMapping("deleteEtu/{id}")
	public boolean deleteEtudiant(@PathVariable int id)
	{
		int idMax = ier.findMaxId();
		if (id > 0 && id<=idMax)
		{
			log.info("L'étudiant a bien été supprimé");
			ier.deleteById(id);
			return true;
		}
		log.error("L'étudiant n'a pas pu être supprimé");
		return false;
	}
	
	@PutMapping("updateEtu/{id}")
	public Etudiant updateEtudiant(@RequestBody Etudiant newEtudiant, @PathVariable int id)
	{
		return ier.findById(id).map(etudiant ->{
			etudiant.setNom(newEtudiant.getNom());
			etudiant.setPrenom(newEtudiant.getPrenom());
			etudiant.setEmail(newEtudiant.getEmail());
			etudiant.setTelephone(newEtudiant.getTelephone());
			etudiant.setAnneeEtude(newEtudiant.getAnneeEtude());
			return ier.save(newEtudiant);
		}).orElseGet(() ->{
			return ier.save(newEtudiant);
		});
	
	}
}

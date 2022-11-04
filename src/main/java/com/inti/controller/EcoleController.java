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

import com.inti.model.Ecole;
import com.inti.services.IEcoleRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("ecole")
@Slf4j
public class EcoleController {
	
	@Autowired
	IEcoleRepository ier;
	
	@GetMapping("listeEcole")
	public List<Ecole> getAllEcole()
	{
		log.info("Voici la liste des écoles");
		return ier.findAll();
	}
	
	@GetMapping("getEcole/{id}")
	public Ecole getEcole(@PathVariable int id)
	{
		int idMax = ier.findMaxId();
		if (id > 0 && id<=idMax)
		{
		log.info("Voici l'école avec l'id "+id);
		return ier.findById(id).get();
		}
		log.error("école inexistante");
		return null;
	}
	
	@PostMapping("saveEcole")
	public boolean saveEcole(@RequestBody Ecole ecole)
	{
		if (ecole!=null)
		{
			log.info("l'école a bien été sauvegardé");
			ier.save(ecole);
			return true;
		}
		log.error("l'école n'a pas pu être sauvegardé");
		return false;
	}
	
	@DeleteMapping("deleteEcole/{id}")
	public boolean deleteEcole(@PathVariable int id)
	{
		int idMax = ier.findMaxId();
		if (id > 0 && id<=idMax)
		{
			log.info("L'école a bien été supprimé");
			ier.deleteById(id);
			return true;
		}
		log.error("L'école n'a pas pu être supprimé");
		return false;
	}
	
	@PutMapping("updateEcole/{id}")
	public Ecole updateEcole(@RequestBody Ecole newEcole, @PathVariable int id)
	{
		return ier.findById(id).map(ecole ->{
			ecole.setNom(newEcole.getNom());
			ecole.setAdresse(newEcole.getAdresse());
			ecole.setCp(newEcole.getCp());
			ecole.setVille(newEcole.getVille());
			return ier.save(ecole);
		}).orElseGet(() ->{
			return ier.save(newEcole);
		});
	}
}

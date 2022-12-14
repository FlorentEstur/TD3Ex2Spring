package com.inti.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inti.model.Ecole;

@Repository
public interface IEcoleRepository extends JpaRepository<Ecole, Integer> {
	
	@Query(value = "select max(id) from ecole_TD3", nativeQuery = true)
	int findMaxId();

}

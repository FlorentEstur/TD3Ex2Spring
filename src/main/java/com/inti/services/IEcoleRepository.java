package com.inti.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Ecole;

@Repository
public interface IEcoleRepository extends JpaRepository<Ecole, Integer> {

}

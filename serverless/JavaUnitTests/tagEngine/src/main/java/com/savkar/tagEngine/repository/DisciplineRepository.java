package com.savkar.tagEngine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.savkar.tagEngine.entity.Discipline;

public interface DisciplineRepository extends CrudRepository<Discipline, Long>{
	
	List<Discipline> findByName(String name);

}

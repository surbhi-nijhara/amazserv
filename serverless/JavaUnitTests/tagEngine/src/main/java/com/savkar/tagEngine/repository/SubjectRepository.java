package com.savkar.tagEngine.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.savkar.tagEngine.entity.Discipline;
import com.savkar.tagEngine.entity.Subject;



public interface SubjectRepository extends CrudRepository<Subject,Long> {
	public Subject findByName(String name);
	
	public List<Subject> findByDiscipline(Discipline d);
	
	public Subject getSubjectByNameAndDiscipline(String name, Discipline discipline);
}

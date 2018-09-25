package com.savkar.tagEngine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savkar.tagEngine.entity.Discipline;
import com.savkar.tagEngine.model.DisciplineModel;
import com.savkar.tagEngine.repository.DisciplineRepository;

@Service

public class DisciplineService {
    @Autowired
	DisciplineRepository repository;
	public DisciplineModel saveDiscipline (DisciplineModel disciplineModel) {
		Discipline discipline = translateToDiscipline(disciplineModel);
		return translateToDisciplineModel(repository.save(discipline));
		
	}
	

	
	public Discipline translateToDiscipline(DisciplineModel disciplineModel) {
		if(disciplineModel.getId() != null) {
			return new Discipline(disciplineModel.getId(),disciplineModel.getName());
		}
		else {
			return new Discipline(disciplineModel.getName());
		}
	}
	
	public DisciplineModel translateToDisciplineModel(Discipline discipline) {
		return DisciplineModel.builder().id(discipline.getId()).name(discipline.getName()).build();
	}

	public List<Discipline> getDiscipline(String discipline) {
		return repository.findByName(discipline);
	}
	
	public List<Discipline> getAllDiscipline() {
		return (List<Discipline>) repository.findAll();
	}
}

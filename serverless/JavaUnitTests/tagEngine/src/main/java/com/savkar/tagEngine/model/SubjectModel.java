package com.savkar.tagEngine.model;

import java.util.Set;
import java.util.UUID;

import com.savkar.tagEngine.model.DisciplineModel.DisciplineModelBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectModel {
	private Long id;
	private String name;
	private String subjectPrerequisite;
	private DisciplineModel discipline;
	Set<SubjectModel> childSubject;
	Long collegeTypeId;
	CollegeTypeModel collegeTypeModel;
	
	public SubjectModel() {
		
	}
	
	public SubjectModel(Long id, String name, String subjectPrerequisite, DisciplineModel discipline) {
		super();
		this.id = id;
		this.name = name;
		this.subjectPrerequisite = subjectPrerequisite;
		this.discipline = discipline;
	}
	
	public SubjectModel(Long id, String name, String subjectPrerequisite, DisciplineModel discipline,
			Set<SubjectModel> childSubject) {
		super();
		this.id = id;
		this.name = name;
		this.subjectPrerequisite = subjectPrerequisite;
		this.discipline = discipline;
		this.childSubject = childSubject;
	}
 
	public SubjectModel(Long id, String name, String subjectPrerequisite, DisciplineModel discipline,
			Set<SubjectModel> childSubject, Long collegeTypeId) {
		super();
		this.id = id;
		this.name = name;
		this.subjectPrerequisite = subjectPrerequisite;
		this.discipline = discipline;
		this.childSubject = childSubject;
		this.collegeTypeId = collegeTypeId;
	}
	
	
	
	@Override
	public String toString() {
		return "SubjectModel [id=" + id + ", name=" + name + ", discipline=" + discipline + "]";
	}

	public SubjectModel(Long id, String name, String subjectPrerequisite, DisciplineModel discipline,
			Set<SubjectModel> childSubject, Long collegeTypeId, CollegeTypeModel collegeTypeModel) {
		super();
		this.id = id;
		this.name = name;
		this.subjectPrerequisite = subjectPrerequisite;
		this.discipline = discipline;
		this.childSubject = childSubject;
		this.collegeTypeId = collegeTypeId;
		this.collegeTypeModel = collegeTypeModel;
	}

	

	

	
	
	

	
	
	
}

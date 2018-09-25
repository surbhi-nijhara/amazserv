package com.savkar.tagEngine.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.savkar.tagEngine.entity.CollegeType;
import com.savkar.tagEngine.entity.Discipline;
import com.savkar.tagEngine.entity.Subject;
import com.savkar.tagEngine.model.CollegeTypeModel;
import com.savkar.tagEngine.model.DisciplineModel;
import com.savkar.tagEngine.model.SubjectModel;
import com.savkar.tagEngine.repository.CollegeTypeRepository;
import com.savkar.tagEngine.repository.DisciplineRepository;
import com.savkar.tagEngine.repository.SubjectRepository;

@Service
public class SubjectService {
	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired 
	DisciplineService disciplineService;
	
	@Autowired 
	DisciplineRepository disciplineRepo;
	
	@Autowired 
	CollegeTypeRepository collegeTypeRepository;
	
	
	public SubjectModel saveSubject(SubjectModel subjectModel){
		CollegeType collegeType = collegeTypeRepository.findById(subjectModel.getCollegeTypeId()).get();
		List<Discipline> disciplineList = disciplineService.getDiscipline(subjectModel.getDiscipline().getName());
		DisciplineModel model;
		if(disciplineList.size() == 0) {
			model = disciplineService.saveDiscipline(subjectModel.getDiscipline());
		}
		else {
			model = disciplineService.translateToDisciplineModel(disciplineList.get(0));
		}
		
		Discipline discipline = disciplineService.translateToDiscipline(model);
		
		if(subjectModel.getSubjectPrerequisite() != null && subjectModel.getSubjectPrerequisite() != "") {
			Subject savedSubjectPrerequisite = subjectRepo.getSubjectByNameAndDiscipline(subjectModel.getSubjectPrerequisite(), discipline);
			if(savedSubjectPrerequisite == null) {
				Subject subjectPrerequisite = translateToSubject(subjectModel.getSubjectPrerequisite(),	 model, collegeType);
				savedSubjectPrerequisite = subjectRepo.save(subjectPrerequisite);
			}
			Subject subjectToSave = subjectRepo.getSubjectByNameAndDiscipline(subjectModel.getName(), discipline);
			if(subjectToSave == null) {
				subjectToSave = translateToSubject(subjectModel, model, collegeType); 	
			}
			Set<Subject> childSubjectList = subjectToSave.getChild_subjects();
			if(childSubjectList == null) {
				childSubjectList = new HashSet<Subject>();
			}
			childSubjectList.add(savedSubjectPrerequisite);
			subjectToSave.setChild_subjects(childSubjectList);
			return translateToSubjectModel(subjectRepo.save(subjectToSave));
		}
		else {
			Subject subject = subjectRepo.getSubjectByNameAndDiscipline(subjectModel.getName(), discipline);
			if(subject == null) {
				Subject saveSubject = translateToSubject(subjectModel, model, collegeType);
				return translateToSubjectModel(subjectRepo.save(saveSubject));
			}else {
				return null;
			}
		}
		
	}
	
	public List<SubjectModel> getSubjectByName(String name){
		Subject subject = subjectRepo.findByName(name);
		SubjectModel model = translateToSubjectModel(subject);
		List<SubjectModel> modelList = new ArrayList<SubjectModel>();
		modelList.add(model);
		return modelList;
	}
	
	public List<SubjectModel> getAllSubjects() {
		List<Subject> subjects =  (List<Subject>) subjectRepo.findAll();
		return subjects.stream().map(sub -> translateToSubjectModel(sub)).collect(Collectors.toList());
	}
	
	public Subject getSubjectEntityByName(String name){
		Subject subject = subjectRepo.findByName(name);
		return subject;
	}
	
	public List<SubjectModel> getSubjectByDisciplineName(String name){
		List<Discipline> disciplineList = disciplineRepo.findByName(name);
		if(disciplineList.size() > 0){
			Discipline discipline = disciplineList.get(0);
			List<Subject> subjectList = subjectRepo.findByDiscipline(discipline);
			if(subjectList.size() > 0){
				List<SubjectModel> subjectModelList = new ArrayList<>();
				for (Subject subject : subjectList) {
					subjectModelList.add(translateToSubjectModel(subject));
				}
				return subjectModelList;
			}
			else{
				return null;
			}
			
		}
		else{
			return null;
		}
	}
	
	public Subject translateToSubject(SubjectModel subjectModel, DisciplineModel discipline, CollegeType collegeType) {
		return new Subject(subjectModel.getName(), new Discipline(discipline.getId(), discipline.getName()), collegeType);
	}
	
	public Subject translateToSubject(String subjectPrerequisite, DisciplineModel discipline, CollegeType collegeType) {
		return new Subject(subjectPrerequisite, new Discipline(discipline.getId(), discipline.getName()), collegeType);
	}
	
	
	public SubjectModel translateToSubjectModel(Subject subject){
		
		SubjectModel subjectModel = SubjectModel.builder().id(subject.getId()).name(subject.getName()).discipline(disciplineService.translateToDisciplineModel(subject.getDiscipline())).build();
		subjectModel.setCollegeTypeModel(CollegeTypeModel.builder().id(subject.getCollegeType().getId()).name(subject.getCollegeType().getName()).build());
		if(subject.getChild_subjects() !=null && subject.getChild_subjects().size() >  0) {
			Set<SubjectModel> modelList = new HashSet<SubjectModel>();
			Set<Subject> childSubjects = subject.getChild_subjects();
			for (Subject subjectObj : childSubjects) {
				modelList.add(translateToSubjectModel(subjectObj));
			}
			subjectModel.setChildSubject(modelList);
		}
		return subjectModel;
	}

	
}

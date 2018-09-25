package com.savkar.tagEngine.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savkar.tagEngine.entity.Discipline;
import com.savkar.tagEngine.entity.Subject;
import com.savkar.tagEngine.entity.Tag;
import com.savkar.tagEngine.model.DisciplineModel;
import com.savkar.tagEngine.model.SubjectModel;
import com.savkar.tagEngine.model.TagModel;
import com.savkar.tagEngine.repository.DisciplineRepository;
import com.savkar.tagEngine.repository.SubjectRepository;
import com.savkar.tagEngine.repository.TagRepository;

@Service

public class TagService {
	
	private static final Logger logger = LogManager.getLogger(TagService.class);
	
    @Autowired
	TagRepository tagRepository;
    
    @Autowired
	SubjectRepository subjectRepository;
    
    @Autowired
    SubjectService subjectService;
    
    @Autowired
    DisciplineRepository disciplineRepository;
    
    @Autowired
    EntityManager entityManager;
	
    public TagModel saveTag(TagModel tagModel){
    	String subjectName = tagModel.getSubject().getName();
    	Subject subject = subjectService.getSubjectEntityByName(subjectName);
    	if(subject != null){
    		Tag pTag = null;
    		if(tagModel.getTag() != null) {
    			pTag = tagRepository.getTagByNameAndSubject(tagModel.getTag().getName(), subject);
    			if(pTag == null) {
    				pTag = tagRepository.save(translateToTagEntity(tagModel.getTag(),subject,null, tagModel.getCreatedBy(), tagModel.getModifiedBy()));
    			}
    		}
    		
    		Tag tagToSave = translateToTagEntity(tagModel, subject, pTag, tagModel.getCreatedBy(), tagModel.getModifiedBy());
    		return translateToTagModel(tagRepository.save(tagToSave));
    	}
    	else{
    		return null;	
    	}
    	
    }
    
    public TagModel getTagBySubjectName(String subName) {
    	return null;
    }
    
    public Optional<Tag> getTagById(Long tagId) {
    	return tagRepository.findById(tagId);
    }
    
    public TagModel translateToTagModel(Tag tag){
    	TagModel tagModel = new TagModel();
    	tagModel.setId(tag.getId());
    	tagModel.setName(tag.getName());
    	tagModel.setSubject(subjectService.translateToSubjectModel(tag.getSubject()));
    	if(tag.getTag() != null) {
    		TagModel pTagModel = new TagModel();
    		pTagModel.setId(tag.getTag().getId());
    		pTagModel.setName(tag.getTag().getName());
    		pTagModel.setSubject(subjectService.translateToSubjectModel(tag.getTag().getSubject()));
    		
    		tagModel.setTag(pTagModel);
    	}
    	return tagModel;
    }
    
    private Tag translateToTagEntity(TagModel model, Subject sub, Tag pTag, int createdBy, int modifiedBy){
		return new Tag(model.getName(), sub, pTag, createdBy, modifiedBy);
	}

	public List<TagModel> getTagsByName(String name) {
		List<Tag> tags =  (List<Tag>) tagRepository.getTagByName("%"+name+"%");
		return tags.stream().map(tag -> translateToTagModel(tag)).collect(Collectors.toList());
	}
	
	 private List<TagModel> getTagsBySubjectName(String subName) {
	    	//Get subject by name
	    	Subject sub = subjectService.getSubjectEntityByName(subName);
	    	// Get tags by ID
	    	if(Objects.nonNull(sub)) {    		 
	    		List<Tag> tags = tagRepository.getTagBySubjectId(sub.getId());
	    		return tags.stream().map(tag -> translateToTagModel(tag)).collect(Collectors.toList());
	    	}else {
	    		logger.debug("Subjects not found for name "+subName);
	    		return null;
	    	}
	    	
	    }
	 
	    private List<TagModel> getTagsBySubjectId(Long id) {
	   		 
    		List<Tag> tags = tagRepository.getTagBySubjectId(id);
    		return tags.stream().map(tag -> translateToTagModel(tag)).collect(Collectors.toList());
    	
    }
	    
	    public List<TagModel> getTagsByDisciplineName(String discipline){
	    	
	    	List<SubjectModel> subs = subjectService.getSubjectByDisciplineName(discipline);
	    	List<TagModel> tags = new ArrayList<TagModel>();
	    	if(subs != null) {
	    		for (SubjectModel sub : subs) {
		    		tags.addAll(getTagsBySubjectId(sub.getId()));
		    	}
	    	}
	    	
	    	  	
	    	return tags;
	    }
	    
	    public List<TagModel> getTagsByDisciplineNameAndTagName(String tagName, String discipline){
	    	
	    	List<SubjectModel> subs = subjectService.getSubjectByDisciplineName(discipline);
	    	List<TagModel> tags = new ArrayList<TagModel>();
	    	if(subs != null) {
	    		for (SubjectModel sub : subs) {
	    			List<Tag> tagList = tagRepository.getTagByNameAndSubject("%"+tagName+"%", sub.getId());
	    			tags.addAll(tagList.stream().map(tag -> translateToTagModel(tag)).collect(Collectors.toList()));
		    	}
	    	}
	    	
	    	  	
	    	return tags;
	    }
	    
	    public List<TagModel> getTagsByDisciplineSubjectAndTagName(String tagName, String subName, String discipline){
	    	Discipline disciplineEntity = disciplineRepository.findByName(discipline).get(0);
	    	Subject subject = subjectRepository.getSubjectByNameAndDiscipline(subName, disciplineEntity);
	    	List<TagModel> tags = new ArrayList<TagModel>();
	    	List<Tag> tagList = tagRepository.getTagByNameAndSubject("%"+tagName+"%", subject.getId());
	    	tags = tagList.stream().map(tag -> translateToTagModel(tag)).collect(Collectors.toList());
	    	return tags;
	    }
	    
	    public List<TagModel> getTagsByDisciplineAndSubjectName(String subName, String discipline){
	    	Discipline disciplineEntity = disciplineRepository.findByName(discipline).get(0);
	    	Subject subject = subjectRepository.getSubjectByNameAndDiscipline(subName, disciplineEntity);
	    	
	    	List<TagModel> tags = new ArrayList<TagModel>();
	    	if(subject != null) {
	    		List<Tag> tagList = tagRepository.getTagBySubjectId(subject.getId());
		    	tags =  tagList.stream().map(tag -> translateToTagModel(tag)).collect(Collectors.toList());
	    	}
	    	return tags;
	    }

	public List<TagModel> getTags(Optional<String> name, Optional<String> subject, Optional<String> discipline) {
		List<TagModel> tags = null;	
		if(name.isPresent() && subject.isPresent() && discipline.isPresent()) {
			tags = getTagsByDisciplineSubjectAndTagName(name.get(), subject.get(), discipline.get());
			return tags;
		}
		
		if(name.isPresent() && subject.isPresent()) {
			Subject sub = subjectService.getSubjectEntityByName(subject.get());
			List<Tag> tagList = tagRepository.getTagByNameAndSubject("%"+name.get()+"%", sub.getId());
			tags = tagList.stream().map(tag -> translateToTagModel(tag)).collect(Collectors.toList());
			return tags;
		}
		
		if(name.isPresent() && discipline.isPresent()) {
			tags = getTagsByDisciplineNameAndTagName(name.get(), discipline.get());
			return tags;
		}
		
		if(subject.isPresent() && discipline.isPresent()) {
			tags = getTagsByDisciplineAndSubjectName(subject.get(), discipline.get());
			return tags;
		}
		
		if(name.isPresent()) {
			tags = getTagsByName(name.get());
			return tags;
		}
		if(subject.isPresent()) {
			tags = getTagsBySubjectName(subject.get());
			return tags;
		}
		if(discipline.isPresent()) {
			tags = getTagsByDisciplineName(discipline.get());
			return tags;
		}
		
		
		
		return tags;
	}

	public List<TagModel> getAllTags() {
		List<Tag> tagList = (List<Tag>) tagRepository.findAll();
		return tagList.stream().map(tag -> translateToTagModel(tag)).collect(Collectors.toList());
	}
	
	public TagModel updateTag(Long tagId, TagModel tagModel) {
		Optional<Tag> tagObject = tagRepository.findById(tagId);
		if(tagObject.isPresent()) {
			Tag tag = tagObject.get();
			String newTagName = tagModel.getName();
			Subject subject = subjectRepository.findById(tag.getSubject().getId()).get();
			Tag existingTag = tagRepository.getTagByNameAndSubject(newTagName, subject);
			if(existingTag == null) {
				tag.setName(newTagName);
				return translateToTagModel(tagRepository.save(tag));
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
}

package com.savkar.tagEngine.model;

import com.savkar.tagEngine.entity.Subject;
import com.savkar.tagEngine.entity.Tag;
import com.savkar.tagEngine.entity.Tag.TagBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagModel {
	private Long id;
	private String name;
	private SubjectModel subject;	
	private TagModel tag;
	private String parentname;
	private Long subjectid;
	private int createdBy;
	private int modifiedBy;
	
	public TagModel() {
		
	}
	public TagModel(Long id, String name, SubjectModel subjectModel, TagModel tagModel, String parentname, Long subjectid) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subjectModel;
		this.tag = tagModel;
		this.parentname = parentname;
		this.subjectid = subjectid;
	}
	public TagModel(Long id, String name, SubjectModel subject, TagModel tag, String parentname, Long subjectid,
			int createdBy, int modifiedBy) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.tag = tag;
		this.parentname = parentname;
		this.subjectid = subjectid;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}	
	
	
}

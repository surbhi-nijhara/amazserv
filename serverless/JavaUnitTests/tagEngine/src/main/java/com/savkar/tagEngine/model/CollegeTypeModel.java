package com.savkar.tagEngine.model;

import java.util.Set;

import com.savkar.tagEngine.model.SubjectModel.SubjectModelBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollegeTypeModel {
	private Long id;
	private String name;
	public CollegeTypeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollegeTypeModel(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}

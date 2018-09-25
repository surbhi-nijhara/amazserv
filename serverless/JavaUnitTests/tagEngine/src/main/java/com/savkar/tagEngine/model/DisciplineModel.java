package com.savkar.tagEngine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisciplineModel {
	
	private Long id;
	private String name;
	
public DisciplineModel(){}
	
	public DisciplineModel(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public DisciplineModel( String name) {
		this.name = name;
	}
}

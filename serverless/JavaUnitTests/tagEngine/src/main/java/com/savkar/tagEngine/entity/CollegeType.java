package com.savkar.tagEngine.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.savkar.tagEngine.entity.Subject.SubjectBuilder;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "college_type")
@Data
@Builder
public class CollegeType {
	
	@Id
	@Column(name = "id", unique=true, updatable = false, nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;

	public CollegeType() {
		super();
	}

	public CollegeType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}

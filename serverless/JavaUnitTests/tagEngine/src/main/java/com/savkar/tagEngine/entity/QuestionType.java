package com.savkar.tagEngine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.savkar.tagEngine.entity.Tag.TagBuilder;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "question_type")
@Data
@Builder
public class QuestionType {
	@Id
	//@Column(name = "id", unique=true, updatable = false, nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;

	public QuestionType() {
		
	}

	public QuestionType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public QuestionType(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "QuestionType [id=" + id + ", name=" + name + "]";
	}
	
	
	
}

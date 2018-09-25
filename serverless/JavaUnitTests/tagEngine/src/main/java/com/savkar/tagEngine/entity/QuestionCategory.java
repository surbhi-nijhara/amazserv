package com.savkar.tagEngine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.savkar.tagEngine.entity.QuestionType.QuestionTypeBuilder;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "question_category")
@Data
@Builder
public class QuestionCategory {
	@Id
	//@Column(name = "id", unique=true, updatable = false, nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;

	public QuestionCategory() {
		
	}

	public QuestionCategory(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public QuestionCategory(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "QuestionCategory [id=" + id + ", name=" + name + "]";
	}

	
}

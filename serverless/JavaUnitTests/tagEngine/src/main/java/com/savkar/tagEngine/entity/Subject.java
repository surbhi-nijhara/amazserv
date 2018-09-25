package com.savkar.tagEngine.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savkar.tagEngine.entity.Discipline.DisciplineBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

@Entity
@Table(name = "subject")
@Data
@Builder
@EqualsAndHashCode(exclude= {"child_subjects"})
public class Subject {
	@Id
	//@Column(name = "id", unique=true, updatable = false, nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "discipline_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Discipline discipline;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "subject_prerequisite", joinColumns = { @JoinColumn(name = "subject_id") }, inverseJoinColumns = { @JoinColumn(name = "prerequisite_subject_id") })
	@Singular
	Set<Subject> child_subjects;
	
	@ManyToMany(mappedBy = "child_subjects")
	@Singular
    private Set<Subject> parent_subjects;
	
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "college_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CollegeType collegeType;

	protected Subject() {
		
	}

	public Subject(String name, Discipline discipline) {
		super();
		this.name = name;
		this.discipline = discipline;
	}

	public Subject(Long id, String name, Discipline discipline) {
		super();
		this.id = id;
		this.name = name;
		this.discipline = discipline;
	}
	
	public Subject(String name, Discipline discipline, CollegeType collegeType) {
		super();
		this.name = name;
		this.discipline = discipline;
		this.collegeType = collegeType;
	}
	
	public Subject(Long id, String name, Discipline discipline, Set<Subject> parent_subjects,
			Set<Subject> child_subjects) {
		super();
		this.id = id;
		this.name = name;
		this.discipline = discipline;
		this.parent_subjects = parent_subjects;
		this.child_subjects = child_subjects;
	}
	
	public Subject(Long id, String name, Discipline discipline, Set<Subject> child_subjects,
			Set<Subject> parent_subjects, CollegeType collegeType) {
		super();
		this.id = id;
		this.name = name;
		this.discipline = discipline;
		this.child_subjects = child_subjects;
		this.parent_subjects = parent_subjects;
		this.collegeType = collegeType;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", discipline=" + discipline + "]";
	}

	

	

	


	
}

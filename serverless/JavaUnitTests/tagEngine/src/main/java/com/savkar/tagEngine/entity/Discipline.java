package com.savkar.tagEngine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
@Entity
@Table(name = "discipline")
public class Discipline{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id", updatable = false, nullable = false,columnDefinition = "int")
	private Long id;
 
	@Column(name = "name")
	private String name;
   
	public Discipline(){}
	
	public Discipline(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Discipline(String name) {
		this.name = name;
	}	

	@Override
	public String toString() {
		return "Discipline [id=" + id + ", name=" + name + "]";
	}
	
	
}

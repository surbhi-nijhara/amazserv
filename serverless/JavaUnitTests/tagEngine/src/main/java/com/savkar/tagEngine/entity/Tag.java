package com.savkar.tagEngine.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savkar.tagEngine.entity.Subject.SubjectBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Entity
@Table(name = "tag")
@Data
@Builder
@EqualsAndHashCode(exclude= {"questions"})
public class Tag {
	@Id
	@TableGenerator(name="tagTableGen",initialValue=1849,allocationSize=1)
	@Column(name = "id", unique=true, updatable = false, nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator="tagTableGen")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "subject_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Subject subject;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "parent_tag_id", nullable=true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Tag tag;
	
	
	@ManyToMany(mappedBy = "tags",fetch = FetchType.EAGER)
	@Singular
    private Set<Question> questions;
	
	@Column(name = "created_by")
	private Integer created_by;
	
	@Column(name = "modified_by")
	private Integer modified_by;
	
	@Column(name = "date_created")
	private LocalDateTime date_created = LocalDateTime.now();
	
	@Column(name = "date_modified")
	private LocalDateTime date_modified = LocalDateTime.now();
	
	@Column(name = "is_active")
	private Boolean is_active = true;

	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public Tag(String name, Subject subject, Tag tag) {
		super();
		this.name = name;
		this.subject = subject;
		this.tag = tag;
	}
	
	public Tag(String name, Subject subject, Tag tag, Integer created_by, Integer modified_by) {
		super();
		this.name = name;
		this.subject = subject;
		this.tag = tag;
		this.created_by = created_by;
		this.modified_by = modified_by;
	}

	public Tag(Long id, String name, Subject subject, Tag tag, Set<Question> questions) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.tag = tag;
		this.questions = questions;
	}
	
	public Tag(Long id, String name, Subject subject, Tag tag, Set<Question> questions, Integer created_by,
			Integer modified_by, LocalDateTime date_created, LocalDateTime date_modified, Boolean is_active) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.tag = tag;
		this.questions = questions;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.date_created = date_created;
		this.date_modified = date_modified;
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", subject=" + subject + ", tag=" + tag +  "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (questions!= null && !questions.equals(other.questions))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (subject!=null&&!subject.equals(other.subject))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (tag!=null && !tag.equals(other.tag))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	
	
	
	
	

	
}

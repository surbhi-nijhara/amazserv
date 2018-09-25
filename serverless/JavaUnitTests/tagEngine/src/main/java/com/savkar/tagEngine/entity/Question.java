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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savkar.tagEngine.entity.Tag.TagBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Entity
@Table(name = "question")
@Data(staticConstructor="of")
@Builder
public class Question {
	@Id
	//@Column(name = "id", unique=true, updatable = false, nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "question_title")
	private String question_title;
	
	@Column(name = "question_formula")
	private String question_formula;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "question_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private QuestionType questionType;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "question_category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private QuestionCategory questionCategory;
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
	@JoinTable(name = "question_tag", joinColumns = { @JoinColumn(name = "question_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	@Singular
	Set<Tag> tags;
	
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
	
	@Column(name = "is_answer_visible")
	private Boolean is_answer_visible;

	public Question() {

	}

	public Question(Long id, String question_title, String question_formula, QuestionType questionType,
			QuestionCategory questionCategory, Set<Tag> tags) {
		super();
		this.id = id;
		this.question_title = question_title;
		this.question_formula = question_formula;
		this.questionType = questionType;
		this.questionCategory = questionCategory;
		this.tags = tags;
	}

	public Question(String question_title, String question_formula, QuestionType questionType,
			QuestionCategory questionCategory, Set<Tag> tags, Integer created_by, Integer modified_by, boolean is_answer_visible) {
		super();
		this.question_title = question_title;
		this.question_formula = question_formula;
		this.questionType = questionType;
		this.questionCategory = questionCategory;
		this.tags = tags;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.is_answer_visible = is_answer_visible;
	}
	
	public Question(Long id, String question_title, String question_formula, QuestionType questionType,
			QuestionCategory questionCategory, Set<Tag> tags, Integer created_by, Integer modified_by,
			LocalDateTime date_created, LocalDateTime date_modified, Boolean is_active) {
		super();
		this.id = id;
		this.question_title = question_title;
		this.question_formula = question_formula;
		this.questionType = questionType;
		this.questionCategory = questionCategory;
		this.tags = tags;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.date_created = date_created;
		this.date_modified = date_modified;
		this.is_active = is_active;
	}

	
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", question_title=" + question_title + ", question_formula=" + question_formula
				+ ", questionType=" + questionType + ", questionCategory=" + questionCategory +  "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (questionCategory == null) {
			if (other.questionCategory != null)
				return false;
		} else if (!questionCategory.equals(other.questionCategory))
			return false;
		if (questionType == null) {
			if (other.questionType != null)
				return false;
		} else if (!questionType.equals(other.questionType))
			return false;
		if (question_formula == null) {
			if (other.question_formula != null)
				return false;
		} else if (!question_formula.equals(other.question_formula))
			return false;
		if (question_title == null) {
			if (other.question_title != null)
				return false;
		} else if (!question_title.equals(other.question_title))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (tags!=null && !tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((questionCategory == null) ? 0 : questionCategory.hashCode());
		result = prime * result + ((questionType == null) ? 0 : questionType.hashCode());
		result = prime * result + ((question_formula == null) ? 0 : question_formula.hashCode());
		result = prime * result + ((question_title == null) ? 0 : question_title.hashCode());
		return result;
	}

	public Question(Long id, String question_title, String question_formula, QuestionType questionType,
			QuestionCategory questionCategory, Set<Tag> tags, Integer created_by, Integer modified_by,
			LocalDateTime date_created, LocalDateTime date_modified, Boolean is_active, Boolean is_answer_visible) {
		super();
		this.id = id;
		this.question_title = question_title;
		this.question_formula = question_formula;
		this.questionType = questionType;
		this.questionCategory = questionCategory;
		this.tags = tags;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.date_created = date_created;
		this.date_modified = date_modified;
		this.is_active = is_active;
		this.is_answer_visible = is_answer_visible;
	}

	
	
	
	
}

package com.savkar.tagEngine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.savkar.tagEngine.entity.Discipline;
import com.savkar.tagEngine.entity.Subject;
import com.savkar.tagEngine.entity.Tag;

public interface TagRepository extends CrudRepository<Tag, Long>{
	
	Tag getTagByNameAndSubject(String name, Subject subject);
	
	@Query(value="select * from tag  where name like :tag_name ", nativeQuery=true)
	List<Tag> getTagByName(@Param("tag_name") String tag_name);
	
	@Query(value="select * from tag  where name like :tag_name and subject_id=:subject_id", nativeQuery=true)
	List<Tag> getTagByNameAndSubject(@Param("tag_name") String tag_name, @Param("subject_id") Long subjectId);
	
	List<Tag> getTagBySubjectId(Long id);
}

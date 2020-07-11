package com.damon.prodsched.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.damon.prodsched.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	//@Query(nativeQuery=true, value="SELECT project.machine_id FROM project INNER JOIN machine on id = project.machine_id")
	@Override
	public List<Project> findAll();
	
	public Project findByProjectId(long id);
	
	public Project findById(long id);

}

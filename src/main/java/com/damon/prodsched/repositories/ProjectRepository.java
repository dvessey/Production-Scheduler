package com.damon.prodsched.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.damon.prodsched.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	@Override
	public List<Project> findAll();
	
	public Project findByProjectId(long id);
	
	public Project findById(long id);

}

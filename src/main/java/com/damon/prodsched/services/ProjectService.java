package com.damon.prodsched.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.damon.prodsched.entities.Project;
import com.damon.prodsched.repositories.MachineRepository;
import com.damon.prodsched.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository proRepo;
	
	
	@Autowired
	MachineRepository machRepo;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	@Transactional
	public List<Project> getAll(){
		return proRepo.findAll();
	}
	
	public Project findByProjectId(long id) {
		return proRepo.findByProjectId(id);
	}
	
	public void delete(Project aProject) {
		proRepo.delete(aProject);
	}
	
	
	public Project findById(long id) {
		Optional<Project> projectResponse = Optional.ofNullable(proRepo.findById(id));
		Project project = projectResponse.get();
		return project;
		
	}
		
}

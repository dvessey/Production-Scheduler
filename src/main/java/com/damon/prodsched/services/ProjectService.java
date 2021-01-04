package com.damon.prodsched.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.damon.prodsched.entities.Machine;
import com.damon.prodsched.entities.Project;
import com.damon.prodsched.repositories.MachineRepository;
import com.damon.prodsched.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	MachineService machService;
	
	
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
	
	
	
	//startIndex is the item being dragged, index is the position where it was dropped
	public void compareDates(Long theOldMachineId, Long theMachineId, int startIndex, int index, LinkedList<Project> projects) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("==================================| In compareDates() |====================================================");
		
		//if the project is staying inside the same machine
		Machine oldMachine = machService.findById(theOldMachineId);
		Machine theMachine = machService.findById(theMachineId);
		if(theOldMachineId == theMachineId) {
		
			if (startIndex == 0) {
				Project currentProject = projects.get(0);
				Long currentHoursToComplete = currentProject.getHoursToComplete();
				currentProject.setStartDate(LocalDateTime.now());
				currentProject.setEndDate(currentProject.getStartDate().plusHours(currentHoursToComplete));
				save(currentProject);
				for (int i = startIndex + 1; i < projects.size(); i++) {
					Project nextProject = projects.get(i);	
					nextProject.setStartDate(projects.get(i-1).getEndDate());
					Long nextHoursToComplete = nextProject.getHoursToComplete();
					nextProject.setEndDate(nextProject.getStartDate().plusHours(nextHoursToComplete));
					save(nextProject);
				}
				
			}
			else if (startIndex < index) {
				//get the project right before the project that was dragged that was not altered still in the list
				Project prevProject = projects.get(startIndex - 1);
				Project currentProject = projects.get(startIndex);
				currentProject.setStartDate(prevProject.getEndDate());
				Long currentHoursToComplete = currentProject.getHoursToComplete();
				currentProject.setEndDate(currentProject.getStartDate().plusHours(currentHoursToComplete));
				save(currentProject);
				for (int i = startIndex; i < projects.size(); i++) {
					Project nextProject = projects.get(i);	
					nextProject.setStartDate(projects.get(i-1).getEndDate());
					Long nextHoursToComplete = nextProject.getHoursToComplete();
					nextProject.setEndDate(nextProject.getStartDate().plusHours(nextHoursToComplete));
					save(nextProject);
				}
			}
				
				
				if (index == 0) {
					Project currentProject = projects.get(index);
					Long currentHoursToComplete = currentProject.getHoursToComplete();
					currentProject.setStartDate(LocalDateTime.now());
					currentProject.setEndDate(currentProject.getStartDate().plusHours(currentHoursToComplete));
					save(currentProject);
					for(int i = index + 1; i < projects.size(); i++) {
						Project nextProject = projects.get(i);	
						nextProject.setStartDate(projects.get(i-1).getEndDate());
						Long nextHoursToComplete = nextProject.getHoursToComplete();
						nextProject.setEndDate(nextProject.getStartDate().plusHours(nextHoursToComplete));
						save(nextProject);
					}
				}
				else {
					Project currentProject = projects.get(index);
					Long currentHoursToComplete = currentProject.getHoursToComplete();
					Project prevProject = projects.get(index - 1);
					currentProject.setStartDate(prevProject.getEndDate());
					currentProject.setEndDate(currentProject.getStartDate().plusHours(currentHoursToComplete));
					save(currentProject);
					for (int i = index + 1; i < projects.size(); i++) {
						Project nextProject = projects.get(i);	
						nextProject.setStartDate(projects.get(i - 1).getEndDate());
						Long nextHoursToComplete = nextProject.getHoursToComplete();
						nextProject.setEndDate(nextProject.getStartDate().plusHours(nextHoursToComplete));
						save(nextProject);
					}
				 }
		}
		//project is moving to a different machine
		else {
			if (index == 0) {
				Project currentProject = projects.get(index);
				Long currentHoursToComplete = currentProject.getHoursToComplete();
				currentProject.setStartDate(LocalDateTime.now());
				currentProject.setEndDate(currentProject.getStartDate().plusHours(currentHoursToComplete));
				currentProject.setMachine(theMachine);
				save(currentProject);
				for(int i = index + 1; i < projects.size(); i++) {
					Project nextProject = projects.get(i);	
					nextProject.setStartDate(projects.get(i-1).getEndDate());
					Long nextHoursToComplete = nextProject.getHoursToComplete();
					nextProject.setEndDate(nextProject.getStartDate().plusHours(nextHoursToComplete));
					nextProject.setMachine(theMachine);
					save(nextProject);
				}
			}
			else {
				Project currentProject = projects.get(index);
				Long currentHoursToComplete = currentProject.getHoursToComplete();
				Project prevProject = projects.get(index - 1);
				currentProject.setStartDate(prevProject.getEndDate());
				currentProject.setEndDate(currentProject.getStartDate().plusHours(currentHoursToComplete));
				currentProject.setMachine(theMachine);
				save(currentProject);
				for (int i = index + 1; i < projects.size(); i++) {
					Project nextProject = projects.get(i);	
					nextProject.setStartDate(projects.get(i - 1).getEndDate());
					Long nextHoursToComplete = nextProject.getHoursToComplete();
					nextProject.setEndDate(nextProject.getStartDate().plusHours(nextHoursToComplete));
					nextProject.setMachine(theMachine);
					save(nextProject);
				}
			 }
			reorder(theOldMachineId);
		}
		
			
	}
	
	public void reorder(Long oldMachineId) {
		Machine oldMachine = machService.findById(oldMachineId);
		List<Project> projects = oldMachine.getProjects();
		if(projects.size() == 0) {
			return;
		}
		ArrayList<Project> theProjects = new ArrayList<Project>();
		Project currentProject = projects.get(0);
		Long currentHoursToComplete = currentProject.getHoursToComplete();
		currentProject.setStartDate(LocalDateTime.now());
		currentProject.setEndDate(currentProject.getStartDate().plusHours(currentHoursToComplete));
		save(currentProject);
		theProjects.add(currentProject);
		for (int i = 0 + 1; i < projects.size(); i++) {
			Project nextProject = projects.get(i);	
			nextProject.setStartDate(projects.get(i-1).getEndDate());
			Long nextHoursToComplete = nextProject.getHoursToComplete();
			nextProject.setEndDate(nextProject.getStartDate().plusHours(nextHoursToComplete));
			save(nextProject);
			theProjects.add(nextProject);
		}
		oldMachine.setProjects(theProjects);
		machService.save(oldMachine);
	}
		
}

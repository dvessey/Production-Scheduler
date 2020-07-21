package com.damon.prodsched.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.damon.prodsched.entities.Machine;
import com.damon.prodsched.entities.Project;
import com.damon.prodsched.services.MachineService;
import com.damon.prodsched.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	MachineService machService;
	
	
	@GetMapping("/addProject")
	public String createProject(Model model) {
		Project aProject = new Project();
		List<Machine> machines = machService.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("machines", machines);
		return "projects/new-projects";
	}
	

	@PostMapping("/saveProject")
	public String saveProject(Project project, @RequestParam("machine") List<Long> id,  Model model) {
		ArrayList<Project> projects = new ArrayList<Project>();
		projects.add(project);
		
		long theMachineId = id.get(0);
		Machine theMachine = machService.findById(theMachineId);
		
		project.setMachine(theMachine);
		proService.save(project);
		theMachine.setProjects(projects);
		machService.save(theMachine);
		return "redirect:/";
	}
	
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long id, Model model) {
		Project aProject = proService.findById(id);
		List<Machine> machines = machService.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("machines", machines);
		return "projects/new-projects";
	}
		
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long id, Model model) {
		Project aProject = proService.findById(id);
		proService.delete(aProject);
		return "redirect:/";
	}
		
		

}

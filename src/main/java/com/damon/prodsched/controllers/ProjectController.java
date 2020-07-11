package com.damon.prodsched.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@GetMapping("/addMachine")
	public String addMachine(@RequestParam("id") long id, Model model) {
		Project aProject = new Project();
		Machine aMachine = machService.findById(id);
		
		Machine theMachine = aMachine;		
		theMachine = machService.addProjectToMachine(aMachine, aProject);
		System.out.println("aProject machine's ID: " + aProject.getMachine().getId()); //THIS RETURNS THE CORRECT ID

		model.addAttribute("machine", theMachine);
		model.addAttribute("project", aProject);
		return "projects/new-projects";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		proService.save(project);		
		return "redirect:/";
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long id, Model model) {
		//Project aProject = proService.findByProjectId(id);
		Project aProject = proService.findById(id);
		model.addAttribute("project", aProject);
		return "projects/new-projects";
	}
		
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long id, Model model) {
		//Project aProject = proService.findByProjectId(id);
		Project aProject = proService.findById(id);
		proService.delete(aProject);
		return "redirect:/";
	}
		
		

}

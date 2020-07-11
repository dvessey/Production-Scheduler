package com.damon.prodsched.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.damon.prodsched.entities.Machine;
import com.damon.prodsched.entities.Project;
import com.damon.prodsched.repositories.MachineRepository;
import com.damon.prodsched.repositories.ProjectRepository;

@Controller
public class HomeController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	MachineRepository machRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) {
		List<Project> projects = proRepo.findAll();
		List<Machine> machines= machRepo.findAll();
		model.addAttribute("projects", projects);
		model.addAttribute("machines", machines);
		return "projects/projects";
	}
}

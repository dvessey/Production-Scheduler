package com.damon.prodsched.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.damon.prodsched.entities.Machine;
import com.damon.prodsched.entities.Project;
import com.damon.prodsched.repositories.MachineRepository;
import com.damon.prodsched.repositories.ProjectRepository;
import com.damon.prodsched.services.MachineService;
import com.damon.prodsched.services.ProjectService;

@Controller
public class HomeController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	MachineRepository machRepo;
	
	@Autowired
	MachineService machService;
	
	@Autowired
	ProjectService proService;
	
	@GetMapping("/")
	public String displayHome(Model model) {
		List<Project> projects = proRepo.findAll();
		List<Machine> machines= machRepo.findAllByOrderByIdAsc();
		model.addAttribute("projects", projects);
		model.addAttribute("machines", machines);
		return "projects/projects";
	}
	
	
}

package com.damon.prodsched.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.damon.prodsched.entities.Machine;
import com.damon.prodsched.services.MachineService;

@Controller
@RequestMapping("/machines")
public class MachineController {
	
	@Autowired
	MachineService machService;
	
	
	@GetMapping
	public String displayMachines(Model model) {
		//Iterable<Machine> machines = machService.getAll();
		List<Machine> machines = machService.findAll();
		model.addAttribute("machines", machines);
		return "machines/list-machines";
	}
	
	@GetMapping("/new")
	public String displayMachineForm(Model model) {
		Machine aMachine = new Machine();
		model.addAttribute("machine", aMachine);
		return "machines/new-machine";
	}
	
	@PostMapping("/save")
	public String createMachine(Machine machine, Model model) {
		machService.save(machine);
		return "redirect:/machines";
	}
	
	@GetMapping("/update")
	public String displayMachineUpdateForm(@RequestParam("id") long id, Model model) {
		//Machine aMachine = machService.findByMachineId(id);
		Machine aMachine = machService.findById(id);
		model.addAttribute("machine", aMachine);
		return "machines/new-machine";
	}

	@GetMapping("/delete")
	public String deleteMachine(@RequestParam("id") long id, Model model) {
		//Machine aMachine = machService.findByMachineId(id);
		Machine aMachine = machService.findById(id);
		machService.delete(aMachine);
		return "redirect:/machines";
	}
}

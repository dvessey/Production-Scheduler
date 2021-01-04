package com.damon.prodsched.controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.damon.prodsched.dto.ProjectWrapperDto;
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
	
	//@Autowired
	//ProjectWrapperDto proWrapper;
	
	
	@GetMapping("/addProject")
	public String createProject(Model model) {
		Project aProject = new Project();
		List<Machine> machines = machService.findAllByOrderByIdAsc();
		model.addAttribute("project", aProject);
		model.addAttribute("machines", machines);
		return "projects/new-projects";
	}
	

	@PostMapping("/saveProject")
	public String saveProject(Project project, @RequestParam("machine") List<Long> id, Model model) {
		ArrayList<Project> projects = new ArrayList<Project>();
		projects.add(project);
		
		long theMachineId = id.get(0);
		Machine theMachine = machService.findById(theMachineId);
		
		LocalDateTime theStartDate = project.getStartDate();
		long hoursToComplete = project.getHoursToComplete();
		LocalDateTime endDate = theStartDate.plusHours(hoursToComplete);
		
		project.setEndDate(endDate);
		project.setMachine(theMachine);
		proService.save(project);
				
		theMachine.setProjects(projects);
		machService.save(theMachine);
		return "redirect:/";
	}
	
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long id, Model model) {
		Project aProject = proService.findById(id);
		List<Machine> machines = machService.findAllByOrderByIdAsc();
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
	
	@PostMapping("/saveOrder")
	public String saveViaAjax(@RequestBody ProjectWrapperDto wrapper) throws ParseException{
		String machineId = wrapper.getMachineStringId();
		Long theMachineId = Long.parseLong(machineId);
		Machine machine = machService.findById(theMachineId);
		
		String startPosition = wrapper.getStartPosition();
		int startIndex = Integer.parseInt(startPosition);
		
		System.out.println("startIndex: " + startIndex);
		
		String droppedIndex = wrapper.getDroppedIndex();
		int index = Integer.parseInt(droppedIndex);
		
		String oldMachineId = wrapper.getOldMachineId();
		Long theOldMachineId = Long.parseLong(oldMachineId);
		Machine oldMachine = machService.findById(theOldMachineId);
		
		System.out.println("DroppedIndex: " + droppedIndex);
		System.out.println("OldMachineId: " + theOldMachineId);
		System.out.println("theMachineId: " + theMachineId);
		
		LinkedList<Project> projectsLL = new LinkedList<Project>();
		
		
		List<String> projectIds = wrapper.getProjectIds();
		for(String id : projectIds) {
			Long theProjectId = Long.parseLong(id);
			Project project = proService.findById(theProjectId);
			projectsLL.add(project);
			project.setMachine(machine);
			//proService.save(project);
		}
	
		
		proService.compareDates(theOldMachineId, theMachineId, startIndex, index, projectsLL);
		
		
		ArrayList<Project> projects = new ArrayList<Project>(projectsLL); //convert linked list back to an array list
		
		
		machine.setProjects(projects); // need to save LinkedList to an array list
		machService.save(machine);
		
//		List<Project> oldProjects = oldMachine.getProjects();
//		ArrayList<Project> theOldProjects = new ArrayList<Project>(oldProjects);
//		oldMachine.setProjects(theOldProjects);
//		machService.save(oldMachine);
		
		return "redirect:/";
	}
	
	
}

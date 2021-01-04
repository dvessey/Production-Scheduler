package com.damon.prodsched.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.damon.prodsched.entities.Machine;
import com.damon.prodsched.repositories.MachineRepository;
import com.damon.prodsched.repositories.ProjectRepository;

@Service
public class MachineService{
	
	@Autowired
	MachineRepository machRepo;
	
	@Autowired
	ProjectRepository proRepo;
	
	public Machine save(Machine machine) {
		return machRepo.save(machine);
	}
	
	public Iterable<Machine> getAll(){
		return machRepo.findAll();
	}

	public List<Machine> findAllByOrderByIdAsc(){
		return machRepo.findAllByOrderByIdAsc();
	}
	
	public Iterable<Machine> findAllById(long id){
		return machRepo.findAllById(id);
	}
	
	
	public void delete(Machine aMachine) {
		machRepo.delete(aMachine);
	}
	
	
	public Machine findById(long id) {
		Optional<Machine> machineResponse = Optional.ofNullable(machRepo.findById(id));
		Machine machine = machineResponse.get();
		return machine;
	}
	

}

package com.damon.prodsched.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.damon.prodsched.entities.Machine;

public interface MachineRepository extends CrudRepository<Machine, Long>{
	
	//public Machine findByMachineId(long id);
	
	public Machine findById(long id);
	
	@Override
	public List<Machine> findAll();
}

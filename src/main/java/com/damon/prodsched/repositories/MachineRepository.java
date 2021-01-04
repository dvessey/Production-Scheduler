package com.damon.prodsched.repositories;

import java.util.List;

import javax.persistence.OrderColumn;

import org.springframework.data.repository.CrudRepository;
import com.damon.prodsched.entities.Machine;

public interface MachineRepository extends CrudRepository<Machine, Long>{
		
	public Machine findById(long id);
	
	//@Override
	public List<Machine> findAllByOrderByIdAsc();
	
	public Iterable<Machine> findAllById(long id);
}

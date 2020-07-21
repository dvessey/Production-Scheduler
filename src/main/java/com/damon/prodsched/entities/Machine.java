package com.damon.prodsched.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="machine")
public class Machine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="machine_seq")
	private long id;
	private String name;
	
	@OneToMany(mappedBy="machine", cascade = CascadeType.ALL)
	private List<Project> projectList;
	
	public Machine() {
		projectList = new ArrayList<Project>();
		
	}

	public Machine(String name) {
		super();
		this.name = name;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProjects() {
		return projectList;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projectList = projects;
	}
		

}

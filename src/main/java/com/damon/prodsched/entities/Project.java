package com.damon.prodsched.entities;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Query;


@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="project_seq")
	private long projectId;
	@Column(name="prioritynum")
	private int priorityNum;
	private String name;
	@Column(name="operationnum")
	private String operationNum;
	private String description;
	

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name="machine_id", referencedColumnName="id")
	private Machine machine;

	

	public Project() {
		
	}


	public Project (int priorityNum, String name, String operationNum, String description) {
		super();
		this.priorityNum = priorityNum;
		this.name = name;
		this.operationNum = operationNum;
		this.description = description;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPriorityNum() {
		return priorityNum;
	}

	public void setPriorityNum(int priorityNum) {
		this.priorityNum = priorityNum;
	}

	public String getOperationNum() {
		return operationNum;
	}

	public void setOperationNum(String operationNum) {
		this.operationNum = operationNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}


	public Machine getMachine() {
		return machine;
	}
	
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	
}

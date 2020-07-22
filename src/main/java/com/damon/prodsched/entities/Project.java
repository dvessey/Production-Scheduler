package com.damon.prodsched.entities;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="project_seq")
	private long projectId;
	private String name;
	@Column(name="operationnum")
	private String operationNum;
	private String description;
	@Column(name="startdate")
	@DateTimeFormat(pattern="MM-dd-yyyy HH:mm")
	private LocalDateTime startDate;
	@Column(name="hourstocomplete")
	private long hoursToComplete;
	@Column(name="enddate")
	@DateTimeFormat(pattern="MM-dd-yyyy HH:mm")
	private LocalDateTime endDate;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch=FetchType.LAZY)
	@JoinColumn(name="machine_id", referencedColumnName="id", nullable = false)
	private Machine machine;

	

	public Project() {
		
	}


	public Project (int priorityNum, String name, String operationNum, String description, LocalDateTime startDate, long hoursToComplete, LocalDateTime endDate) {
		super();
		this.priorityNum = priorityNum;
		this.name = name;
		this.operationNum = operationNum;
		this.description = description;
		this.startDate = startDate;
		this.hoursToComplete = hoursToComplete;
		this.endDate = endDate;
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
	
	public LocalDateTime getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}


	public long getHoursToComplete() {
		return hoursToComplete;
	}


	public void setHoursToComplete(long hoursToComplete) {
		this.hoursToComplete = hoursToComplete;
	}
	
	public LocalDateTime getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}


	public Machine getMachine() {
		return machine;
	}
	
	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	
	
}

package com.example.demo.entity;

public enum JobEnum {

Director("Director", 2200),
Camarero("Camarero", 1500),
Runner("Runner", 1300),
Cocinero("Cocinero", 1800);

private String jobName;
private double salary;	

JobEnum (String jobName, double salary) {
	
this.jobName = jobName;
this.salary = salary;
	
}

public String getJobName() {
	return jobName;
}

public double getSalary() {
	return salary;
}

public void setJobName(String jobName) {
	this.jobName = jobName;
}

public void setSalary(double salary) {
	this.salary = salary;
}



	
}
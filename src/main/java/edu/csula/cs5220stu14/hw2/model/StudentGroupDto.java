package edu.csula.cs5220stu14.hw2.model;

public class StudentGroupDto {
	
	private Integer studentGroupId;
	private String name;
	
	public StudentGroupDto() {
	}
	
	public StudentGroupDto(StudentGroup g) {
		studentGroupId = g.getStudentGroupId();
		name = g.getName();
		
		/*
		 * Don't include members here since 
		 * we don't want to see that when displaying all groups.
		 */
	}

	public Integer getStudentGroupId() {
		return studentGroupId;
	}

	public void setStudentGroupId(Integer studentGroupId) {
		this.studentGroupId = studentGroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

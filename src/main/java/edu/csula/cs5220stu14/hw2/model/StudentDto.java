package edu.csula.cs5220stu14.hw2.model;

public class StudentDto {
	
	private Integer studentId;
	private String name;
	private String parent;
	private String parentEmail;
	private Integer birthYr;
	
	private Integer studentGroupId;
	private String studentGroupName;
	
	private boolean inactive;
	
	public StudentDto() {	
	}
	
	public StudentDto(Student s) {
		studentId = s.getStudentId();
		name = s.getName();
		parent = s.getParent();
		parentEmail = s.getParentEmail();
		birthYr = s.getBirthYr();
		
		// initialize
		studentGroupId = null;
		studentGroupName = null; 
		/*
		 * JSON output does not need the whole group info with student list,
		 * because that's infinite recursion.
		 * Just print the id and name of group.
		 */
		StudentGroup tempStudentGroup = s.getStudentGroup();
		if (tempStudentGroup != null) {
			studentGroupId = tempStudentGroup.getStudentGroupId();
			studentGroupName = tempStudentGroup.getName();
		}
		
		inactive = s.isInactive();
	}
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public Integer getBirthYr() {
		return birthYr;
	}

	public void setBirthYr(Integer birthYr) {
		this.birthYr = birthYr;
	}

	public Integer getStudentGroupId() {
		return studentGroupId;
	}

	public void setStudentGroupId(Integer studentGroupId) {
		this.studentGroupId = studentGroupId;
	}

	public String getStudentGroupName() {
		return studentGroupName;
	}

	public void setStudentGroupName(String studentGroupName) {
		this.studentGroupName = studentGroupName;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
}

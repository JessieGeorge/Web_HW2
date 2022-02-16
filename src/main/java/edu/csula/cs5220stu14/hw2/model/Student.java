package edu.csula.cs5220stu14.hw2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "student_id")
	private Integer studentId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String parent;
	
	@Column(name = "parent_email", nullable = false)
	private String parentEmail;
	
	@Column(name = "birth_year", nullable = false)
	private Integer birthYr;
	
	@ManyToOne
	@JoinColumn(name = "student_group_id")
	private StudentGroup studentGroup;
	
	private boolean inactive = Boolean.FALSE;
	
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
	
	public StudentGroup getStudentGroup() {
		return studentGroup;
	}
	public void setStudentGroup(StudentGroup studentGroup) {
		this.studentGroup = studentGroup;
	}
	
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
}


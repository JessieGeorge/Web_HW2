package edu.csula.cs5220stu14.hw2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student_groups")
public class StudentGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "student_group_id")
	private Integer studentGroupId;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@OneToMany(mappedBy = "studentGroup")
	private List<Student> students = new ArrayList<Student>();
	
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
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
}

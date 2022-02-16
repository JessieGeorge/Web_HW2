package edu.csula.cs5220stu14.hw2.model.dao;

import java.util.List;

import edu.csula.cs5220stu14.hw2.model.Student;
import edu.csula.cs5220stu14.hw2.model.StudentGroup;

public interface ArtSchoolDao {
	
	List<Student> getStudents();
	
	List<StudentGroup> getStudentGroups();
	
	Student getStudent(Integer studentId);
	
	StudentGroup getStudentGroup(Integer studentGroupId);
	
	Student saveStudent(Student student);
	
	StudentGroup saveStudentGroup(StudentGroup studentGroup);
}

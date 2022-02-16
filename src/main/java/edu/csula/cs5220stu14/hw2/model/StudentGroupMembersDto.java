package edu.csula.cs5220stu14.hw2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentGroupMembersDto extends StudentGroupDto {
	
	private List<Map.Entry<Integer, String>> members;
	
	public StudentGroupMembersDto() {
		super();
	}
	
	public StudentGroupMembersDto(StudentGroup g) {
		super(g);
		
		// initialize
		members = new ArrayList<Map.Entry<Integer, String>>();
		
		/*
		 * JSON output does not need the whole student list info,
		 * because that's infinite recursion.
		 * Just print the id and name of each student.
		 */
		List<Student> tempStudents = g.getStudents(); 
		for (Student s : tempStudents) {
			if (!s.isInactive()) {
				// Only display active students
				Map.Entry<Integer, String> m = 
						Map.entry(s.getStudentId(), s.getName());
				members.add(m);
			}
		}
	}

	public List<Map.Entry<Integer, String>> getMembers() {
		return members;
	}

	public void setMembers(List<Map.Entry<Integer, String>> members) {
		this.members = members;
	}
}

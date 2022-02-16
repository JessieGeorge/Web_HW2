package edu.csula.cs5220stu14.hw2.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.csula.cs5220stu14.hw2.model.StudentGroup;
import edu.csula.cs5220stu14.hw2.model.StudentGroupDto;
import edu.csula.cs5220stu14.hw2.model.StudentGroupMembersDto;
import edu.csula.cs5220stu14.hw2.model.dao.ArtSchoolDao;

@RestController
@RequestMapping("/student-groups")
public class StudentGroupsController {
	
	@Autowired
	private ArtSchoolDao artSchoolDao;
	
	@GetMapping
	public List<StudentGroupDto> getStudentGroups(ModelMap models) {
		List<StudentGroup> studentGroups = artSchoolDao.getStudentGroups();
		List<StudentGroupDto> studentGroupDtos = new 
				ArrayList<StudentGroupDto>();
		for (StudentGroup g : studentGroups) {
			studentGroupDtos.add(new StudentGroupDto(g));
		}
		return studentGroupDtos;
	}
	
	@GetMapping("/{studentGroupId}")
	public StudentGroupMembersDto getStudentGroup(@PathVariable 
			Integer studentGroupId) {
		StudentGroup studentGroup = artSchoolDao
				.getStudentGroup(studentGroupId);
		if (studentGroup == null)
		      throw new ResponseStatusException(
		    		  HttpStatus.NOT_FOUND, "Student Group not found");
		
		// returns members of the group along with regular group info
		StudentGroupMembersDto studentGroupMembersDto = 
				new StudentGroupMembersDto(studentGroup);
		return studentGroupMembersDto;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StudentGroupDto addStudentGroup(@RequestBody 
			StudentGroupDto dto) {
		StudentGroup g = new StudentGroup();
		g.setStudentGroupId(dto.getStudentGroupId());
		g.setName(dto.getName());
		
		g = artSchoolDao.saveStudentGroup(g);
		return new StudentGroupDto(g);
	}
	
	@PatchMapping("/{studentGroupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStudentGroup(@PathVariable 
			Integer studentGroupId,
			@RequestBody Map<String, Object> patch) {
		StudentGroup studentGroup = artSchoolDao.getStudentGroup(
				studentGroupId);
		if (studentGroup == null)
		      throw new ResponseStatusException(
		    		  HttpStatus.NOT_FOUND, "Student Group not found");
		
		/*
		 * Not checking studentGroupId, since the user should not change PK.
		 * 
		 * Not checking members, since 
		 * the user should not change members here,
		 * they have to update the enrollment for a single student at a time.
		 */
		
		for (String key : patch.keySet()) {
			switch(key) {
				case "name":
					studentGroup.setName((String)patch.get(key));
					break;
				default:
			}
		}
		
		studentGroup = artSchoolDao.saveStudentGroup(studentGroup);
	}
}

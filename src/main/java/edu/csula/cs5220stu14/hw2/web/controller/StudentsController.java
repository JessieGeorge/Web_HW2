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

import edu.csula.cs5220stu14.hw2.model.Student;
import edu.csula.cs5220stu14.hw2.model.StudentDto;
import edu.csula.cs5220stu14.hw2.model.StudentGroup;
import edu.csula.cs5220stu14.hw2.model.dao.ArtSchoolDao;

@RestController
@RequestMapping("/students")
public class StudentsController {
	
	@Autowired
	private ArtSchoolDao artSchoolDao;
	
	@GetMapping
	public List<StudentDto> getStudents(ModelMap models) {
		List<Student> students = artSchoolDao.getStudents();
		List<StudentDto> studentDtos = new ArrayList<StudentDto>();
		for(Student s : students) {
			studentDtos.add(new StudentDto(s));
		}
		return studentDtos;
	}
	
	@GetMapping("/{studentId}")
	public StudentDto getStudent(@PathVariable Integer studentId) {
		Student student = artSchoolDao.getStudent(studentId);
		if (student == null)
		      throw new ResponseStatusException(
		    		  HttpStatus.NOT_FOUND, "Student not found");
		
		StudentDto studentDto = new StudentDto(student);
		return studentDto;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StudentDto addStudent(@RequestBody StudentDto dto) {
		Student s = new Student();
		s.setStudentId(dto.getStudentId());
		s.setName(dto.getName());
		s.setParent(dto.getParent());
		s.setParentEmail(dto.getParentEmail());
		s.setBirthYr(dto.getBirthYr());
		s.setInactive(dto.isInactive());
		
		Integer dtoId = dto.getStudentGroupId();
		if(dtoId != null) {
			StudentGroup g = artSchoolDao.getStudentGroup(dtoId);
			s.setStudentGroup(g);
			/*
			 *  the group's list of students is 
			 *  automatically taken care of
			 *  with the mappedBy annotations 
			 *  in the StudentGroup.java class.
			 */
		}
		
		/*
		 * Not checking studentGroupName,
		 * because I'm assuming the user enters 
		 * studentGroupId if they want to enroll in a group.
		 * Also assuming good input.
		 */
		
		s = artSchoolDao.saveStudent(s);
		return new StudentDto(s);
	}
	
	@PatchMapping("/{studentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStudent(@PathVariable Integer studentId,
			@RequestBody Map<String, Object> patch) {
		Student student = artSchoolDao.getStudent(studentId);
		if (student == null)
		      throw new ResponseStatusException(
		    		  HttpStatus.NOT_FOUND, "Student not found");
		
		/*
		 * Not checking studentGroupName,
		 * because I'm assuming the user enters 
		 * studentGroupId if they want to 
		 * update enrollment to a different group.
		 * Also assuming good input.
		 * 
		 * Not checking studentId, since the user should not change PK.
		 */
		
		for (String key : patch.keySet()) {
			switch(key) {
				case "name":
					student.setName((String)patch.get(key));
					break;
				case "parent":
					student.setParent((String)patch.get(key));
					break;
				case "parentEmail":
					student.setParentEmail((String)patch.get(key));
					break;
				case "birthYr":
					student.setBirthYr((Integer)patch.get(key));
					break;
				case "inactive":
					/*
					 * According to Prof:
					 * Displaying group members 
					 * should not have inactive students.
					 * But the inactive student may still have a group id.
					 * 
					 * So:
					 * Remove inactive student from the StudentGroupMembersDto.
					 * But don't set the group of the inactive student to null.
					 */
					student.setInactive((boolean)patch.get(key));
					break;
				case "studentGroupId":
					Object gid = patch.get(key);
					if (gid == null) {
						// unenroll
						student.setStudentGroup(null);
					} else {
						// enroll in different group
						StudentGroup studentGroup = artSchoolDao
								.getStudentGroup((Integer) gid);
						student.setStudentGroup(studentGroup);
					}
					/*
					 *  the group's list of students is 
					 *  automatically taken care of
					 *  with the mappedBy annotations 
					 *  in the StudentGroup.java class.
					 */
					break;
				default:
			}
		}
		
		student = artSchoolDao.saveStudent(student);
	}
}

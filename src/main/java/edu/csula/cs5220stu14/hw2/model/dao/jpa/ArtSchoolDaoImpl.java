package edu.csula.cs5220stu14.hw2.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import edu.csula.cs5220stu14.hw2.model.Student;
import edu.csula.cs5220stu14.hw2.model.StudentGroup;
import edu.csula.cs5220stu14.hw2.model.dao.ArtSchoolDao;

@Repository
public class ArtSchoolDaoImpl implements ArtSchoolDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Student> getStudents() {
		return entityManager
				.createQuery("FROM Student", Student.class)
				.getResultList();
		
		/*
		 * This includes inactive students as per Canvas requirements.
		 */
	}

	@Override
	public List<StudentGroup> getStudentGroups() {
		return entityManager
				.createQuery("FROM StudentGroup", StudentGroup.class)
				.getResultList();
	}

	@Override
	public Student getStudent(Integer studentId) {
		return entityManager.find(Student.class, studentId);
	}
	
	@Override
	public StudentGroup getStudentGroup(Integer studentGroupId) {
		return entityManager.find(StudentGroup.class, studentGroupId);
	}
	
	@Override
	@Transactional
	public Student saveStudent(Student student) {
		return entityManager.merge(student);
	}

	@Override
	@Transactional
	public StudentGroup saveStudentGroup(StudentGroup studentGroup) {
		return entityManager.merge(studentGroup);
	}

}

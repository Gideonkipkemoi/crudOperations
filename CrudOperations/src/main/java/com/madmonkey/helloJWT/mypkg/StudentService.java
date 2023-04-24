package com.madmonkey.helloJWT.mypkg;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	

	public void createStudent(Student student) {
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		studentRepository.save(student);
	}
	public Student updateStudent(Student student) {
		studentRepository
		.findById(student.getId())
		.ifPresent(stud->{
			stud.setEmail(student.getEmail());
			stud.setName(student.getName());
			//stud.setPassword(student.getPassword());
			studentRepository.save(stud);
		});
		return student;
	}
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	public Student getStudent(int id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student stud = null;
		if(optional.isPresent()) {
			stud = optional.get();
		}else {
			throw new RuntimeException(id+" not found in the DB");
		}return stud;
		
	}
}

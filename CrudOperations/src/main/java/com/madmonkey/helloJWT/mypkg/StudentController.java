package com.madmonkey.helloJWT.mypkg;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private StudentService studentService;
	

	@PostMapping("/save")
	public void createStudent(@RequestBody Student student){
		studentService.createStudent(student);
	}
	@PutMapping("/update")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student){
		return new ResponseEntity<Student>(studentService.updateStudent(student), HttpStatus.ACCEPTED);
	}
	@GetMapping("/")
	public ResponseEntity<List<StudentDTO>> getAllStudents(){
		return new ResponseEntity<List<StudentDTO>>(studentService.getAllStudents().stream().map(student->modelMapper.map(student, StudentDTO.class)).collect(Collectors.toList()),HttpStatus.OK);
	}
	@GetMapping("/student/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id){
		Student st = studentService.getStudent(id);
		StudentDTO responseStudent = modelMapper.map(st, StudentDTO.class);
		return ResponseEntity.ok().body(responseStudent);
	}

}

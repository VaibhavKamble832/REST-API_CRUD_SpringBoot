package com.CRUD_API_App.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD_API_App.model.Student;
import com.CRUD_API_App.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/Student")
	public String addStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return "Student added Successfully...";
	}
	
	@GetMapping("/Student")
	public ResponseEntity<List<Student>> getAllStudents(){
		
		List<Student> studentlist = new ArrayList<>();
		studentRepository.findAll().forEach(studentlist::add);
		return new ResponseEntity<List<Student>>(studentlist, HttpStatus.OK); 
	}
	
	@GetMapping("/Student/{student_id}")
	public ResponseEntity<Student> getStudentById(@PathVariable long student_id){
		
		Optional<Student> stud = studentRepository.findById(student_id);
		if(stud.isPresent()) {
			return new ResponseEntity<Student>(stud.get(), HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/Student/{student_id}")
	public String updateStudent(@PathVariable long student_id, @RequestBody Student student) {
		
		Optional<Student> stud = studentRepository.findById(student_id);
		if(stud.isPresent()) {
			Student studRecord = stud.get();
			studRecord.setStudent_name(student.getStudent_name());
			studRecord.setStudent_contact(student.getStudent_contact());
			studRecord.setStudent_email(student.getStudent_email());
			studRecord.setCourse(student.getCourse());
			
			studentRepository.save(studRecord);
			return "Record is Updated..";
		}
		else {
			return "No record found of id "+student_id;
		}
		
	}
	
	@DeleteMapping("/Student/{student_id}")
	public String deleteStudent(@PathVariable long student_id) {
		studentRepository.deleteById(student_id);
		return "Record is Deleted...";
	}
	
	
	
	
	
}

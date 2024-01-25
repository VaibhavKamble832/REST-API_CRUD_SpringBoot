package com.CRUD_API_App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUD_API_App.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}

package com.example.interview.repository;

import com.example.interview.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository {
    Student find(long id);

    Student save(Student student);
}

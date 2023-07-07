package com.example.interview.repository;

import com.example.interview.model.Student;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryStudentRepository implements StudentRepository {
    private ConcurrentHashMap<Long, Student> rep = new ConcurrentHashMap<>();

    @Override
    public Student find(long id) {
        return rep.get(id);
    }

    @Override
    public Student save(Student student) {
        return rep.put(student.getId(), student);
    }
}

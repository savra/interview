package com.example.interview.model;

import com.example.interview.repository.StudentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class Student {
    @Autowired
    public ApplicationContext context;

    @Autowired
    public StudentRepository repository;

    private long id;
    private String name;
    boolean sex;
    private String inn;


    public Student createNewStudent() {
        return context.getBean(Student.class);
    }

    public void doSimpleJob(Student student) {
        boolean b = checkInn(student.getInn());
        if (b) {
            repository.save(student);
        }
    }

    public List<Student> doHardJob(List<Student> students) {
        students.stream()
                .filter(student -> student != null)
                .forEach(s -> {
                    boolean b = checkInn(s.getInn());
                    if (b) {
                        students.remove(s);
                        Student saved = repository.save(s);
                        students.add(saved);
                    }
                });
        return students;
    }

    boolean checkInn(String inn) {
        if (inn.equals(null)) {
            return false;
        }
        if (inn.length() < 8) {
            return false;
        }
        return true;
    }
}

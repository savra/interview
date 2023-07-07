package com.example.interview;

import com.example.interview.model.Student;
import com.example.interview.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class InterviewApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private StudentRepository repository;

    private Student getNewStudent() {
        return context.getBean(Student.class);
    }

    @Test
    void testSaveStudents() {
        // создаем нового студента
        Student newStudent = getNewStudent();
        // проставляем id и остальные поля
        newStudent.setId(1);
        newStudent.setName("Вася");
        newStudent.setSex(true);
        newStudent.setInn("111222333444");

        repository.save(newStudent);

        // теперь достаем
        Student student = repository.find(1);

        System.out.println("\nПроверяем первого студента:");

        // доблжно быть 1
        System.out.println(student.getId());
        // доблжно быть Вася
        System.out.println(student.getName());
        // доблжно быть 111222333444
        System.out.println(student.getInn());


        Student oneMoreStudent = getNewStudent();

        oneMoreStudent.setId(2);
        oneMoreStudent.setName("Петя");
        oneMoreStudent.setInn("555666777888");
        oneMoreStudent.setSex(false);

        System.out.println("\nПроверяем второго студента (сохранен с помошью doSimpleJob!!):");
        oneMoreStudent.doSimpleJob(oneMoreStudent);

        Student oneMoreStudentNew = repository.find(2);
        // доблжно быть 2
        System.out.println(oneMoreStudentNew.getId());
        // доблжно быть Петя
        System.out.println(oneMoreStudentNew.getName());
        // доблжно быть 555666777888
        System.out.println(oneMoreStudentNew.getInn());

        System.out.println("\nСнова проверяем первого студента, тест почему-то не работает правильно, но я его поправлю в следующем PR");

        // доблжно быть 1
        System.out.println(student.getId());
        // доблжно быть Вася
        System.out.println(student.getName());
    }

    @Test
    public void testRepository() {
        Student newStudent = getNewStudent();
        newStudent.setId(1);
        newStudent.setName("Вася");

        Student savedStudent = repository.save(newStudent);

        System.out.println("\n Студет успешно сохранен: " + savedStudent);

    }
}

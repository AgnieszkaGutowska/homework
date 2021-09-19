package com.example.demo;

import com.example.demo.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class Start {

    @Autowired
    private EntityManager entityManager;

    @EventListener(ApplicationReadyEvent.class)
    public void runExample(){

        Teacher teacher = new Teacher();
        Teacher teacher2 = new Teacher();
        List<Teacher> teachers= new ArrayList<>();

        teacher.setFirstName("Lukasz");
        teacher.setLastName("Chrzanowski");
        teacher.setEmail("a.gutowska1@gmail.com");
        teacher.setNumber("111222333");

        teacher2.setFirstName("Tomasz");
        teacher2.setLastName("Lissowski");
        teacher2.setEmail("a.gutowska1@gmail.com");
        teacher2.setNumber("444555666");

        entityManager.persist(teacher);
        entityManager.persist(teacher2);


        TypedQuery<Teacher> query = entityManager.createQuery("select e from Teacher e where e.firstName = :name", Teacher.class);
        query.setParameter("name", "Lukasz");
        for (Teacher teacher1 : query.getResultList()) {
            System.out.println(teacher1.getFirstName());
            System.out.println(teacher1.getLastName());
            System.out.println(teacher1.getEmail());
            System.out.println(teacher1.getNumber());
        }
        TypedQuery<Teacher> query2 = entityManager.createQuery("select e from Teacher e where e.firstName = :name", Teacher.class);
        query2.setParameter("name", "Tomasz");
        for (Teacher teacher22 : query2.getResultList()) {
            System.out.println(teacher22.getFirstName());
            System.out.println(teacher22.getLastName());
            System.out.println(teacher22.getEmail());
            System.out.println(teacher22.getNumber());
        }
    }

}

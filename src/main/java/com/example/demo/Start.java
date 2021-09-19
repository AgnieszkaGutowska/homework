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
        Teacher teacher3 = new Teacher();
        Teacher teacher4 = new Teacher();
        List<Teacher> teachers= new ArrayList<>();

        teacher.setFirstName("Lukasz");
        teacher.setLastName("Chrzanowski");
        teacher.setEmail("test1@gmail.com");
        teacher.setNumber("111222333");

        teacher2.setFirstName("Tomasz");
        teacher2.setLastName("Lissowski");
        teacher2.setEmail("test2@gmail.com");
        teacher2.setNumber("444555666");

        teacher3.setFirstName("Maciej");
        teacher3.setLastName("Koziara");
        teacher3.setEmail("test3@gmail.com");
        teacher3.setNumber("444555666");

        teacher4.setFirstName("Mateusz");
        teacher4.setLastName("Szymula");
        teacher4.setEmail("test4@gmail.com");
        teacher4.setNumber("444555666");

        entityManager.persist(teacher);
        entityManager.persist(teacher2);
        entityManager.persist(teacher3);
        entityManager.persist(teacher4);


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
        TypedQuery<Teacher> query3 = entityManager.createQuery("select e from Teacher e where e.firstName = :name", Teacher.class);
        query3.setParameter("name", "Maciej");
        for (Teacher teacher33 : query3.getResultList()) {
            System.out.println(teacher33.getFirstName());
            System.out.println(teacher33.getLastName());
            System.out.println(teacher33.getEmail());
            System.out.println(teacher33.getNumber());
        }
        TypedQuery<Teacher> query4 = entityManager.createQuery("select e from Teacher e where e.firstName = :name", Teacher.class);
        query4.setParameter("name", "Mateusz");
        for (Teacher teacher44 : query4.getResultList()) {
            System.out.println(teacher44.getFirstName());
            System.out.println(teacher44.getLastName());
            System.out.println(teacher44.getEmail());
            System.out.println(teacher44.getNumber());
        }
    }

}

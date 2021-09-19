package com.example.demo.repository;

import com.example.demo.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional
public class TeachersRepository {


    @Autowired
    private EntityManager entityManager;

    public List<Teacher> findAll() {
        final Query query = entityManager.createQuery("SELECT e FROM Teacher e", Teacher.class);
        return query.getResultList();
    }

    public List<Teacher> find(Long id) {
        final Query query = entityManager.createQuery("SELECT e FROM Teacher e WHERE e.id=" +id , Teacher.class);
        return query.getResultList();
    }

    public void save(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public void delete(Teacher teacher) {
        entityManager.remove(teacher);
    }

    public void update(Teacher teacher){
        entityManager.merge(teacher);

    }

}

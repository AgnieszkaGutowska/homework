package com.example.demo.repository;

import com.example.demo.entities.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeachersRepositoryTest {

    @Test
    void findAll() {


        TeachersRepository teachersRepository= new TeachersRepository();

        teachersRepository.findAll();
        assertEquals("Lukasz", teachersRepository.findAll().get(0).getFirstName());
    }
}
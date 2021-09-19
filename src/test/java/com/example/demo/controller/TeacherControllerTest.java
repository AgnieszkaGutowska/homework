package com.example.demo.controller;

import com.example.demo.dto.TeacherDTO;
import com.example.demo.entities.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherControllerTest {


    @Test
    void addSingleTeacher() {
        Teacher teacher= new Teacher();
        teacher.setFirstName("Lukasz");
        String firstName = teacher.getFirstName();
        assertEquals("Lukasz", firstName);

    }

    @Test
    void addTeacherToDB() {
    }

    @Test
    void delete() {
    }
}
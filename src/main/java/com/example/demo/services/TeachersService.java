package com.example.demo.services;

import com.example.demo.dto.TeacherDTO;
import com.example.demo.entities.Teacher;
import com.example.demo.repository.TeachersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeachersService {

    @Autowired
    private TeachersRepository teachersRepository;

    private static final Logger LOGGER = LogManager.getLogger(TeachersService.class);

    public List<TeacherDTO> findAll() {
        final List<Teacher> teachers = this.teachersRepository.findAll();
        LOGGER.info("Getting list of all teachers");
        return teachers.stream().map(entity -> new TeacherDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getNumber())).collect(Collectors.toList());

    }

    public List<TeacherDTO> findById(Long id) {
        final List<Teacher> teachers = this.teachersRepository.find(id);

        return teachers.stream().map(teacher -> new TeacherDTO(teacher.getId(), teacher.getFirstName(), teacher.getLastName(), teacher.getEmail(), teacher.getNumber())).collect(Collectors.toList());
    }


    public void saveNewTeacher(TeacherDTO teacherDTO) {
        final Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getTeacherFirstName());
        teacher.setLastName(teacherDTO.getTeacherLastName());
        teacher.setEmail(teacherDTO.getTeacherEmail());
        teacher.setNumber(teacherDTO.getTeacherNumber());
        LOGGER.debug("Received drink list" + teacher);
        this.teachersRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        final Teacher teacher = teachersRepository.find(id).get(0);

        teachersRepository.delete(teacher);
        LOGGER.debug("Delete teacher : " + teacher);
    }

    public TeacherDTO updateTeacher(TeacherDTO teacherDTO) {
        teacherDTO.setTeacherId((long) teachersRepository.findAll().size());
        teacherDTO.setTeacherFirstName(teacherDTO.getTeacherFirstName());
        teacherDTO.setTeacherLastName(teacherDTO.getTeacherLastName());
        teacherDTO.setTeacherEmail(teacherDTO.getTeacherEmail());
        teacherDTO.setTeacherNumber(teacherDTO.getTeacherNumber());
        System.out.println(teacherDTO);

        return teacherDTO;
    }


    public void changeTeacherDTOOnTeacherEntity(TeacherDTO teacherDTO){

        final Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.getTeacherId());
        teacher.setFirstName(teacherDTO.getTeacherFirstName());
        teacher.setLastName(teacherDTO.getTeacherLastName());
        teacher.setEmail(teacherDTO.getTeacherEmail());
        teacher.setNumber(teacherDTO.getTeacherNumber());
        System.out.println("Teacher entity: " + teacher);
        LOGGER.debug("Update teacher : " + teacher);
        teachersRepository.update(teacher);
    }
}

package com.example.demo.controller;

import com.example.demo.dto.TeacherDTO;
import com.example.demo.entities.Teacher;
import com.example.demo.services.TeachersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class TeacherController {


    @Autowired
    private TeachersService teachersService;
    private static final Logger LOGGER = LogManager.getLogger(TeacherController.class);

    @GetMapping("/teachers")
    public String getEvents(Model model) {

        List<TeacherDTO> teachers = teachersService.findAll();
        model.addAttribute("teacher", new TeacherDTO());
        model.addAttribute("teachers", teachers);

        return "teachers";
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public String getEvent(Model model, @PathVariable String id) {

        Long idTeacher = Long.valueOf(id);
        TeacherDTO teacherDTO = teachersService.findById(idTeacher).get(0);
        model.addAttribute("id", teacherDTO.getTeacherId());
        model.addAttribute("teacher", teacherDTO);

        return "teacher";
    }

    @GetMapping("/addTeacher")
    public String addSingleTeacher(Teacher teacher, Model model) {
        model.addAttribute("teachers", teachersService.findAll());
        model.addAttribute("teacher", new TeacherDTO());
        return "addTeacher";
    }

    @GetMapping("/removeTeacher")
    public String succesfullDeleteTeacher() {

        return "removeTeacher";
    }

    @GetMapping(value = "/editTeacher/{id}")
    public String editSingleTeacher(Model model, @PathVariable Long id) {
        teachersService.findAll();
        TeacherDTO teacherDTO = teachersService.findById(id).get(0);

        model.addAttribute("id", teacherDTO.getTeacherId());
        model.addAttribute("teacher", teacherDTO);
        LOGGER.debug("Edited teacher : "+ teacherDTO);
        return "editTeacher";
    }

    @PostMapping(value = {"/addTeacher"})
    public String addTeacherToDB(@ModelAttribute TeacherDTO teacherDTO, Model model) {
        model.addAttribute("teacher", teacherDTO);
        teachersService.saveNewTeacher(teacherDTO);
        teachersService.findAll();
        LOGGER.debug("Added teacher : " + teacherDTO);
        return "addTeacherSuccesful";
    }

    @PostMapping(value = "/removeTeacher/{id}")
    public String delete(@PathVariable Long id) {

        teachersService.findAll();
        Long idTeacher = Long.valueOf(id);
        teachersService.deleteTeacher(idTeacher);
        LOGGER.info(idTeacher);
        return "redirect:/removeTeacher";
    }

    @PostMapping(value = "/editTeacher/{id}")
    public String getEditTeacher(@ModelAttribute TeacherDTO teacherDTO, Model model, @PathVariable Long id) {

        model.addAttribute("teacher", teacherDTO);
        teachersService.updateTeacher(teacherDTO);
        List<TeacherDTO> list = new ArrayList<>();
        list.add(teacherDTO);
        teachersService.changeTeacherDTOOnTeacherEntity(list.get(0));

        return "redirect:/teachers";
    }


}

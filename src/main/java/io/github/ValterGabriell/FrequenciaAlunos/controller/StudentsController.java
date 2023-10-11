package io.github.ValterGabriell.FrequenciaAlunos.controller;

import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import io.github.ValterGabriell.FrequenciaAlunos.mapper.students.InsertStudents;
import io.github.ValterGabriell.FrequenciaAlunos.service.StudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {

    private final StudentsService service;


    public StudentsController(StudentsService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<InsertStudents> insertStudentsIntoDatabase(
            @RequestBody InsertStudents request,
            @RequestParam(required = true) String adminId) {
        InsertStudents student = service.insertStudentIntoDatabase(request, adminId);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PatchMapping(params = {"studentId"})
    public ResponseEntity<Student> updateStudent(@RequestBody InsertStudents request, @RequestParam String studentId) throws Exception {
        Student student = service.updateStudent(request, studentId);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> allStudentsFromDatabase = service.getAllStudentsFromDatabase();
        return new ResponseEntity<>(allStudentsFromDatabase, HttpStatus.OK);
    }

    @DeleteMapping(params = {"studentId"})
    public ResponseEntity<?> deleteStudent(String studentId) {
        service.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
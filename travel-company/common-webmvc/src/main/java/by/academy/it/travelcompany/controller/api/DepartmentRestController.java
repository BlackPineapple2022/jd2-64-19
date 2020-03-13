package by.academy.it.travelcompany.controller.api;

import by.academy.it.travelcompany.orm.entity.Department;
import by.academy.it.travelcompany.orm.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "departments")
public class DepartmentRestController {

    @Autowired
    private DepartamentRepository departamentRepository;

    @GetMapping
    public ResponseEntity<Iterable<Department>> getAllDepartment() {
        Iterable<Department> departments = departamentRepository.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Iterable<Department>> getDepartmentById(@PathVariable("id") Long id) {
        Department department = departamentRepository.findById(id).get();
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDepartment(@PathVariable("id") Long id) {
        departamentRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}" )
    public ResponseEntity<Department> updateDepartment(
            @PathVariable("id") Long id,
            @RequestBody Department newDepartment) {
        Optional<Department> department = departamentRepository.findById(id);
        if (!department.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (newDepartment.getDepartmentName()!=null) {
            department.get().setDepartmentName(newDepartment.getDepartmentName());
        }
        return new ResponseEntity<>(departamentRepository.save(department.get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @RequestBody Department newDepartment) {
        newDepartment = departamentRepository.save(newDepartment);
        return new ResponseEntity<>(newDepartment, HttpStatus.OK);
    }

}

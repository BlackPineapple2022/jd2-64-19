package by.academy.it.travelcompany.controller.api;

import by.academy.it.travelcompany.orm.entity.Employee;
import by.academy.it.travelcompany.orm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Employee>> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.get(id);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.remove(id);
    }

    @PutMapping(value = "/{id}" )
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("id") Long id,
            @RequestBody Employee newEmployee) {
        Employee employee = employeeService.get(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (newEmployee.getFirstName()!=null) {
            employee.setFirstName(newEmployee.getFirstName());
        }

        if (newEmployee.getLastName()!=null) {
            employee.setLastName(newEmployee.getLastName());
        }

        if(newEmployee.getJoinDate()!=null){
            employee.setJoinDate(newEmployee.getJoinDate());
        }

        newEmployee = employeeService.update(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Employee> createEmployee(
            @PathVariable("id") Long id,
            @RequestBody Employee newEmployee) {

        if (employeeService.get(id) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        newEmployee = employeeService.add(newEmployee);
        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }



}

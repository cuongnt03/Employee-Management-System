package com.springboot.ems.controller;

import com.springboot.ems.entity.Employee;
import com.springboot.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //display home page with list of employee
    @RequestMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    //display form to add
    @GetMapping("/add")
    public String showFormToAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add_employee";
    }

    // save employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        // save employee to database
        if (employeeService.findEmployeeByEmail(employee.getEmail()) != null)
            result.rejectValue("email", null, "There was already had existed employee");
        if (result.hasErrors()) {
//            model.addAttribute("email", employee);
            return "add_employee";
        }
            employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    //update employee by id
    @GetMapping("/update/{id}")
    public String showFormToUpdate(@PathVariable (value = "id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    // delete employee by turning isAcive to false
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id) {
        Employee employee = employeeService.getEmployeeById(id);
        employee.setActive(false);
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}

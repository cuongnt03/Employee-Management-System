package com.springboot.ems.service;

import com.springboot.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
public interface EmployeeService {
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    Employee findEmployeeByEmail(String email);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

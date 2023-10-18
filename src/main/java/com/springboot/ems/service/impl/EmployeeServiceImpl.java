package com.springboot.ems.service.impl;

import com.springboot.ems.entity.Employee;
import com.springboot.ems.repository.EmployeeRepository;
import com.springboot.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee); // neu loi thi them this.
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Employee not found for id :: " + id));
    }

    @Override
    public void deleteEmployeeById(Long id) {
        this.employeeRepository.deleteById(id); // neu loi thi them this.
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable); // neu loi thi them this.
    }
}

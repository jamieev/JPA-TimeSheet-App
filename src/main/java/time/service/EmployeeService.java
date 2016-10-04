package time.service;

import java.util.List;

import time.model.Employee;

public interface EmployeeService {

    public List<Employee> listEmployees();

    public Employee createEmployee(Employee employee);

    public void deleteEmployee(Long id);

    public Employee getEmployee(Long id);
    
	public List<Employee> findByProject(Long projectId);
}

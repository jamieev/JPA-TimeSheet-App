package time.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import time.Utils;
import time.model.Employee;
import time.repo.EmployeeRepository;
import time.service.EmployeeService;

@Service
public class EmloyeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Resource
	EmployeeRepository employeeRepo;

    public List<Employee> listEmployees() {
    	log.info("Listing employees ");
        return Utils.toList(employeeRepo.findAll());
    }

    public Employee createEmployee(Employee employee) {
    	log.info("Saving employee: " + employee);
    	return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
    	log.info("Deleting employee: " + id);
    	employeeRepo.delete(id);
    }

    public Employee getEmployee(Long id) {
    	return employeeRepo.findOne(id);
    }
    
	public List<Employee> findByProject(Long projectId) {
		return Utils.toList(employeeRepo.findByProjectId(projectId));
	}

}

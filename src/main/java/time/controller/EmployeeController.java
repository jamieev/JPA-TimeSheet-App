package time.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import time.Utils;
import time.exceptions.CustomerNotFoundException;
import time.exceptions.ProjectNotFoundException;
import time.model.DailyEntry;
import time.model.Employee;
import time.service.DailyEntryService;
import time.service.EmployeeService;
import time.service.ProjectService;

@RestController
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Resource
	ProjectService projectService;
	
	@Resource
	EmployeeService employeeService;
		
	@Resource
	DailyEntryService entryService;
	
	@RequestMapping(value="/employees")
	public List<Employee> listEmployees() {
		return Utils.toList(employeeService.listEmployees());
	}
	
	@RequestMapping(value="/projects/{projectId}/employees")
	public List<Employee> findByProject(@PathVariable(name="projectId") Long projectId) {
		return Utils.toList(employeeService.findByProject(projectId));
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee) {
		log.info("Creating employee: " + employee);
		return employeeService.createEmployee(employee);
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.DELETE)
	public void deleteEmployee(Long id) {
		log.info("Deleteing employee: " + id);
		employeeService.deleteEmployee(id);
	}
	
	@RequestMapping(value="/employees/{id}", method=RequestMethod.GET)
	public Employee getEmployee(@PathVariable(name="id") Long id) {
		log.info("getting employee id : " + id);
		return employeeService.getEmployee(id);
	}
	
	@RequestMapping(value="/employees/{id}/addToProject/{projectId}", method=RequestMethod.PUT)
	public void addToProject(@PathVariable(name="id")Long id, @PathVariable(name="projectId") Long projectId) 
	throws CustomerNotFoundException, ProjectNotFoundException{
		projectService.addEmployeeToProject(id, projectId);
	}
	
	@RequestMapping(value="employees/dailyEntries", method=RequestMethod.POST)
	public DailyEntry createDailyEntry(@RequestBody DailyEntry entry) {
		log.info("creating daily entry: " + entry);
		return entryService.createDailyEntry(entry);
	}
	
	@RequestMapping(value="employees/{id}/dailyEntries", method=RequestMethod.GET)
	public List<DailyEntry> findEntriesByEmployee(@PathVariable(name="id") Long id) {
		log.info("Finding entries for employee id " + id);
		return entryService.findEntriesByEmployee(id);
	}
}

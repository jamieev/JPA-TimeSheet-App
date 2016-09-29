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
import time.model.Project;
import time.repo.DailyEntryRepository;
import time.repo.EmployeeRepository;
import time.repo.ProjectRepository;

@RestController
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Resource
	EmployeeRepository employeeRepo;
	
	@Resource
	ProjectRepository projRepo;
	
	@Resource
	DailyEntryRepository entryRepo;
	
	@RequestMapping(value="/employees")
	public List<Employee> listEmployees() {
		return Utils.toList(employeeRepo.findAll());
	}
	
	@RequestMapping(value="/projects/{projectId}/employees")
	public List<Employee> findByProject(@PathVariable(name="projectId") Long projectId) {
		return Utils.toList(employeeRepo.findByProjectId(projectId));
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee) {
		log.info("Creating employee: " + employee);
		return employeeRepo.save(employee);
	}
	
	@RequestMapping(value="/employees/{id}", method=RequestMethod.GET)
	public Employee getEmployee(@PathVariable(name="id") Long id) {
		log.info("getting employee id : " + id);
		return employeeRepo.findOne(id);
	}
	
	@RequestMapping(value="/employees/{id}/addToProject/{projectId}", method=RequestMethod.POST)
	public void addToProject(@PathVariable(name="id")Long id, @PathVariable(name="projectId") Long projectId) 
	throws CustomerNotFoundException, ProjectNotFoundException{
		log.info("adding employee " +  id + " to project " + projectId);
		
		//NB this should be in a business service
		Employee e = employeeRepo.findOne(id);
		if(e == null) throw new CustomerNotFoundException();
		Project p = projRepo.findOne(projectId);
		if(p == null) throw new ProjectNotFoundException();
		e.addProject(p);
		employeeRepo.save(e);
	}
	
	@RequestMapping(value="employees/dailyEntries", method=RequestMethod.POST)
	public DailyEntry createDailyEntry(@RequestBody DailyEntry entry) {
		log.info("creating daily entry: " + entry);
		return entryRepo.save(entry);
	}
	
	@RequestMapping(value="employees/{id}/dailyEntries", method=RequestMethod.GET)
	public List<DailyEntry> findEntriesByEmployee(@PathVariable(name="id") Long id) {
		log.info("Finding entries for employee id " + id);
		return entryRepo.findByEmployee(id);
	}

}

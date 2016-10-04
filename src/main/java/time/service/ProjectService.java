package time.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import time.exceptions.CustomerNotFoundException;
import time.exceptions.ProjectNotFoundException;
import time.model.Employee;
import time.model.Project;
import time.repo.EmployeeRepository;
import time.repo.ProjectRepository;

@Service
public class ProjectService {
	//NB this should be an interface and implementation ..
	
	private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

	@Resource
	EmployeeRepository employeeRepo;
	@Resource
	ProjectRepository projRepo;

	public void addEmployeeToProject(Long employeeId, Long projectId) 
			throws CustomerNotFoundException, ProjectNotFoundException{
		log.info("adding employee " +  employeeId + " to project " + projectId);
		
		//NB this should be in a business service
		Employee e = employeeRepo.findOne(employeeId);
		if(e == null) throw new CustomerNotFoundException();
		Project p = projRepo.findOne(projectId);
		if(p == null) throw new ProjectNotFoundException();
		e.addProject(p);
		employeeRepo.save(e);
	}

}

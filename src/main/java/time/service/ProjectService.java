package time.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import time.Utils;
import time.exceptions.CustomerNotFoundException;
import time.exceptions.ProjectNotFoundException;
import time.model.Employee;
import time.model.Project;
import time.model.ProjectTask;
import time.repo.EmployeeRepository;
import time.repo.ProjectRepository;

@Service
public class ProjectService {
	//NB this should be an interface and implementation ..
	
	private static final String DEFAULT_TASK = "Default Task";

	private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

	@Resource
	EmployeeRepository employeeRepo;
	@Resource
	ProjectRepository projectRepo;

	public void addEmployeeToProject(Long employeeId, Long projectId) 
			throws CustomerNotFoundException, ProjectNotFoundException{
		log.info("adding employee " +  employeeId + " to project " + projectId);
		
		//NB this should be in a business service
		Employee e = employeeRepo.findOne(employeeId);
		if(e == null) throw new CustomerNotFoundException();
		Project p = projectRepo.findOne(projectId);
		if(p == null) throw new ProjectNotFoundException();
		e.addProject(p);
		employeeRepo.save(e);
	}

	public Project createProject(Project project) {
		log.info("Creating Project: " + project);
		if(project.getTasks().isEmpty()) {
			ProjectTask task = new ProjectTask(DEFAULT_TASK);
			project.addTask(task);
		}
		return projectRepo.save(project);
	}
	
	public Project getProject(Long id) {
		log.info("getting Project id : " + id);
		return projectRepo.findOne(id);
	}
	
	public Project findByName(String name) {
		log.info("getting Project by name : " + name);
		return projectRepo.findOneByProjectName(name);
	}
	
	public List<Project> listProjects() {
		return Utils.toList(projectRepo.findAll());
	}
}

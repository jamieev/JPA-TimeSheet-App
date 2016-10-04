package time.service;

import java.util.List;

import org.springframework.stereotype.Service;

import time.exceptions.CustomerNotFoundException;
import time.exceptions.ProjectNotFoundException;
import time.model.Project;

@Service
public interface ProjectService {

	public void addEmployeeToProject(Long employeeId, Long projectId) 
			throws CustomerNotFoundException, ProjectNotFoundException;

	public Project createProject(Project project);
	
	public Project getProject(Long id);
	
	public Project findByName(String name);
	
	public List<Project> listProjects();
}

package time.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import time.model.Project;
import time.repo.ProjectRepository;

@RestController
public class ProjectController {

	private static final Logger log = LoggerFactory.getLogger(ProjectController.class);
	
	@Resource
	ProjectRepository projectRepo;
	
	@RequestMapping(value="/projects", method=RequestMethod.POST)
	public Project createProject(Project project) {
		log.info("Creating Project: " + project);
		return projectRepo.save(project);
	}
	
	@RequestMapping(value="/projects", method=RequestMethod.GET)
	public Project getProject(@RequestParam(name="id") Long id) {
		log.info("getting Project id : " + id);
		return projectRepo.findOne(id);
	}
}

package time.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import time.Utils;
import time.model.Project;
import time.service.ProjectService;

@RestController
public class ProjectController {

	private static final Logger log = LoggerFactory.getLogger(ProjectController.class);
	
	@Resource
	ProjectService projectService;
	
	@RequestMapping(value="/projects", method=RequestMethod.POST)
	public Project createProject(@RequestBody Project project) {
		log.info("Creating Project: " + project);
		return projectService.createProject(project);
	}
	
	@RequestMapping(value="/projects/{id}", method=RequestMethod.GET)
	public Project getProject(@PathVariable(name="id") Long id) {
		log.info("getting Project id : " + id);
		return projectService.getProject(id);
	}
	
	@RequestMapping(value="/projects/search", method=RequestMethod.GET)
	public Project findByName(@RequestParam(name="name") String name) {
		log.info("getting Project by name : " + name);
		return projectService.findByName(name);
	}
	
	@RequestMapping(value="/projects")
	public List<Project> listProjects() {
		return Utils.toList(projectService.listProjects());
	}
}

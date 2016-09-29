package time.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import time.Utils;
import time.model.Project;
import time.repo.ProjectRepository;

@Controller
public class ProjectWebController {

	@Resource
	ProjectRepository projectRepo;
	
    @RequestMapping("/listProjects")
    public String listProjects( Model model) {
        model.addAttribute("projects", Utils.toList(projectRepo.findAll()));
        return "listProjects";
    }

    @GetMapping(value="/createProject")
    public String addProjectForm(Model model) {
    	model.addAttribute("project", new Project("blurb"));
        return "createProject";
    }

    @PostMapping(value="/createProject")
    public String addProjectSubmit(@ModelAttribute Project project) {
    	projectRepo.save(project);
        return "forward:/listProjects";
    }
}

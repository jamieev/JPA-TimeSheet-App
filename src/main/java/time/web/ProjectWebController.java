package time.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import time.Utils;
import time.exceptions.CustomerNotFoundException;
import time.exceptions.ProjectNotFoundException;
import time.model.Project;
import time.repo.EmployeeRepository;
import time.service.ProjectService;

@Controller
public class ProjectWebController {

	@Resource
	ProjectService projectService;
	
	@Resource
	EmployeeRepository employeeRepo;
	
    @RequestMapping("/listProjects")
    public String listProjects( Model model) {
        model.addAttribute("projects", Utils.toList(projectService.listProjects()));
        return "listProjects";
    }

    @GetMapping(value="/createProject")
    public String addProjectForm(Model model) {
    	model.addAttribute("project", new Project("blurb"));
        return "createProject";
    }

    @GetMapping(value="/viewProject")
    public String viewProject(Model model, Long id) {
    	model.addAttribute("project",projectService.getProject(id));
    	model.addAttribute("employees", employeeRepo.findByProjectId(id));
    	model.addAttribute("allEmployees", employeeRepo.findAll());
        return "viewProject";
    }

    @PostMapping(value="/createProject")
    public String addProjectSubmit(@ModelAttribute Project project) {
    	projectService.createProject(project);
        return "forward:/listProjects";
    }
    
    @PostMapping(value="/addEmployee")
    public String addEmployee(Model model, Long projectId, Long employeeId) throws CustomerNotFoundException, ProjectNotFoundException {
    	//TODO add check for existing employees.
    	projectService.addEmployeeToProject(employeeId, projectId);
    	model.addAttribute(projectId);
        return "redirect:/viewProject?id="+projectId;
    }
}

package time.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import time.Utils;
import time.model.Employee;
import time.repo.EmployeeRepository;

@Controller
public class EmployeeWebController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeWebController.class);
	@Resource
	EmployeeRepository employeeRepo;
	
    @RequestMapping("/listEmployees")
    public String listEmployees( Model model) {
        model.addAttribute("employees", Utils.toList(employeeRepo.findAll()));
        return "listEmployees";
    }

    @GetMapping(value="/createEmployee")
    public String addEmployeeForm(Model model) {
    	model.addAttribute("employee", new Employee());
        return "createEmployee";
    }

    @PostMapping(value="/createEmployee")
    public String addEmployeeSubmit(@ModelAttribute Employee employee) {
    	log.info("Saving employee: " + employee);
    	employeeRepo.save(employee);
        return "forward:/listEmployees";
    }
}

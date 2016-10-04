package time.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import time.Utils;
import time.model.TimeSheet;
import time.service.TimeSheetService;

@Controller
public class TimeSheetWebController {
	
	@Resource
	TimeSheetService tsService;

	@GetMapping(value="/listTimesheets")
    public String listTimeSheets( Model model) {
        model.addAttribute("timesheets", Utils.toList(tsService.listTimeSheets()));
        return "listTimesheets";
    }
	
    @GetMapping(value="/createTimesheet")
    public String addTimeSheetForm(Model model, @RequestParam(name="employeeId") Long employeeId) {
       	model.addAttribute("timesheet", new TimeSheet());
       	model.addAttribute("employeeId", employeeId);//should be retrieved from session
        return "createTimesheet";
    }

    @PostMapping(value="/createTimesheet")
    public String addTimesheetSubmit(@ModelAttribute TimeSheet timeSheet,  @RequestParam(name="employeeId") Long employeeId) {
    	tsService.createTimeSheet(timeSheet, employeeId);
        return "redirect:/listTimesheets";
    }

    @GetMapping(value="/submitTimesheet")
    public String submitTimesheetSubmit( @RequestParam(name="timesheetId") Long id) throws Exception  {
    	tsService.submitTimeSheet(id);
        return "redirect:/listTimesheets";
    }

    @GetMapping(value="/approveTimesheet")
    public String approveTimesheetSubmit( @RequestParam(name="timesheetId") Long id) throws Exception  {
    	tsService.approveTimeSheet(id);
        return "redirect:/listTimesheets";
    }
}

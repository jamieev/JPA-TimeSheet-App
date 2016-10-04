package time.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import time.Utils;
import time.constants.TimeSheetStatus;
import time.exceptions.IncorrectTimeSheetStatusException;
import time.exceptions.TimeSheetIncompleteException;
import time.exceptions.TimeSheetNotFoundException;
import time.model.DailyEntry;
import time.model.Employee;
import time.model.Project;
import time.model.TimeSheet;
import time.repo.TimeSheetRepository;
import time.service.EmployeeService;
import time.service.TimeSheetService;

@Service
public class TimeSheetServiceImpl implements TimeSheetService {

	private static final Logger log = LoggerFactory.getLogger(TimeSheetService.class);

	@Resource
	TimeSheetRepository timeSheetRepo;
	
	@Resource
	EmployeeService empService;
	
	public void submitTimeSheet(Long id) 
			throws TimeSheetNotFoundException, IncorrectTimeSheetStatusException, TimeSheetIncompleteException {
		TimeSheet ts = timeSheetRepo.findOne(id);
		if(ts == null) throw new TimeSheetNotFoundException();
		if(ts.getStatus() != TimeSheetStatus.NEW) throw new IncorrectTimeSheetStatusException();
		if(!ts.isComplete()) throw new TimeSheetIncompleteException();
		ts.submit();
		timeSheetRepo.save(ts);
	}

	public void approveTimeSheet(Long id) throws TimeSheetNotFoundException, IncorrectTimeSheetStatusException {
		TimeSheet ts = timeSheetRepo.findOne(id);
		if(ts == null) throw new TimeSheetNotFoundException();
		if(ts.getStatus() != TimeSheetStatus.AWAITING_APPROVAL) throw new IncorrectTimeSheetStatusException();
		ts.approve();
		timeSheetRepo.save(ts);
	}

	public TimeSheet createTimeSheet(TimeSheet timeSheet, Long employeeId) {
		log.info("Creating sneaky timesheet");
		//cheat method to avoid having to do a web page!
		Employee emp = empService.getEmployee(employeeId);
		Project proj = emp.getProjects().get(0);
		Calendar cal = Calendar.getInstance();
		List<DailyEntry> entries = new ArrayList<DailyEntry>(5);
		for(int i = 1; i< 6;i++) {
			DailyEntry e = new DailyEntry(cal.getTime(), proj, proj.getTasks().get(0), emp, 1);
			entries.add(e);
			cal.add(Calendar.DAY_OF_WEEK, -1);
		}
		
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DAY_OF_WEEK, 5);
		
		TimeSheet ts = new TimeSheet(emp, cal.getTime(), cal2.getTime());
		ts.setEntries(entries);
		
		
		return createTimeSheet(ts);
	}
	
	public TimeSheet createTimeSheet(TimeSheet timeSheet) {
		log.info("Creating timesheet");
		
		return timeSheetRepo.save(timeSheet);
	}
	
	public TimeSheet getTimeSheet(Long id) {
		return timeSheetRepo.findOne(id);
	}
	
	public List<TimeSheet> listTimeSheets() {
		return Utils.toList(timeSheetRepo.findAll());
	}

	public List<TimeSheet> listTimeSheetsByEmployee(Long employeeId) {
		return Utils.toList(timeSheetRepo.findByEmployee(employeeId));
	}
}

package time.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import time.Utils;
import time.constants.TimeSheetStatus;
import time.exceptions.IncorrectTimeSheetStatusException;
import time.exceptions.TimeSheetIncompleteException;
import time.exceptions.TimeSheetNotFoundException;
import time.model.TimeSheet;
import time.repo.TimeSheetRepository;

@RestController
public class TimeSheetController {

	private static final Logger log = LoggerFactory.getLogger(TimeSheetController.class);
	
	@Resource
	TimeSheetRepository timeSheetRepo;
	
	@RequestMapping(value="/timesheets", method=RequestMethod.POST)
	public TimeSheet createTimeSheet(@RequestBody TimeSheet timeSheet) {
		log.info("Creating TimeSheet: " + timeSheet);
		return timeSheetRepo.save(timeSheet);
	}
	
	@RequestMapping(value="/timesheets/{id}", method=RequestMethod.GET)
	public TimeSheet getTimeSheet(@PathVariable(name="id") Long id) {
		log.info("getting TimeSheet id : " + id);
		return timeSheetRepo.findOne(id);
	}
	
	@RequestMapping(value="/timesheets")
	public List<TimeSheet> listTimeSheets() {
		return Utils.toList(timeSheetRepo.findAll());
	}
	
	@RequestMapping(value="/timesheets/{id}/submit")
	public void submitTimeSheet(@PathVariable(name="id") Long id) 
			throws TimeSheetNotFoundException, IncorrectTimeSheetStatusException, TimeSheetIncompleteException {
		TimeSheet ts = timeSheetRepo.findOne(id);
		if(ts == null) throw new TimeSheetNotFoundException();
		if(ts.getStatus() != TimeSheetStatus.NEW) throw new IncorrectTimeSheetStatusException();
		if(!ts.isComplete()) throw new TimeSheetIncompleteException();
		ts.submit();
		timeSheetRepo.save(ts);
	}

	@RequestMapping(value="/timesheets/{id}/approve")
	public void approveTimeSheet(@PathVariable(name="id") Long id) 
			throws TimeSheetNotFoundException, IncorrectTimeSheetStatusException {
		TimeSheet ts = timeSheetRepo.findOne(id);
		if(ts == null) throw new TimeSheetNotFoundException();
		if(ts.getStatus() != TimeSheetStatus.AWAITING_APPROVAL) throw new IncorrectTimeSheetStatusException();
		ts.approve();
		timeSheetRepo.save(ts);
	}
}

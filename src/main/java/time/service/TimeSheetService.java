package time.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import time.Utils;
import time.constants.TimeSheetStatus;
import time.exceptions.IncorrectTimeSheetStatusException;
import time.exceptions.TimeSheetIncompleteException;
import time.exceptions.TimeSheetNotFoundException;
import time.model.TimeSheet;
import time.repo.TimeSheetRepository;

@Service
public class TimeSheetService {

	@Resource
	TimeSheetRepository timeSheetRepo;
	
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

	public TimeSheet createTimeSheet(TimeSheet timeSheet) {
		return timeSheetRepo.save(timeSheet);
	}
	
	public TimeSheet getTimeSheet(Long id) {
		return timeSheetRepo.findOne(id);
	}
	

	public List<TimeSheet> listTimeSheets() {
		return Utils.toList(timeSheetRepo.findAll());
	}
}

package time.service;

import java.util.List;

import org.springframework.stereotype.Service;

import time.exceptions.IncorrectTimeSheetStatusException;
import time.exceptions.TimeSheetIncompleteException;
import time.exceptions.TimeSheetNotFoundException;
import time.model.TimeSheet;

@Service
public interface TimeSheetService {

	
	public void submitTimeSheet(Long id) 
			throws TimeSheetNotFoundException, IncorrectTimeSheetStatusException, TimeSheetIncompleteException;
	
	public void approveTimeSheet(Long id) throws TimeSheetNotFoundException, IncorrectTimeSheetStatusException;

	public TimeSheet createTimeSheet(TimeSheet timeSheet, Long employeeId) ;
	
	public TimeSheet createTimeSheet(TimeSheet timeSheet);
	
	public TimeSheet getTimeSheet(Long id);
	
	public List<TimeSheet> listTimeSheets();

	public List<TimeSheet> listTimeSheetsByEmployee(Long employeeId);
}

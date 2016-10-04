package time.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import time.constants.TimeSheetStatus;

public class TimeSheetTest {

	private TimeSheet ts;
	private Employee employee = new Employee("Joe", "Bloggs", "gt000000");
	private Project project = new Project("Big Project");
	private ProjectTask task = new ProjectTask("task1");
	private List<DailyEntry> entries;
	static Calendar cal = Calendar.getInstance();
	
	@Before
	public void setUp() throws Exception {
		entries = new ArrayList<DailyEntry>(5);
		for(int i = 1; i< 6;i++) {
			DailyEntry e = new DailyEntry(cal.getTime(), project, task, employee, 1);
			entries.add(e);
			cal.add(Calendar.DAY_OF_WEEK, -1);
		}
		
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DAY_OF_WEEK, 5);
		
		ts = new TimeSheet(employee, cal.getTime(), cal2.getTime());
		ts.setEntries(entries);
	}

	@Test
	public void testNewStatus() {
		assertEquals(TimeSheetStatus.NEW, ts.getStatus());
	}

	@Test
	public void testApprove() {
		ts.approve();
		assertEquals(TimeSheetStatus.APPROVED, ts.getStatus());
	}

	@Test
	public void testSubmit() {
		ts.submit();
		assertEquals(TimeSheetStatus.AWAITING_APPROVAL, ts.getStatus());
	}

	@Test
	public void testGetValue() {
		assertTrue(ts.getValue() == 5D);
	}

	@Test
	public void testIsComplete() {
		assertTrue(ts.isComplete());
	}

	@Test
	public void testIsNotComplete() {
		List<DailyEntry> incompleteEntries = new ArrayList<DailyEntry>(1);
		DailyEntry e = new DailyEntry(cal.getTime(), project, task, employee, 1);
		incompleteEntries.add(e);
		
		Date start = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK,1);
		Date end = cal.getTime();
		
		TimeSheet incomplete =  new TimeSheet(employee, start, end);
		incomplete.setEntries(incompleteEntries);
		assertFalse(incomplete.isComplete());
	}
}

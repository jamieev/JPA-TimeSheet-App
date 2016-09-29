package time.integration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.web.client.RestTemplate;

import time.model.DailyEntry;
import time.model.Employee;
import time.model.Project;
import time.model.ProjectTask;
import time.model.TimeSheet;

public class RestIntegrationTests {
	
	private static String URL = "http://localhost:8080/";

	public static void main(String[] args) throws Exception {
		RestTemplate template = new RestTemplate();
		String unique = UUID.randomUUID().toString();
		//Create a project with tasks
		String projectName = "TestProj" + unique;
		ProjectTask task1 = new ProjectTask(projectName + " Task One");
		ProjectTask task2 = new ProjectTask(projectName + " Task Two");
		Project proj = new Project(projectName);
		proj.addTask(task1);
		proj.addTask(task2);

		Project savedProj = template.postForObject(URL + "projects", proj, Project.class);
		System.out.println("Created Project: " + savedProj);
		
		//search by name
		System.out.println("Found by Name: " + template.getForObject(URL + "projects/search?name=" + projectName, Project.class));

		//create employee
		Employee joe = new Employee(unique, "TestUser", unique);
		joe = template.postForObject(URL + "employees", joe, Employee.class);
		System.out.println("Created Employee: " + joe);

		//find employee
		System.out.println("Found employee: " + template.getForObject(URL + "employees/" + joe.getId(), Employee.class));
		
		//add to project
		template.put(URL + "/employees/" + joe.getId() + 
				"/addToProject/" + savedProj.getId(),null);
		
		//find by project
		System.out.println("Found employees for project: " + template.getForObject(URL + "/projects/" + savedProj.getId() + "/employees", List.class));
	
		//create timesheet
		Calendar cal = Calendar.getInstance();
		List<DailyEntry> entries = new ArrayList<DailyEntry>(5);
		for(int i = 1; i< 6;i++) {
			DailyEntry e = new DailyEntry(cal.getTime(), savedProj, savedProj.getTasks().get(0), joe, 1);
			entries.add(e);
			cal.add(Calendar.DAY_OF_WEEK, -1);
		}
		
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DAY_OF_WEEK, 5);
		
		TimeSheet ts = new TimeSheet(joe, cal.getTime(), cal2.getTime());
		ts.setEntries(entries);
		ts = template.postForObject(URL + "/timesheets", ts, TimeSheet.class);
		
		System.out.println("Created Timesheet" + ts);
		
		//find timesheets
		System.out.println("Found timesheet: " + template.getForObject(URL + "/timesheets/" + ts.getId(), TimeSheet.class));

	}

}

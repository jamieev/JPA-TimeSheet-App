package time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import time.model.DailyEntry;
import time.model.Employee;
import time.model.Project;
import time.model.ProjectTask;
import time.model.TimeSheet;
import time.repo.DailyEntryRepository;
import time.repo.EmployeeRepository;
import time.repo.ProjectRepository;
import time.repo.TaskRepository;
import time.repo.TimeSheetRepository;

@SpringBootApplication
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository empRepo,
			ProjectRepository projRepo, TaskRepository taskRepo,
			DailyEntryRepository entryRepo, TimeSheetRepository timeSheetRepo) {
		return (args) -> {
			//create task
			ProjectTask task1 = new ProjectTask("Big Project Task One");
			ProjectTask task2 = new ProjectTask("Big Project Task Two");
			//create project
			String projectName = "Big Project";
			Project project1 = new Project(projectName);
			project1.addTask(task1);
			project1.addTask(task2);

			projRepo.save(project1);
			
			Employee jamie = new Employee("Joe", "Bloggs", "gt909999");
			jamie.addProject(project1);
			empRepo.save(jamie);
			Employee eimaj = new Employee("Eoj", "Sggolb", "gt909991");
			eimaj.addProject(project1);
			empRepo.save(jamie);
			empRepo.save(eimaj);


			log.info("--------------------------");
			for(Employee emp : (empRepo.findAll())) {
				log.info(emp.toString());
			}
			
			log.info(projRepo.findAll().toString());
			log.info(taskRepo.findAll().toString());
			
			log.info("Employees: " + empRepo.findByProjectId(project1.getId()));
			
			Calendar cal = Calendar.getInstance();
			DailyEntry entry = new DailyEntry(cal.getTime(), project1, task1, jamie, 1f); 
			DailyEntry entry1 = new DailyEntry(cal.getTime(), project1, task1, eimaj, 0.5f);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			DailyEntry entry2 = new DailyEntry(cal.getTime(), project1, task1, eimaj, 0.5f); 
			entryRepo.save(entry);
			entryRepo.save(entry1);
			entryRepo.save(entry2);
			log.info("--------------------------");
			log.info(entryRepo.findAll().toString());
			log.info("--------------------------");
			log.info(entryRepo.findByEmployee(eimaj).toString());
			
			log.info("--------------------------");

			List<DailyEntry> entries = new ArrayList<DailyEntry>(5);
			for(int i = 1; i< 6;i++) {
				DailyEntry e = new DailyEntry(cal.getTime(), project1, task1, jamie, 1);
				entries.add(e);
				cal.add(Calendar.DAY_OF_WEEK, -1);
			}
			
			Calendar cal2 = Calendar.getInstance();
			cal2.add(Calendar.DAY_OF_WEEK, 5);
			
			TimeSheet ts = new TimeSheet(jamie, cal.getTime(), cal2.getTime());
			ts.setEntries(entries);
			timeSheetRepo.save(ts);
			
			log.info("--------------------------");
			for(TimeSheet sheet: timeSheetRepo.findByEmployee(jamie)) {
				log.info("TS: " + sheet.toString());
			}
			
			
		};
	}
}

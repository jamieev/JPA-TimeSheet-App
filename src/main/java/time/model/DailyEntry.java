package time.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DailyEntry {

	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	Date date;
	
	@ManyToOne
	Project project;
	
	@ManyToOne
	ProjectTask task;
	
	@ManyToOne
	Employee employee;
	
	double value;

	public DailyEntry(Date date, Project project, ProjectTask task, Employee employee, double value) {
		super();
		this.date = date;
		this.project = project;
		this.task = task;
		this.employee = employee;
		this.value = value;
	}

	public DailyEntry() {}
	
	public double getValue() {
		return value;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Project getProject() {
		return project;
	}

	public ProjectTask getTask() {
		return task;
	}

	public Employee getEmployee() {
		return employee;
	}

	@Override
	public String toString() {
		return "DailyEntry [id=" + id + ", date=" + date + ", project=" + project.getProjectName() + ", task=" + task.getTaskName() + ", employee="
				+ employee.getGtNumber() + ", value=" + value + "]";
	}
	
}

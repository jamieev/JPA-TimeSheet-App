package time.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"gtNumber"}),
		@UniqueConstraint(columnNames = {"firstName", "lastName"})})
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String gtNumber;
	
	public Employee() {}
	
	public Employee(String firstName, String lastName, String gtNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gtNumber = gtNumber;
	}
	
	@ManyToMany
	List<Project> projects = new ArrayList<Project>();
	
	public void addProject(Project project) {
		projects.add(project);
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public String getGtNumber() {
		return gtNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public Long getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGtNumber(String gtNumber) {
		this.gtNumber = gtNumber;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gtNumber=" + gtNumber
				+ "]";
	}

	
}

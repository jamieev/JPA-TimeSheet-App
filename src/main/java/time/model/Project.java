package time.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"projectName"})})
public class Project {

	@Id
	@GeneratedValue
	private Long id;
	private String projectName;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	List<ProjectTask> tasks = new ArrayList<ProjectTask>();
	
	public Project() {}
	
	public Project(String projectName) {
		super();
		this.projectName = projectName;
	}

	public void addTask(ProjectTask task) {
		task.setProject(this);
		tasks.add(task);
	}

	public Long getId() {
		return id;
	}

	public String getProjectName() {
		return projectName;
	}

	public List<ProjectTask> getTasks() {
		return Collections.unmodifiableList(this.tasks);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", tasks=" + tasks + "]";
	}
}

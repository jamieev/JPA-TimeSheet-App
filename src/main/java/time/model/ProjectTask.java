package time.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ProjectTask {

	@Id
	@GeneratedValue
	private Long id;
	private String taskName;
	
	@ManyToOne
	@JsonBackReference
	private Project project;
	
	public ProjectTask() {}
	public ProjectTask(String taskName) {
		super();
		this.taskName = taskName;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Project getProject() {
		return project;
	}
	@Override
	public String toString() {
		return "ProjectTask [id=" + id + ", taskName=" + taskName + ", project=" + project.getId() + "]";
	}
}

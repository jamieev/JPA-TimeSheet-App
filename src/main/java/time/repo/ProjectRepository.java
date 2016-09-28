package time.repo;

import org.springframework.data.repository.CrudRepository;

import time.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	Project findOneByProjectName(String projectName);
}

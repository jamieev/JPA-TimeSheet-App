package time.repo;

import org.springframework.data.repository.CrudRepository;

import time.model.ProjectTask;

public interface TaskRepository extends CrudRepository<ProjectTask, Long> {

}

package time.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import time.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	List<Employee> findByLastName(String lastName);
	
	@Query("select E from Employee E join E.projects P where P.id = ?1")
	List<Employee> findByProjectId(Long projectId);
}

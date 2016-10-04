package time.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import time.model.TimeSheet;

public interface TimeSheetRepository extends CrudRepository<TimeSheet, Long>{
	
	@Query("select T from TimeSheet T where employee.id = ?1")
	List<TimeSheet> findByEmployee(Long employeeId);

}

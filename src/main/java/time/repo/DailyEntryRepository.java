package time.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import time.model.DailyEntry;
import time.model.Employee;

public interface DailyEntryRepository extends CrudRepository<DailyEntry, Long>{

	@Query("select D from DailyEntry D where employee = ?1")
	List<DailyEntry> findByEmployee(Employee employee);

}

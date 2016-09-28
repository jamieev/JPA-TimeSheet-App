package time.repo;

import org.springframework.data.repository.CrudRepository;

import time.model.TimeSheet;

public interface TimeSheetRepository extends CrudRepository<TimeSheet, Long>{

}

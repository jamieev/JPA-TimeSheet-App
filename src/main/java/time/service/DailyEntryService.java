package time.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import time.model.DailyEntry;
import time.repo.DailyEntryRepository;

@Service
public class DailyEntryService {

	@Resource
	DailyEntryRepository entryRepo;
	
	public DailyEntry createDailyEntry(@RequestBody DailyEntry entry) {
		return entryRepo.save(entry);
	}
	
	public List<DailyEntry> findEntriesByEmployee(@PathVariable(name="id") Long id) {
		return entryRepo.findByEmployee(id);
	}
}

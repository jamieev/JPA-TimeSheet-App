package time.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import time.model.DailyEntry;
import time.repo.DailyEntryRepository;
import time.service.DailyEntryService;

@Service
public class DailyEntryServiceImpl implements DailyEntryService {

	@Resource
	DailyEntryRepository entryRepo;
	
	public DailyEntry createDailyEntry(DailyEntry entry) {
		return entryRepo.save(entry);
	}
	
	public List<DailyEntry> findEntriesByEmployee(Long id) {
		return entryRepo.findByEmployee(id);
	}
}

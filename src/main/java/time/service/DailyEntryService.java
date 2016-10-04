package time.service;

import java.util.List;

import time.model.DailyEntry;

public interface DailyEntryService {

	public DailyEntry createDailyEntry(DailyEntry entry);
	
	public List<DailyEntry> findEntriesByEmployee(Long id);
}

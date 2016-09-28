package time.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import constants.TimeSheetStatus;

@Entity
public class TimeSheet {
	
	public static final long TOTAL_DAY_TARGET = 5;

	@Id
	@GeneratedValue
	private Long id;

	public TimeSheet() {
		super();
	}
	
	@OneToOne
	private Employee employee;
	
	@Temporal(TemporalType.DATE)
	private Date startdate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private TimeSheetStatus status;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	List<DailyEntry> entries;

	public TimeSheet(Employee employee, Date startdate, Date endDate) {
		super();
		this.employee = employee;
		this.startdate = startdate;
		this.endDate = endDate;
		status = TimeSheetStatus.NEW;
	}
	
	public void approve() {
		status = TimeSheetStatus.APPROVED;
	}
	
	public void submit() {
		status = TimeSheetStatus.AWAITING_APPROVAL;
	}
	
	public double getValue() {
		double totalDays = 0D;
		for(DailyEntry entry: entries) {
			totalDays += entry.getValue();
		}
		return totalDays;
	}
	
	public boolean isComplete() {
		return getValue() >= TOTAL_DAY_TARGET;
	}

	public List<DailyEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<DailyEntry> entries) {
		this.entries = entries;
	}
	
	public TimeSheetStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "TimeSheet [id=" + id + ", employee=" + employee.getGtNumber() + ", startdate=" + startdate + ", endDate=" + endDate
				+ ", status=" + status + ", entries=" + (entries == null?null:entries.size()) + ", value=" + getValue() + "]";
	}
}

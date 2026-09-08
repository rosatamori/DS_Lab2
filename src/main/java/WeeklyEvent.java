import java.util.GregorianCalendar;

import calendar.MeetingCalendar;

public class WeeklyEvent extends CalendarEvent {
	
	private GregorianCalendar repeatUntil;

	/**
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	public WeeklyEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime, GregorianCalendar repeat) {
		super(description, location, startTime, endTime);
		// TODO Auto-generated constructor stub
		this.repeatUntil = repeat;
	}
	
	public void scheduleEvent(MeetingCalendar cal) {
		return;
	}

	//getters and setters
	/**
	 * @return the repeatUntil
	 */
	public GregorianCalendar getRepeatUntil() {
		return repeatUntil;
	}

	/**
	 * @param repeatUntil the repeatUntil to set
	 */
	public void setRepeatUntil(GregorianCalendar repeatUntil) {
		this.repeatUntil = repeatUntil;
	}
	
	
}

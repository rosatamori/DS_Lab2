import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class MultiDayPerWeekEvent extends CalendarEvent {
	
	
	private GregorianCalendar repeatUntil;
	private int[] days;

	public MultiDayPerWeekEvent(String description, String location, GregorianCalendar startTime,
			GregorianCalendar endTime, GregorianCalendar repeat, int[] days) {
		
		super(description, location, startTime, endTime);
		
		this.repeatUntil = repeat;
		this.days = days;
	}
	
	public void scheduleEvent(MeetingCalendar cal) {
		//get meeting info
		//String desc = getDescription();
		//String loc = getLocation();
		//GregorianCalendar
		//Meeting newEvent = new Meeting(getDescription(), getLocation(), getStartTime(), getEndTime());
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

	/**
	 * @return the days
	 */
	public int[] getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int[] days) {
		this.days = days;
	}

	
}
